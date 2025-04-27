package server.command;

import server.collection.CollectionManager;
import shared.dto.Request;
import shared.dto.Response;
import shared.model.LabWork;

public class Update_id {
    private final CollectionManager collectionManager;

    public Update_id(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public Response execute(Request request) {
        try {
            Object[] data = (Object[]) request.getData();
            long id = (long) data[0];
            LabWork newLabWork = (LabWork) data[1];

            if (!collectionManager.containsId(id)) {
                return new Response(Response.Status.ERROR, "Элемент с ID " + id + " не найден");
            }

            collectionManager.updateById(id, newLabWork);
            return new Response(Response.Status.OK, "Элемент с ID " + id + " успешно обновлен");
        } catch (ClassCastException e) {
            return new Response(Response.Status.ERROR, "Неверный формат данных");
        }
    }
}