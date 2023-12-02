package backend.exception;

import org.springframework.http.HttpStatus;

public class AuthenticationException extends RuntimeException {
    private final HttpStatus status;

    public AuthenticationException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getHttpStatus() {
        return status;
    }
}
