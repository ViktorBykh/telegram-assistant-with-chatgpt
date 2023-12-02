package backend.dto.response;

import lombok.Data;

@Data
public class ErrorResponseDto {
    private String message;

    public ErrorResponseDto(String message) {
        this.message = message;
    }
}
