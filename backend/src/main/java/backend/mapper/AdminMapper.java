package backend.mapper;

import backend.dto.request.AdminRegistrationDto;
import backend.dto.response.AdminResponseDto;
import backend.model.Admin;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AdminMapper {
    AdminResponseDto mapToDto(Admin admin);

    @Mapping(target = "password", ignore = true)
    Admin mapToEntity(AdminRegistrationDto adminRegistrationDto);
}
