package client.command;

import client.ui.ConsoleReader;
import shared.dto.Request;
import shared.model.Difficulty;
import shared.model.LabWork;
import client.command.base.Command;
import client.command.base.ReaderCreator;

import java.util.Scanner;

import static server.command.base.CollectionManager.add;

public class Add extends Command {
    public static Request prepare(Scanner scanner) {
        LabWork labWork = new ConsoleReader().read(scanner); // Бывший ReaderCreator
        return new Request("add", labWork);
    }
}
