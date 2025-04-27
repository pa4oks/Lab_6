package client.command;

import shared.model.Difficulty;
import shared.model.LabWork;
import client.command.base.Command;
import client.command.base.ReaderCreator;

import java.util.Iterator;
import java.util.Scanner;

import static server.command.base.CollectionManager.priorityQueue;


public class Update_id extends Command implements ReaderCreator {
    public Update_id() {
        super("update_id");
    }
    @Override
    public void execute() throws IllegalAccessException {
        System.out.println("Введите id: ");
        Scanner in = new Scanner(System.in);
        long id;
        while (true) {
            System.out.println("Введите ID: ");
            try {
                String input = in.nextLine();
                id = Long.parseLong(input);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: Некорректный формат числа. Пожалуйста, введите число long");
            }
        }
        boolean f = false;
        Iterator<LabWork> iterator = priorityQueue.iterator();
        while (iterator.hasNext()) {
            LabWork lb = iterator.next();
            long currentId = lb.getId();

            if (currentId == id) {
                f = true;
                System.out.println("Введите имя: ");
                //while...
                lb.setName(in.nextLine());
                lb.setCoordinates(coordinateReaderCreator());
                double minpoint;
                while (true) {
                    System.out.println("Введите Минимальный Пойнт: ");
                    try {
                        String input = in.nextLine();
                        minpoint = Float.parseFloat(input);
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Ошибка: Некорректный формат числа. Пожалуйста, введите число double");
                    }
                }
                lb.setMinimalPoint(minpoint);
                Difficulty selectedDifficulty = null;
                while (selectedDifficulty == null) {
                    System.out.println("Введите сложность: ");
                    for (Difficulty difficulty : Difficulty.values()) {
                        System.out.println(difficulty);
                    }

                    try {
                        selectedDifficulty = Difficulty.valueOf(in.nextLine());
                    } catch (IllegalArgumentException e) {
                        System.out.println("Ошибка: Некорректный ввод сложности. Пожалуйста, введите еще раз");
                    }
                }
                lb.setAuthor(personReaderCreator());
                break;
            }
            /*try {
                f=true;
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: такого id нет. Пожалуйста, введите еще раз");
            }*/
        }
        //update_id(Long.parseLong(in.nextLine()));
        System.out.println("Выполнено успешно");
    }

    @Override
    public String getHelp() {
        return "изменяет поля объекта из коллекции по id";
    }
}
