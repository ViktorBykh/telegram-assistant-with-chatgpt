package backend.dto.request;

import backend.model.Message;
import java.util.List;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@Data
@RequiredArgsConstructor
@Accessors(chain = true)
public class ChatRequestDto {
    public static final String DEFAULT_OPENAI_ROLE = "user";
    public static final int DEFAULT_MESSAGE_NUMBER = 1;
    private String model;
    private List<Message> messages;
    private int number;
    private double temperature;

    public ChatRequestDto(String model, String prompt) {
        this.model = model;
        this.number = DEFAULT_MESSAGE_NUMBER;
        this.messages = List.of(new Message()
                .setRole(DEFAULT_OPENAI_ROLE)
                .setContent(prompt));
    }
}
