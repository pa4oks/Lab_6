package server.command;

import server.collection.CollectionManager;
import shared.dto.Response;

public class Clear {
    private CollectionManager collectionManager;

    public Clear(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public Response execute() {
        try {
            int count = collectionManager.getCollection().size();
            collectionManager.clearCollection();
            return new Response(Response.Status.OK,
                    "Коллекция очищена. Удалено элементов: " + count);
        } catch (Exception e) {
            return new Response(Response.Status.ERROR,
                    "Ошибка очистки коллекции: " + e.getMessage());
        }
    }
}