package ru.shirokikh.exception.handler;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.shirokikh.exception.ClientNotFoundException;
import ru.shirokikh.exception.ContactValueIsAlreadyExist;
import ru.shirokikh.exception.response.ErrorMessageResponse;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(ClientNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessageResponse handleClientNotFoundException(ClientNotFoundException ex) {
        ErrorMessageResponse errorMessageResponse = new ErrorMessageResponse();
        errorMessageResponse.setMessage(ex.getMessage());
        errorMessageResponse.setStatus(HttpStatus.NOT_FOUND);
        return errorMessageResponse;
    }

    @ExceptionHandler(ContactValueIsAlreadyExist.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorMessageResponse handleContactValueIsAlreadyExist(ContactValueIsAlreadyExist ex) {
        ErrorMessageResponse errorMessageResponse = new ErrorMessageResponse();
        errorMessageResponse.setMessage(ex.getMessage());
        errorMessageResponse.setStatus(HttpStatus.CONFLICT);
        return errorMessageResponse;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        ex.getBindingResult().getGlobalErrors().forEach(error ->
                errors.put("object", error.getDefaultMessage())
        );

        return errors;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleConstraintViolation(ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getConstraintViolations().forEach(violation ->
                errors.put("error", violation.getMessage())
        );
        return errors;
    }
}
