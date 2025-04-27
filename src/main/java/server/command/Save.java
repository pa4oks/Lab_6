package server.command;

import server.collection.CollectionManager;
import shared.dto.Response;

public class Save {
    private final CollectionManager collectionManager;

    public Save(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public Response execute() {
        boolean success = collectionManager.saveDataToFile(false);
        return success
                ? new Response(Response.Status.OK, "Коллекция сохранена")
                : new Response(Response.Status.ERROR, "Ошибка при сохранении");
    }
}