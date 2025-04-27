package server.command;

import server.collection.CollectionManager;
import shared.dto.Response;
import shared.model.LabWork;

public class Head {
    private final CollectionManager collectionManager;

    public Head(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public Response execute() {
        LabWork first = collectionManager.getFirst();
        return first != null
                ? new Response(Response.Status.OK, "Первый элемент коллекции", first)
                : new Response(Response.Status.OK, "Коллекция пуста");
    }
}