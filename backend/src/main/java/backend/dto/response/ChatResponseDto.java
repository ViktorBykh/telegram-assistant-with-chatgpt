package backend.dto.response;

import backend.model.Message;
import java.util.List;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ChatResponseDto {
    private List<Choice> choices;

    @Data
    @RequiredArgsConstructor
    public static class Choice {
        private int index;
        private Message message;
    }
}
