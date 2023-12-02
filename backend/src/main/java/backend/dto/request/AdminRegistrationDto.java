package backend.dto.request;

import lombok.Data;

@Data
public class AdminRegistrationDto {
    private String firstName;
    private String lastName;
    private String login;
    private String password;
}
