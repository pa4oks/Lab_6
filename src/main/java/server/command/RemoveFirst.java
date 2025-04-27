package server.command;

import server.collection.CollectionManager;
import shared.dto.Response;
import shared.model.LabWork;

public class RemoveFirst {
    private CollectionManager collectionManager;

    public RemoveFirst(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public Response execute() {
        if (collectionManager.getCollection().isEmpty()) {
            return new Response(Response.Status.ERROR, "Коллекция пуста");
        }
        LabWork removed = collectionManager.getCollection().poll();
        return new Response(Response.Status.OK, "Удален элемент: " + removed.getName(), removed);
    }
}