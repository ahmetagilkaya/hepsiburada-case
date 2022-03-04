package tr.com.hepsiburada.rest_api.core.exception;

import org.springframework.core.NestedRuntimeException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;
import java.util.Date;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ExceptionMessage handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        return new ExceptionMessage(
                new Date(),
                HttpStatus.BAD_REQUEST,
                exception.getMessage()
        );
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {DataIntegrityViolationException.class})
    public ExceptionMessage handleDataIntegrityViolationException(DataIntegrityViolationException exception) {
        return new ExceptionMessage(
                new Date(),
                HttpStatus.BAD_REQUEST,
                exception.getMessage()
        );
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ExceptionMessage handleConstraintViolationException(ConstraintViolationException exception) {
        return new ExceptionMessage(
                new Date(),
                HttpStatus.BAD_REQUEST,
                exception.getMessage()
        );
    }

    @ResponseStatus(HttpStatus.REQUEST_TIMEOUT)
    @ExceptionHandler(value = {ResourceAccessException.class})
    public ExceptionMessage handleResourceAccessException(ResourceAccessException exception) {
        return new ExceptionMessage(
                new Date(),
                HttpStatus.REQUEST_TIMEOUT,
                exception.getMessage()
        );
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = {NestedRuntimeException.class})
    public ExceptionMessage handleNestedRuntimeException(NestedRuntimeException exception) {
        return new ExceptionMessage(
                new Date(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                exception.getMessage()
        );
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    public ExceptionMessage handleHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
        return new ExceptionMessage(
                new Date(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                exception.getMessage()
        );
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = {PersistenceException.class})
    public ExceptionMessage handlePersistenceException(PersistenceException exception) {
        return new ExceptionMessage(
                new Date(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                exception.getMessage()
        );
    }

    @ExceptionHandler(value = {ResponseStatusException.class})
    public ResponseEntity<ExceptionMessage> handleResponseStatusException(ResponseStatusException exception) {
        return new ResponseEntity<>(new ExceptionMessage(
                new Date(),
                exception.getStatus(),
                exception.getReason()
        ), exception.getStatus());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = {Exception.class})
    public ExceptionMessage handleException(Exception exception) {
        return new ExceptionMessage(
                new Date(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                exception.getMessage()
        );
    }

}
