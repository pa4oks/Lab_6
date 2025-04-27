package server.command;

import server.command.base.Command;

import static server.command.base.CollectionManager.priorityQueue;

public class Clear extends Command {
    public Clear() {
        super("clear");
    }
    @Override
    public void execute() throws IllegalAccessException {
        priorityQueue.clear();
        System.out.println("Выполнено успешно");
    }

    @Override
    public String getHelp() {
        return "очистить коллекцию";
    }
}
