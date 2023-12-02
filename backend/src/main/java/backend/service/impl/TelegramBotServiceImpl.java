package backend.service.impl;

import static backend.controller.AdminController.FIRST_RESPONSE_INDEX;
import static backend.controller.AdminController.NO_RESULT_RESPONSE;

import backend.dto.request.ChatRequestDto;
import backend.dto.response.ChatResponseDto;
import backend.model.ChatHistory;
import backend.model.User;
import backend.service.ChatHistoryService;
import backend.service.TelegramBotService;
import backend.service.UserService;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.meta.generics.LongPollingBot;
import org.telegram.telegrambots.util.WebhookUtils;

@Service
public class TelegramBotServiceImpl extends DefaultAbsSender
        implements LongPollingBot, TelegramBotService {

    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String apiUrl;

    @Value("${telegram.bot.botName}")
    private String botName;

    private final RestTemplate openaiRestTemplate;
    private final ChatHistoryService chatHistoryService;
    private final UserService userService;

    @Autowired
    private TelegramBotServiceImpl(@Value("${telegram.bot.token}")
                                   String telegramBotToken,
                                   RestTemplate openaiRestTemplate,
                                   ChatHistoryService chatHistoryService,
                                   UserService userService) {
        super(new DefaultBotOptions(), telegramBotToken);
        this.openaiRestTemplate = openaiRestTemplate;
        this.chatHistoryService = chatHistoryService;
        this.userService = userService;
    }

    @Override
    public void clearWebhook() throws TelegramApiRequestException {
        WebhookUtils.clearWebhook(this);
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            Message message = update.getMessage();
            String tgUserId = message.getChatId().toString();
            String response;
            if (message.getText().equalsIgnoreCase("/start")) {
                response = getGreetingResponse(message);
            } else {
                response = getChatGptResponse(message);
            }
            sendResponse(response, tgUserId);
        }
    }

    @Override
    public void sendResponse(String message, String chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(message);
        sendMessage.enableHtml(true);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.fillInStackTrace();
        }
    }

    private String processResponse(Message message, String response) {
        String userMessage = message.getText();
        Long externalId = message.getFrom().getId();
        User user = getUserOrCreate(externalId, message);

        ChatHistory chatHistory = new ChatHistory()
                .setUser(user)
                .setBotResponse(response)
                .setUserMessage(userMessage)
                .setDateTime(LocalDateTime.now());
        chatHistoryService.saveChatHistory(chatHistory);
        return response;
    }

    private String getChatGptResponse(Message message) {
        String userMessage = message.getText();
        ChatRequestDto request = new ChatRequestDto(model, userMessage);
        ChatResponseDto response =
                openaiRestTemplate.postForObject(apiUrl, request, ChatResponseDto.class);

        if (response == null || response.getChoices() == null
                || response.getChoices().isEmpty()) {
            return NO_RESULT_RESPONSE;
        }

        String botResponse = response.getChoices()
                .get(FIRST_RESPONSE_INDEX)
                .getMessage()
                .getContent();
        return processResponse(message, botResponse);
    }

    private String getGreetingResponse(Message message) {
        return "Hello, " + message.getFrom().getFirstName() + "!";
    }

    private User getUserOrCreate(Long externalId, Message message) {
        return userService.getByExternalId(externalId).orElseGet(()
                -> createUserAndSave(externalId, message));
    }

    private User createUserAndSave(Long externalId, Message message) {
        User user = new User()
                .setExternalId(externalId)
                .setFirstName(message.getFrom().getFirstName())
                .setLastName(message.getFrom().getLastName());
        return userService.create(user);
    }
}
