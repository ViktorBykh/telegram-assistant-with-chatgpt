package backend.service;

import backend.dto.request.AdminRegistrationDto;
import backend.dto.request.AdminRequestDto;
import backend.dto.response.AdminResponseDto;

public interface AdminService {
    AdminResponseDto login(AdminRequestDto adminRequestDto);

    AdminResponseDto register(AdminRegistrationDto adminRegistrationDto);
}
