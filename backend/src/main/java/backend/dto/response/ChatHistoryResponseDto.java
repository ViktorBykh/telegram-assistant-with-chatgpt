package backend.dto.response;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ChatHistoryResponseDto {
    private Long externalId;
    private String firstName;
    private String lastName;
    private String userMessage;
    private String botResponse;
    private String dateTime;
}
