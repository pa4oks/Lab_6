package server.command.base;

import server.collection.CollectionManager;

/**
 * Маркерный интерфейс для серверных команд
 */
public interface ServerCommand {
    /**
     * Инициализация команды с менеджером коллекции
     */
    void setCollectionManager(CollectionManager collectionManager);

    /**
     * Проверка прав доступа для команды
     */
    default boolean checkAccess(String userRole) {
        return true; // По умолчанию доступ разрешен
    }
}