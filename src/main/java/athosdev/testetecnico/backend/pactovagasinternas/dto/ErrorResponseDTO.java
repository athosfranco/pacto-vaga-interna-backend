package athosdev.testetecnico.backend.pactovagasinternas.dto;

import org.springframework.http.HttpStatus;

public class ErrorResponseDTO {

    private HttpStatus statusCode;

    private String message;

    public ErrorResponseDTO(String message, HttpStatus statusCode) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }
}
