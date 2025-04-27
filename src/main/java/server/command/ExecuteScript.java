package server.command;

import server.command.base.Command;
import server.command.base.CommandManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ExecuteScript extends Command {

    private final CommandManager commandManager;
    private String fileName;

    public ExecuteScript(CommandManager commandManager) {
        super("execute_script");
        this.commandManager = commandManager;
    }

    @Override
    public void execute() throws IllegalAccessException {
        if (fileName == null || fileName.isEmpty()) {
            System.err.println("Не указано имя файла для выполнения скрипта.");
            return;
        }

        File scriptFile = new File(fileName);

        try (Scanner scanner = new Scanner(scriptFile)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split("\\s+", 2); // Разделяем по пробелам, максимум на 2 части
                String commandName = parts[0];

                if (commandManager.getCommandList().containsKey(commandName)) {
                    Command command = commandManager.getCommandList().get(commandName);
                    try {
                        command.execute();
                    } catch (Exception e) {
                        System.err.println("Ошибка при выполнении команды '" + commandName + "' из скрипта: " + e.getMessage());
                    }
                } else {
                    System.err.println("Неизвестная команда '" + commandName + "' в скрипте.");
                }
            }
            System.out.println("Скрипт '" + fileName + "' успешно выполнен.");
        } catch (FileNotFoundException e) {
            System.err.println("Файл скрипта не найден: " + fileName);
        } catch (Exception e) {
            System.err.println("Ошибка при чтении или выполнении скрипта: " + e.getMessage());
        }
        System.out.println("Выполнено успешно");
    }

    @Override
    public String getHelp() {
        return "считать и исполнить скрипт из указанного файла.";
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}