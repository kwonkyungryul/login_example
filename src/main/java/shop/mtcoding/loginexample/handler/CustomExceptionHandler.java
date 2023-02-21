package shop.mtcoding.loginexample.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import shop.mtcoding.loginexample.handler.ex.CustomException;
import shop.mtcoding.loginexample.util.Script;

@RestControllerAdvice
public class CustomExceptionHandler {
    
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> customException(CustomException e) { // ResponseEntity 데이터를 리턴할 때 쓴다.
        return new ResponseEntity<>(Script.back(e.getMessage()), e.getStatus());
    }
}