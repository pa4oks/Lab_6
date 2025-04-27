package client.command;
import client.command.base.Command;

import static server.command.base.CommandManager.commandList;

public class Help extends Command {
    public Help() {
        super("help");
    }
    @Override
    public void execute() {
        commandList.forEach((key, command) -> {
            System.out.println(key + ": " + command.getHelp());
        });
    }

    @Override
    public String getHelp() {
        return "выводит справку по доступным командам";
    }

   /* public static void register(HashMap<String,Command> stringCommandHashMap) {
        Help help = new Help();
        stringCommandHashMap.put(help.getName(), help);
    }*/
}
