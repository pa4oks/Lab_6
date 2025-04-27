package server.command;

import server.command.base.Command;
import shared.model.LabWork;

import java.util.Iterator;

import static server.command.base.CollectionManager.priorityQueue;

public class Show extends Command {
    public Show() {
        super("show");
    }

    @Override
    public void execute() {
        Iterator<LabWork> iterator = priorityQueue.iterator();
        while (iterator.hasNext()) {
            LabWork labWork = iterator.next();
            labWork.ShowLabWork(labWork);
            System.out.println();
            /*
            System.out.println("Name: " + labWork.getName());
            System.out.println("ID: " + labWork.getId());
            System.out.println("Coordinates: " + labWork.getCoordinates().getX() + " " + labWork.getCoordinates().getY());
            System.out.println("CreationDate: " + labWork.getCreationDate());
            System.out.println("MinimalPoint: " + labWork.getMinimalPoint());
            System.out.println("Difficulty: " + labWork.getDifficulty());
            System.out.println("Author: " + labWork.getAuthor().getName() + "\n   eyes: " + labWork.getAuthor().getEyeColor() + "\n   hair: " + labWork.getAuthor().getHairColor() + "\n   location: " + labWork.getAuthor().getLocation().getName() + "\n   coordinates: " + labWork.getAuthor().getLocation().getX() + " " + labWork.getAuthor().getLocation().getY() + " " + labWork.getAuthor().getLocation().getZ() + " " + "\n   nationality: " + labWork.getAuthor().getNationality() + "\n   weight: " + labWork.getAuthor().getWeight() + "\n");*/
            System.out.println("Выполнено успешно");
        }
    }

    @Override
    public String getHelp() {
        return "выводит в стандартный поток вывода все элементы коллекции в строковом представлении";
    }
    /*public void ShowLabWork(LabWork labWork){
        System.out.println(labWork.getId());
        System.out.println(labWork.getName());
        System.out.println(labWork.getCoordinates());
        System.out.println(labWork.getCreationDate());
        System.out.println(labWork.getMinimalPoint());
        System.out.println(labWork.getDifficulty());
        System.out.println(labWork.getAuthor());
    }*/
}
