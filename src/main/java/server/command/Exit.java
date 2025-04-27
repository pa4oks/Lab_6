package server.command;

import shared.dto.Response;

public class Exit {
    public Response execute() {
        System.out.println("Завершение работы сервера...");
        return new Response(Response.Status.OK, "Server shutdown");
    }
}