package tr.com.hepsiburada.rest_api.core.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
@AllArgsConstructor
public class ExceptionMessage {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private final Date timestamp;
    private final HttpStatus status;
    private final String message;
}
