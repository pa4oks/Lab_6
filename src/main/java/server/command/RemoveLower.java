package server.command;

import server.collection.CollectionManager;
import shared.dto.Response;
import shared.dto.Request;
import shared.model.LabWork;

public class RemoveLower {
    private final CollectionManager collectionManager;

    public RemoveLower(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public Response execute(Request request) {
        try {
            LabWork compared = (LabWork) request.getData();
            int removedCount = collectionManager.removeLower(compared);
            return new Response(
                    Response.Status.OK,
                    "Удалено элементов: " + removedCount
            );
        } catch (ClassCastException e) {
            return new Response(Response.Status.ERROR, "Неверный формат данных");
        }
    }
}}
