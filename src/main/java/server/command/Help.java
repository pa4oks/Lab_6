package server.command;

import shared.dto.Response;
import java.util.Map;

public class Help {
    private Map<String, String> commandList;

    public Help(Map<String, String> commandList) {
        this.commandList = commandList;
    }

    public Response execute() {
        StringBuilder sb = new StringBuilder("Доступные команды:\n");
        commandList.forEach((name, desc) ->
                sb.append(name).append(" - ").append(desc).append("\n"));
        return new Response(Response.Status.OK, sb.toString());
    }
}