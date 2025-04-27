package server.command;

import server.collection.CollectionManager;
import shared.dto.Response;
import java.util.List;
import shared.model.LabWork;

public class PrintDescending {
    private final CollectionManager collectionManager;

    public PrintDescending(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public Response execute() {
        List<LabWork> descending = collectionManager.getDescending();
        return new Response(
                Response.Status.OK,
                descending.isEmpty() ? "Коллекция пуста" : "Элементы в порядке убывания",
                descending
        );
    }
}