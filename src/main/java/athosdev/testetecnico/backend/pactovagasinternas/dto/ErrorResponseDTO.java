package athosdev.testetecnico.backend.pactovagasinternas.dto;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public class ErrorResponseDTO {

    private HttpStatusCode statusCode;

    private String message;

    public ErrorResponseDTO(String message, HttpStatusCode statusCode) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatusCode getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }
}
