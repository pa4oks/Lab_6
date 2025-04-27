import shared.model.LabWork;
import files.CSVCollectionManager;
import server.command.ExecuteScript;
import server.command.base.Command;
import server.command.base.CommandManager;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Введите путь к файлу CSV: ");
        String filePath = in.nextLine();

        System.out.println("Введите разделитель: ");
        String delimiter = in.nextLine();

         CSVCollectionManager manager = new CSVCollectionManager(filePath, delimiter);

        // Получаем коллекцию LabWork из CSVCollectionManager
        List<LabWork> labWorks = manager.getDataCollectionLabWork();

        // Пример использования данных (вывод информации о первом LabWork):
        if (!labWorks.isEmpty()) {
            System.out.println("Информация о первом LabWork:");
            LabWork firstLabWork = labWorks.get(0);
            firstLabWork.print();  // Используем метод print() из LabWork
        } else {
            System.out.println("Данные LabWork не загружены.");
        }

        // Создаем CommandManager и передаем ему CSVCollectionManager
        CommandManager commandManager = new CommandManager(manager);
        HashMap<String, Command> commandList = commandManager.getCommandList();

        // Основной цикл обработки команд
        while (in.hasNextLine()) {
            String line = in.nextLine();
            String[] parts = line.split("\\s+", 2); // Разделяем команду и аргументы
            String commandName = parts[0]; // Имя команды
            String argument = (parts.length > 1) ? parts[1] : null; // Аргумент (имя файла)

            if (commandList.containsKey(commandName)) {
                Command command = commandList.get(commandName);

                if (command instanceof ExecuteScript) { // Используем instanceof для проверки типа
                    ExecuteScript executeScriptCommand = (ExecuteScript) command; // Приводим к типу Execute_script
                    if (argument != null) {
                        executeScriptCommand.setFileName(argument); // Устанавливаем имя файла
                    } else {
                        System.err.println("Не указано имя файла для execute_script.");
                        continue; // Переходим к следующей итерации цикла
                    }
                }

                try {
                    command.execute(); // Выполняем команду
                } catch (IllegalAccessException e) {
                    System.err.println("Ошибка при выполнении команды " + commandName + ": " + e.getMessage());
                } catch (Exception e) {
                    System.err.println("Произошла ошибка при выполнении команды " + commandName + ": " + e.getMessage());
                }
            } else {
                System.err.println("Unknown command: " + commandName);
            }
        }
    }
}//    D:/Word_documents/file_proga_g.csv
//    execute_script D:/Word_documents/script.txt
//    /home/studs/s467042/Lab5/file_proga_g.csv
//    /home/studs/s467042/Lab5/script.txt