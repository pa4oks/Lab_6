package server.command;

import server.collection.CollectionManager;
import shared.dto.Response;
import shared.dto.Request;

public class CountGreaterThanMinimalPoint {
    private final CollectionManager collectionManager;

    public CountGreaterThanMinimalPoint(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public Response execute(Request request) {
        try {
            double minimalPoint = (double) request.getData();
            long count = collectionManager.countGreaterThanMinimalPoint(minimalPoint);
            return new Response(
                    Response.Status.OK,
                    "Количество элементов: " + count
            );
        } catch (ClassCastException e) {
            return new Response(Response.Status.ERROR, "Неверный формат минимального значения");
        }
    }
}