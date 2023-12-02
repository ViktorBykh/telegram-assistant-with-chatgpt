package backend.dto.response;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AdminResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String login;
    private String token;
}
