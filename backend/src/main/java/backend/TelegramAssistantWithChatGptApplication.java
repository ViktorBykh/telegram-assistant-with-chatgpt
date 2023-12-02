package backend;

import backend.service.impl.TelegramBotServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication
public class TelegramAssistantWithChatGptApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                SpringApplication.run(TelegramAssistantWithChatGptApplication.class, args);
        registerTelegramBotService(context);
    }

    private static void registerTelegramBotService(
            ConfigurableApplicationContext context) {
        TelegramBotServiceImpl telegramBotService =
                context.getBean(TelegramBotServiceImpl.class);
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(telegramBotService);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
