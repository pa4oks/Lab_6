package server.collection;

import shared.model.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class CollectionManager {
    private final PriorityQueue<LabWork> priorityQueue;
    private final LocalDateTime initializationDate;

    public CollectionManager() {
        this.priorityQueue = new PriorityQueue<>(Comparator.naturalOrder());
        this.initializationDate = LocalDateTime.now();
    }

    public PriorityQueue<LabWork> getCollection() {
        return new PriorityQueue<>(priorityQueue);
    }

    public void add(LabWork labWork) {
        if (labWork == null) {
            throw new IllegalArgumentException("LabWork cannot be null");
        }
        priorityQueue.add(labWork);
    }

    public void remove(LabWork labWork) {
        priorityQueue.remove(labWork);
    }

    public boolean removeById(long id) {
        return priorityQueue.removeIf(lw -> lw.getId() == id);
    }

    public int getSize() {
        return priorityQueue.size();
    }

    public LabWork getFirst() {
        return priorityQueue.peek();
    }

    public LocalDateTime getInitializationDate() {
        return initializationDate;
    }

    public void clear() {
        priorityQueue.clear();
    }

    public List<LabWork> getSortedByAuthorLocation() {
        return priorityQueue.stream()
                .sorted(Comparator.comparing(
                        lw -> lw.getAuthor().getLocation().getName()
                ))
                .collect(Collectors.toList());
    }

    public List<LabWork> filterByDifficulty(Difficulty difficulty) {
        if (difficulty == null) return Collections.emptyList();

        return priorityQueue.stream()
                .filter(lw -> difficulty.equals(lw.getDifficulty()))
                .collect(Collectors.toList());
    }

    public long countGreaterThanMinimalPoint(double minimalPoint) {
        return priorityQueue.stream()
                .filter(lw -> lw.getMinimalPoint() != null)
                .filter(lw -> lw.getMinimalPoint() > minimalPoint)
                .count();
    }

    public List<LabWork> getDescending() {
        return priorityQueue.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

    public Optional<LabWork> findById(long id) {
        return priorityQueue.stream()
                .filter(lw -> lw.getId() == id)
                .findFirst();
    }

    public boolean containsId(long id) {
        return priorityQueue.stream()
                .anyMatch(lw -> lw.getId() == id);
    }

    public boolean updateById(long id, LabWork updatedLabWork) {
        if (updatedLabWork == null) return false;

        Optional<LabWork> existing = findById(id);
        if (existing.isPresent()) {
            remove(existing.get());
            add(updatedLabWork);
            return true;
        }
        return false;
    }

    public String getCollectionInfo() {
        return String.format(
                "Тип: %s\nИнициализация: %s\nЭлементов: %d",
                priorityQueue.getClass().getSimpleName(),
                initializationDate,
                priorityQueue.size()
        );
    }

    public List<LabWork> removeLower(LabWork labWork) {
        List<LabWork> removed = priorityQueue.stream()
                .filter(lw -> lw.compareTo(labWork) < 0)
                .collect(Collectors.toList());

        priorityQueue.removeAll(removed);
        return removed;
    }

    public void clearCollection() {
        priorityQueue.clear();
    }

    public List<LabWork> getSortedCollection() {
        return priorityQueue.stream().sorted(Comparator.comparing(LabWork::getId)).collect(Collectors.toList());
    }

    public String getCollectionType() {
        return priorityQueue.getClass().getSimpleName();
    }

    public LabWork removeFirst() {
        return priorityQueue.remove();
    }
}