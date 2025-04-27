package server.command;

import server.collection.CollectionManager;
import shared.dto.Response;
import shared.model.LabWork;

import java.util.List;

public class Show {
    private CollectionManager collectionManager;

    public Show(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public Response execute() {
        List<LabWork> collection = List.copyOf(collectionManager.getCollection());
        if (collection.isEmpty()) {
            return new Response(Response.Status.OK, "Коллекция пуста");
        }
        return new Response(Response.Status.OK, "Элементы коллекции:", collection);
    }
}