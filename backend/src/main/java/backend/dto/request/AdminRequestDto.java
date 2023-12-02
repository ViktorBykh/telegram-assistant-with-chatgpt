package backend.dto.request;

import lombok.Data;

@Data
public class AdminRequestDto {
    private String login;
    private String password;
}
