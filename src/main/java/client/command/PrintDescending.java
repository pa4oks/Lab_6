package client.command;

import shared.model.LabWork;
import client.command.base.Command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

import static server.command.base.CollectionManager.priorityQueue;

public class PrintDescending extends Command {
    public PrintDescending() {
        super("print_descending");
    }
    @Override
    public void execute() throws IllegalAccessException {
        // Создаем копию PriorityQueue.  Так как используется naturalOrder(),
        // нам не нужно передавать компаратор.
        PriorityQueue<LabWork> queueCopy = new PriorityQueue<>(priorityQueue);

        // Преобразуем в ArrayList для сортировки
        List<LabWork> reversedQueue = new ArrayList<>(queueCopy);

        // Сортируем ArrayList в обратном порядке.  Теперь LabWork должен implements Comparable
        reversedQueue.sort(Collections.reverseOrder());

        System.out.println("Элементы в порядке убывания: ");
        for (LabWork labWork : reversedQueue) {
            labWork.ShowLabWork(labWork);
            System.out.println();
        }
        System.out.println("Выполнено успешно");
    }

    @Override
    public String getHelp() {
        return "";
    }
}
