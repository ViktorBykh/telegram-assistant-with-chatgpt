package backend.service;

public interface TelegramBotService {
    void sendResponse(String message, String chatId);
}
