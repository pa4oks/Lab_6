package server.command;

import shared.model.*;
import server.collection.CollectionManager;
import shared.dto.Request;
import shared.dto.Response;

import java.time.LocalDate;

public class Add {
    private final CollectionManager collectionManager;

    public Add(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public Response execute(Request request) {
        try {
            LabWork labWork = (LabWork) request.getData();
            validateLabWork(labWork);

            // Проверяем, что ID не был установлен вручную
            if (labWork.getId() != null) {
                return new Response(Response.Status.ERROR, "ID генерируется автоматически и не может быть задан вручную");
            }

            // Установка даты создания если не указана
            if (labWork.getCreationDate() == null) {
                labWork.setCreationDate(LocalDate.now());
            }

            collectionManager.add(labWork);
            return new Response(Response.Status.OK, "Объект успешно добавлен", labWork);
        } catch (IllegalArgumentException e) {
            return new Response(Response.Status.ERROR, "Ошибка добавления: " + e.getMessage());
        } catch (ClassCastException e) {
            return new Response(Response.Status.ERROR, "Неверный формат данных");
        }
    }

    private void validateLabWork(LabWork labWork) {
        if (labWork.getName() == null || labWork.getName().isEmpty()) {
            throw new IllegalArgumentException("Название не может быть пустым");
        }
        if (labWork.getCoordinates() == null) {
            throw new IllegalArgumentException("Координаты обязательны");
        }
        if (labWork.getDifficulty() == null) {
            throw new IllegalArgumentException("Сложность обязательна");
        }
        if (labWork.getAuthor() == null) {
            throw new IllegalArgumentException("Автор обязателен");
        }
        validateCoordinates(labWork.getCoordinates());
        validatePerson(labWork.getAuthor());
    }

    private void validateCoordinates(Coordinates coordinates) {
        if (coordinates.getX() <= -423) {
            throw new IllegalArgumentException("Координата X должна быть > -423");
        }
        if (coordinates.getY() == null) {
            throw new IllegalArgumentException("Координата Y обязательна");
        }
    }

    private void validatePerson(Person person) {
        if (person.getName() == null || person.getName().isEmpty()) {
            throw new IllegalArgumentException("Имя автора обязательно");
        }
        if (person.getWeight() != null && person.getWeight() <= 0) {
            throw new IllegalArgumentException("Вес должен быть положительным");
        }
        if (person.getEyeColor() == null) {
            throw new IllegalArgumentException("Цвет глаз обязателен");
        }
        if (person.getLocation() == null) {
            throw new IllegalArgumentException("Локация обязательна");
        }
    }
}