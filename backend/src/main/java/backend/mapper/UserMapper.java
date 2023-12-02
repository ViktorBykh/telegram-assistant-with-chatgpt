package backend.mapper;

import backend.dto.response.UserResponseDto;
import backend.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponseDto mapToDto(User user);
}
