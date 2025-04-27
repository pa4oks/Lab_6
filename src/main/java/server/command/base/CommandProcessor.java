package server.command.base;

import shared.dto.Request;
import shared.dto.Response;
import java.util.Map;

/**
 * Обработчик команд, управляющий выполнением
 */
public class CommandProcessor {
    private final Map<String, Command> commandMap;

    public CommandProcessor(Map<String, Command> commandMap) {
        this.commandMap = commandMap;
    }

    /**
     * Обрабатывает сырую команду от клиента
     */
    public Response processCommand(Request request) {
        Command command = commandMap.get(request.getCommandName());
        if (command == null) {
            return new Response(Response.Status.ERROR,
                    "Неизвестная команда: " + request.getCommandName());
        }

        try {
            return command.execute(request);
        } catch (Exception e) {
            return new Response(Response.Status.ERROR,
                    "Ошибка выполнения команды: " + e.getMessage());
        }
    }

    /**
     * Возвращает список всех доступных команд
     */
    public Map<String, Command> getCommandMap() {
        return commandMap;
    }
}