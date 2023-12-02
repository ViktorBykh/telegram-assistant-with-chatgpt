package backend.service;

import backend.model.ChatHistory;
import java.util.List;
import org.springframework.data.domain.PageRequest;

public interface ChatHistoryService {
    List<ChatHistory> findChatHistoriesByUserExternalId(Long externalId, PageRequest pageRequest);

    void saveChatHistory(ChatHistory chatHistory);
}
