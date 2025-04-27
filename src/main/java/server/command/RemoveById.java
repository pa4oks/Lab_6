package server.command;

import server.collection.CollectionManager;
import shared.dto.Response;
import shared.dto.Request;

public class RemoveById {
    private final CollectionManager collectionManager;

    public RemoveById(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public Response execute(Request request) {
        try {
            long id = (long) request.getData();
            boolean removed = collectionManager.removeById(id);

            return removed
                    ? new Response(Response.Status.OK, "Элемент с ID " + id + " удален")
                    : new Response(Response.Status.ERROR, "Элемент с ID " + id + " не найден");
        } catch (ClassCastException e) {
            return new Response(Response.Status.ERROR, "Неверный формат ID");
        }
    }
}