package server.command;
import Comand.base.*;
import server.command.base.Command;

import java.util.Scanner;

import static server.command.base.CollectionManager.priorityQueue;

public class RemoveLower extends Command {
    public RemoveLower(){
        super("remove_lower");
    }
    @Override
    public void execute() {
        long id;
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println("Введите id элемента, элементы меньшие которого хотите удалить");
            try {
                String input = in.nextLine();
                id = Long.parseLong(input);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: Некорректный формат числа. Пожалуйста, введите число long");
            }
        }
        long lastElementUnderDelete = id;
        priorityQueue.removeIf(item -> item.getId() < lastElementUnderDelete);
        System.out.println("Выполнено успешно");
    }

    @Override
    public String getHelp() {
        return "удалить из коллекции все элементы, меньшие, чем заданный";
    }
}
