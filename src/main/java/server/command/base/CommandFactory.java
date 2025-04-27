package server.command.base;
package server.command;
import server.collection.CollectionManager;
import server.command.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Фабрика для создания объектов команд
 */
public class CommandFactory {
    private final CollectionManager collectionManager;

    public CommandFactory(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * Создает и возвращает все доступные команды
     */
    public Map<String, Command> createCommands() {
        Map<String, Command> commands = new HashMap<>();

        // Базовые команды
        commands.put("info", new Info(collectionManager));
        commands.put("show", new Show(collectionManager));
        commands.put("add", new Add(collectionManager));

        // Команды модификации
        commands.put("update", new Update_id(collectionManager));
        commands.put("remove_by_id", new RemoveById(collectionManager));
        commands.put("clear", new Clear(collectionManager));

        // Специальные команды
        commands.put("execute_script", new ExecuteScript(collectionManager, this));
        commands.put("exit", new Exit());
        commands.put("help", new Help(commands));

        return commands;
    }
}