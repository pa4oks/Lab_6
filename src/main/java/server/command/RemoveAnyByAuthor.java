package server.command;

import server.collection.CollectionManager;
import shared.dto.Response;
import shared.dto.Request;
import shared.model.Person;

public class RemoveAnyByAuthor {
    private final CollectionManager collectionManager;

    public RemoveAnyByAuthor(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public Response execute(Request request) {
        try {
            Person author = (Person) request.getData();
            boolean removed = collectionManager.removeAnyByAuthor(author);
            return removed
                    ? new Response(Response.Status.OK, "Элемент с указанным автором удален")
                    : new Response(Response.Status.ERROR, "Элементы с указанным автором не найдены");
        } catch (ClassCastException e) {
            return new Response(Response.Status.ERROR, "Неверный формат данных автора");
        }
    }
}