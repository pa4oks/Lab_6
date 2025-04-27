package server.command;

import server.collection.CollectionManager;
import shared.dto.Response;
import java.time.LocalDateTime;

public class Info {
    private CollectionManager collectionManager;

    public Info(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public Response execute() {
        String info = "Тип коллекции: " + collectionManager.getCollection().getClass().getName() + "\n" +
                "Количество элементов: " + collectionManager.getCollection().size() + "\n" +
                "Дата инициализации: " + LocalDateTime.now();
        return new Response(Response.Status.OK, info);
    }
}