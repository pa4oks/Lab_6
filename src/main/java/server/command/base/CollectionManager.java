package server.command.base;
import shared.model.LabWork;

import java.util.*;

public class CollectionManager implements ReaderCreator{
    public static PriorityQueue<LabWork> priorityQueue = new PriorityQueue<>(Comparator.naturalOrder());
    public static PriorityQueue<LabWork> getCollection(){
        return priorityQueue;
    }
    public static void add(LabWork labWork){
        priorityQueue.add(labWork);
    }



    public static void remove(LabWork labWork){
        priorityQueue.remove(labWork);
    }

    public static void removeByID(long idToRemove){
        priorityQueue.removeIf(item -> item.getId() == idToRemove);
    }

    public static int getSize() {
        return priorityQueue.size();
    }

    public static LabWork getFirst() {
        if (priorityQueue.isEmpty()) {
            return null;
        }
        return priorityQueue.peek(); // peek() retrieves, but does not remove, the head of this queue, or returns null if this queue is empty.
    }

}
