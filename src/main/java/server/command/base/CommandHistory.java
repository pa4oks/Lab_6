package server.command.base;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Хранит историю выполненных команд
 */
public class CommandHistory {
    private static final int MAX_HISTORY_SIZE = 15;
    private final Deque<String> history = new ArrayDeque<>();

    /**
     * Добавляет команду в историю
     */
    public void addCommand(String commandLine) {
        if (history.size() >= MAX_HISTORY_SIZE) {
            history.removeFirst();
        }
        history.addLast(commandLine);
    }

    /**
     * Возвращает последние выполненные команды
     */
    public String getHistory() {
        return String.join("\n", history);
    }
}