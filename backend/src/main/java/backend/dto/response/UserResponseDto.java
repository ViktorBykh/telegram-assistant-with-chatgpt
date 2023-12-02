package backend.dto.response;

import lombok.Data;

@Data
public class UserResponseDto {
    private Long externalId;
    private String firstName;
    private String lastName;
}
