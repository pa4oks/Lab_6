package shared.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Класс для передачи ответов от сервера клиенту.
 * Содержит статус выполнения, сообщение и данные (коллекцию или объект).
 */
public class Response implements Serializable {
    public enum Status {
        OK,
        ERROR,            // ошибка при выполнении
        AUTH_REQUIRED,    // нужга аутентификация
        ACCESS_DENIED    // нет прав для выполнения команды
    }

    private Status status;
    private String message;
    private Object data;
    private String sessionId;      // идентификатор сессии

    public Response() {}

    public Response(Status status, String message) {
        this.status = status;
        this.message = message;
    }

    public Response(Status status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public boolean isSuccess() {
        return status == Status.OK;
    }

    @SuppressWarnings("unchecked")
    public <T> T getDataAs(Class<T> clazz) {
        return clazz.isInstance(data) ? (T) data : null;
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> getCollectionData() {
        return data instanceof List ? (List<T>) data : null;
    }

    @Override
    public String toString() {
        return "Ответ: " +
                "статус: " + status +
                ", пояснение: " + message + '\'' +
                ", данные: " + data +
                ", sessionId: " + sessionId + '\'';
    }
}