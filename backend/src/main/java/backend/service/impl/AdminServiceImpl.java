package backend.service.impl;

import backend.dto.request.AdminRegistrationDto;
import backend.dto.request.AdminRequestDto;
import backend.dto.response.AdminResponseDto;
import backend.exception.AuthenticationException;
import backend.mapper.AdminMapper;
import backend.model.Admin;
import backend.repository.AdminRepository;
import backend.service.AdminService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final AdminMapper adminMapper;

    @Override
    public AdminResponseDto login(AdminRequestDto adminRequestDto) {
        Admin admin = adminRepository.findByLogin(adminRequestDto.getLogin()).orElseThrow(() ->
                new AuthenticationException("Unknown user", HttpStatus.NOT_FOUND));

        if (passwordEncoder.matches(adminRequestDto.getLogin(), admin.getPassword())) {
            return adminMapper.mapToDto(admin);
        }
        throw new AuthenticationException("Invalid password or login", HttpStatus.BAD_REQUEST);
    }

    @Override
    public AdminResponseDto register(AdminRegistrationDto adminRegistrationDto) {
        Optional<Admin> optionalAdmin =
                adminRepository.findByLogin(adminRegistrationDto.getLogin());

        if (optionalAdmin.isPresent()) {
            throw new AuthenticationException("Login already exists", HttpStatus.BAD_REQUEST);
        }

        Admin admin = adminMapper.mapToEntity(adminRegistrationDto);
        admin.setPassword(passwordEncoder.encode(adminRegistrationDto.getPassword()));
        Admin savedAdmin = adminRepository.save(admin);
        return adminMapper.mapToDto(savedAdmin);
    }
}
