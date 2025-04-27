package server.command;

import server.command.base.Command;
import shared.model.LabWork;

import java.util.HashMap;

import static server.command.base.CollectionManager.priorityQueue;

public class Head extends Command {
    public Head() {
        super("head");
    }

    @Override
    public void execute() throws IllegalAccessException {
        LabWork highestPriorityElement = priorityQueue.peek(); // Получаем элемент с наивысшим приоритетом
        if (highestPriorityElement != null) {
            System.out.println("Первый элемент: ");
            highestPriorityElement.ShowLabWork(highestPriorityElement);
        } else {
            System.out.println("Очередь пуста.");
        }
        System.out.println("Выполнено успешно");
    }

    @Override
    public String getHelp() {
        return "вывести первый элемент коллекции";
    }

    public static void register(HashMap<String,Command> stringCommandHashMap) {
        Head head = new Head();
        stringCommandHashMap.put(head.getName(), head);
    }
}
