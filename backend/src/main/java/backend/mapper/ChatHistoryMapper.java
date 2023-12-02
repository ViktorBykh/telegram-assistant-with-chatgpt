package backend.mapper;

import backend.dto.response.ChatHistoryResponseDto;
import backend.model.ChatHistory;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ChatHistoryMapper {
    @Mapping(source = "user.externalId", target = "externalId")
    @Mapping(source = "user.firstName", target = "firstName")
    @Mapping(source = "user.lastName", target = "lastName")
    @Mapping(target = "dateTime", expression = "java(mapDateTime(chatHistory.getDateTime()))")
    ChatHistoryResponseDto mapToDto(ChatHistory chatHistory);

    default String mapDateTime(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("MMM d, yyyy h:mm a"));
    }
}
