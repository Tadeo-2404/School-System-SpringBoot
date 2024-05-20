package school.demo.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomResponse {
    private Object data;
    private HttpStatus statusMessage;
    private String message;
    private int statusCode;

    public CustomResponse(HttpStatus statusMessage, String message, int statusCode) {
        this.data = null;
        this.statusMessage = statusMessage;
        this.message = message;
        this.statusCode = statusCode;
    }

    public CustomResponse(Object data, HttpStatus statusMessage, int statusCode) {
        this.data = data;
        this.statusMessage = statusMessage;
        this.message = "";
        this.statusCode = statusCode;
    }
}
