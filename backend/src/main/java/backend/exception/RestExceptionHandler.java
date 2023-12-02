package backend.exception;

import backend.dto.response.ErrorResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = {AuthenticationException.class})
    @ResponseBody
    public ResponseEntity<ErrorResponseDto> handleException(AuthenticationException ex) {
        return ResponseEntity.status(ex.getHttpStatus())
                .body(new ErrorResponseDto(ex.getMessage()));
    }
}
