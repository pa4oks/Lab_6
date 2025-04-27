package server.command;

import server.collection.CollectionManager;
import shared.dto.Request;
import shared.dto.Response;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ExecuteScript {
    private final CollectionManager collectionManager;
    private final CommandProcessor commandProcessor;

    public ExecuteScript(CollectionManager collectionManager, CommandProcessor commandProcessor) {
        this.collectionManager = collectionManager;
        this.commandProcessor = commandProcessor;
    }

    public Response execute(Request request) {
        String filePath = (String) request.getData();
        File scriptFile = new File(filePath);

        try (Scanner fileScanner = new Scanner(scriptFile)) {
            while (fileScanner.hasNextLine()) {
                String commandLine = fileScanner.nextLine().trim();
                if (!commandLine.isEmpty()) {
                    Response response = commandProcessor.processCommandLine(commandLine);
                    if (response.getStatus() == Response.Status.ERROR) {
                        return response;
                    }
                }
            }
            return new Response(Response.Status.OK, "Скрипт успешно выполнен");
        } catch (FileNotFoundException e) {
            return new Response(Response.Status.ERROR, "Файл скрипта не найден");
        } catch (Exception e) {
            return new Response(Response.Status.ERROR, "Ошибка выполнения скрипта: " + e.getMessage());
        }
    }
}
