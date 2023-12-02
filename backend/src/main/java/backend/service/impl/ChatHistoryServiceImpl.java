package backend.service.impl;

import backend.model.ChatHistory;
import backend.repository.ChatHistoryRepository;
import backend.service.ChatHistoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatHistoryServiceImpl implements ChatHistoryService {
    private final ChatHistoryRepository chatHistoryRepository;

    @Override
    public List<ChatHistory> findChatHistoriesByUserExternalId(Long externalId,
                                                               PageRequest pageRequest) {
        return chatHistoryRepository.findChatHistoriesByUserExternalId(externalId, pageRequest);
    }

    @Override
    public void saveChatHistory(ChatHistory chatHistory) {
        chatHistoryRepository.save(chatHistory);
    }
}
