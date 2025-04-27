package server.command.base;

import server.collection.CollectionManager;
import shared.dto.Request;
import shared.dto.Response;

/**
 * Абстрактный базовый класс для всех команд сервера
 */
public abstract class Command {
    private final String name;
    private final String description;

    protected Command(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Основной метод выполнения команды
     */
    public abstract Response execute(Request request);

    /**
     * Получение краткой справки по команде
     */
    public String getHelp() {
        return name + ": " + description;
    }
    void setCollectionManager(CollectionManager collectionManager){
        
    };

    /**
     * Проверка прав доступа для команды
     */
    boolean checkAccess(String userRole) {
        return true; // По умолчанию доступ разрешен
    }
}