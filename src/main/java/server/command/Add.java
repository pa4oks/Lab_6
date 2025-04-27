package server.command;

import server.command.base.Command;
import server.command.base.ReaderCreator;
import shared.model.Difficulty;
import shared.model.LabWork;

import java.util.Scanner;

import static server.command.base.CollectionManager.add;

public class Add extends Command implements ReaderCreator {
    public Add() {
        super("add");
    }

    @Override
    public void execute() throws IllegalAccessException  {
        LabWork labworkNew = new LabWork();
        Scanner in = new Scanner(System.in);
        System.out.println("Введите имя: ");
        labworkNew.setName(in.nextLine());
        labworkNew.setCoordinates(coordinateReaderCreator());

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
        labworkNew.setMinimalPoint(minpoint);
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

        labworkNew.setDifficulty(selectedDifficulty);
        labworkNew.setAuthor(personReaderCreator());

        add(labworkNew);
        System.out.println("Выполнено успешно");
    }

    @Override
    public String getHelp() {
        return "добавляет новый labwork в коллекцию";
    }

   /* public static void register(HashMap<String,Command> stringCommandHashMap) {
        Add add = new Add();
        stringCommandHashMap.put(add.getName(), help);
    }*/
}
