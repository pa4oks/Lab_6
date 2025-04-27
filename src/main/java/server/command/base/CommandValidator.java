package server.command.base;

import shared.dto.Request;

/**
 * Валидатор входящих команд
 */
public class CommandValidator {
    /**
     * Проверяет корректность запроса
     */
    public static boolean validateRequest(Request request) {
        return request != null
                && request.getCommandName() != null
                && !request.getCommandName().isEmpty();
    }

    /**
     * Проверяет наличие обязательных данных в запросе
     */
    public static boolean validateCommandData(Request request, Class<?> expectedType) {
        if (request.getData() == null) return false;
        return expectedType.isInstance(request.getData());
    }
}