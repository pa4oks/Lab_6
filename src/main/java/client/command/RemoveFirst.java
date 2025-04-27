package client.command;

import client.command.base.Command;

import static server.command.base.CollectionManager.priorityQueue;

public class RemoveFirst extends Command {
    public RemoveFirst() {
        super("remove first");
    }
    @Override
    public void execute() throws IllegalAccessException {
        priorityQueue.poll();
        System.out.println("Выполнено успешно");
    }

    @Override
    public String getHelp() {
        return "удалить первый элемент из коллекции";
    }
}
