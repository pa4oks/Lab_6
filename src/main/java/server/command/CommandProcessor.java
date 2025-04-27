package server.command;

import server.command.base.Command;
import shared.dto.Request;
import shared.dto.Response;
import java.util.Map;

public class CommandProcessor {
    private final Map<String, Command> commands;

    public CommandProcessor(Map<String, Command> commands) {
        this.commands = commands;
    }

    public Response processCommandLine(String commandLine) {
        String[] parts = commandLine.split("\\s+", 2);
        String commandName = parts[0];
        String argument = parts.length > 1 ? parts[1] : null;

        Command command = commands.get(commandName);
        if (command == null) {
            return new Response(Response.Status.ERROR, "Неизвестная команда: " + commandName);
        }

        try {
            Request request = new Request(commandName, argument);
            return command.execute(request);
        } catch (Exception e) {
            return new Response(Response.Status.ERROR, "Ошибка выполнения: " + e.getMessage());
        }
    }
}