package co.com.user.controlleradvice;

import co.com.user.model.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ErrorModel> runtimeExceptionHandler(RuntimeException runtimeException) {
        ErrorModel errorModel = ErrorModel.builder()
                .code("E-101")
                .message(runtimeException.getMessage())
                .exception(runtimeException.getClass().getName())
                .build();
        return new ResponseEntity<>(errorModel,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<ErrorModel> userNotFoundExceptionHandler(UserNotFoundException userNotFoundException) {
        ErrorModel errorModel = ErrorModel.builder()
                .code("E-102")
                .message(userNotFoundException.getMessage())
                .exception(userNotFoundException.getClass().getName())
                .build();
        return new ResponseEntity<>(errorModel, HttpStatus.NOT_FOUND);
    }
}
