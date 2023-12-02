package backend.repository;

import backend.model.ChatHistory;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatHistoryRepository extends JpaRepository<ChatHistory, Long> {
    List<ChatHistory> findChatHistoriesByUserExternalId(Long externalId, PageRequest pageRequest);
}
