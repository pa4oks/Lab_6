package shared.dto;

import java.io.Serializable;

/**
 * Класс для передачи команд от клиента к серверу.
 * Содержит имя команды и данные (объект или примитив).
 */
public class Request implements Serializable {
    private String commandName;  // Название команды (add, remove_by_id)
    private Object data;         // Данные команды (LabWork, Long id и др.)
    private String login;        // аутентификация
    private String password;     // аутентификация

    public Request() {}

    public Request(String commandName) {
        this.commandName = commandName;
    }

    public Request(String commandName, Object data) {
        this.commandName = commandName;
        this.data = data;
    }


    public String getCommandName() {
        return commandName;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Запрос: " +
                "команда" + commandName + '\'' +
                ", данные: " + data +
                ", логин: " + login + '\'' +
                ", пароль:" + password;
    }
}