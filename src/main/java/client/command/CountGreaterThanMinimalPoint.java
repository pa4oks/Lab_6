package client.command;

import shared.model.LabWork;
import client.command.base.Command;

import java.util.Iterator;
import java.util.Scanner;

import static server.command.base.CollectionManager.priorityQueue;

public class CountGreaterThanMinimalPoint extends Command {
    public CountGreaterThanMinimalPoint() {
        super("count_greater_than_minimal_point");
    }
    @Override
    public void execute() throws IllegalAccessException {
        System.out.println("Введите значение минимального пойнта: ");
        Scanner in = new Scanner(System.in);
        double minpoint = in.nextDouble();
        Iterator<LabWork> iterator = priorityQueue.iterator();
        int count = 0;
        while (iterator.hasNext()) {
            LabWork labWork = iterator.next();
            if (labWork.getMinimalPoint() > minpoint) {
                count++;
            }
        }
        if (count == 0) {
            System.out.println("Элементов с бо'льшими минимальными пойнтами нет");
        }
        else {
            System.out.println(count);
        }
        System.out.println("Выполнено успешно");
    }

    @Override
    public String getHelp() {
        return "вывести количество элементов, значение поля minimalPoint которых больше заданного";
    }
}
