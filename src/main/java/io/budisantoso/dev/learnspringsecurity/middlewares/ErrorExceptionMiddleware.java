package io.budisantoso.dev.learnspringsecurity.middlewares;

import io.budisantoso.dev.learnspringsecurity.utils.Null;
import io.budisantoso.dev.learnspringsecurity.utils.UniversalResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorExceptionMiddleware {
    @ExceptionHandler
    public ResponseEntity<UniversalResponse<Null>> exception(Exception exp) {
        UniversalResponse<Null> response = new UniversalResponse<>(HttpStatus.NOT_FOUND.value(), exp.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<UniversalResponse<Null>> illegalException(IllegalArgumentException exp) {
        UniversalResponse<Null> response = new UniversalResponse<>(HttpStatus.BAD_REQUEST.value(), exp.getMessage());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
