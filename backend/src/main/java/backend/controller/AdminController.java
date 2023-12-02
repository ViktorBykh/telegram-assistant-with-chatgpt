package backend.controller;

import backend.config.AdminAuthProvider;
import backend.dto.request.AdminRegistrationDto;
import backend.dto.request.AdminRequestDto;
import backend.dto.request.ChatRequestDto;
import backend.dto.response.AdminResponseDto;
import backend.dto.response.ChatHistoryResponseDto;
import backend.dto.response.ChatResponseDto;
import backend.dto.response.UserResponseDto;
import backend.mapper.ChatHistoryMapper;
import backend.mapper.UserMapper;
import backend.service.ChatHistoryService;
import backend.service.SortingService;
import backend.service.UserService;
import backend.service.impl.AdminServiceImpl;
import io.swagger.v3.oas.annotations.Parameter;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    public static final int FIRST_RESPONSE_INDEX = 0;
    public static final String NO_RESULT_RESPONSE = "No result";

    private final AdminServiceImpl adminService;
    private final AdminAuthProvider adminAuthProvider;
    private final ChatHistoryService chatHistoryService;
    private final ChatHistoryMapper chatHistoryMapper;
    private final UserService userService;
    private final UserMapper userMapper;
    private final RestTemplate restTemplate;
    private final SortingService sortingService;

    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String apiUrl;

    @PostMapping("/login")
    public ResponseEntity<AdminResponseDto> login(@RequestBody AdminRequestDto adminRequestDto) {
        AdminResponseDto admin = adminService.login(adminRequestDto);
        admin.setToken(adminAuthProvider.createToken(admin));
        return ResponseEntity.ok(admin);
    }

    @PostMapping("/register")
    public ResponseEntity<AdminResponseDto> register(
            @RequestBody AdminRegistrationDto adminRegistrationDto) {
        AdminResponseDto createdAdmin = adminService.register(adminRegistrationDto);
        createdAdmin.setToken(adminAuthProvider.createToken(createdAdmin));
        return ResponseEntity
                .created(URI.create("/users/" + createdAdmin.getId()))
                .body(createdAdmin);
    }

    @GetMapping("/user")
    public ResponseEntity<List<UserResponseDto>> getUsers(
            @RequestParam(defaultValue = "5")
            @Parameter(description = "default count items is `5`") Integer count,
            @RequestParam(defaultValue = "0")
            @Parameter(description = "default number page is `0`") Integer page,
            @RequestParam(defaultValue = "externalId")
            @Parameter(description = "default sort by `externalId`") String sortBy,
            @RequestParam(defaultValue = ":ASC")
            @Parameter(description = "default sort order `ASC`") String sortOrder) {
        PageRequest pageRequest = sortingService.getSortedList(count, page, sortBy + sortOrder);
        return ResponseEntity.ok(userService.getAll(pageRequest)
                .stream()
                .map(userMapper::mapToDto)
                .toList());
    }

    @GetMapping("/user/chat-history")
    public ResponseEntity<List<ChatHistoryResponseDto>> showChatHistory(
            @RequestParam("externalId") Long externalId,
            @RequestParam(defaultValue = "5")
            @Parameter(description = "default count items is `5`") Integer count,
            @RequestParam(defaultValue = "0")
            @Parameter(description = "default number page is `0`") Integer page,
            @RequestParam(defaultValue = "dateTime")
            @Parameter(description = "default sort by `dateTime`") String sortBy,
            @RequestParam(defaultValue = ":ASC")
            @Parameter(description = "default sort order `ASC`") String sortOrder) {
        PageRequest pageRequest = sortingService.getSortedList(count, page, sortBy + sortOrder);
        return ResponseEntity.ok(chatHistoryService
                .findChatHistoriesByUserExternalId(externalId, pageRequest)
                .stream()
                .map(chatHistoryMapper::mapToDto)
                .toList());
    }

    @GetMapping("/chatGPT")
    public String chat(@RequestParam String prompt) {
        ChatRequestDto request = new ChatRequestDto(model, prompt);
        ChatResponseDto response =
                restTemplate.postForObject(apiUrl, request, ChatResponseDto.class);
        if (response == null || response.getChoices() == null
                || response.getChoices().isEmpty()) {
            return NO_RESULT_RESPONSE;
        }
        return response.getChoices().get(FIRST_RESPONSE_INDEX).getMessage().getContent();
    }
}
