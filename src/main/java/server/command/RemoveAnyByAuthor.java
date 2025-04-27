package server.command;

import server.command.base.Command;
import server.command.base.ReaderCreator;
import shared.model.LabWork;
import shared.model.Person;

import java.util.Iterator;

import static server.command.base.CollectionManager.priorityQueue;

public class RemoveAnyByAuthor extends Command implements ReaderCreator {
    public RemoveAnyByAuthor() {
        super("remove_any_by_author");
    }
    @Override
    public void execute() throws IllegalAccessException {
        System.out.println("Введите автора");
        Person author = personReaderCreator();
        Iterator<LabWork> iterator = priorityQueue.iterator();
        //priorityQueue.removeIf(labWork -> labWork.getAuthor() == author);
        boolean removed = false;
        while (iterator.hasNext()) {
            LabWork labWork = iterator.next();
            if (!removed && labWork.getAuthor() == author) {
                iterator.remove();
                removed = true;
            }
        }
        System.out.println("Выполнено успешно");
    }

    @Override
    public String getHelp() {
        return "удалить из коллекции один элемент, значение поля author которого эквивалентно заданному";
    }
}
