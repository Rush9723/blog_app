package com.blog.app.exceptions;

import com.blog.app.utils.ApiResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import com.blog.app.utils.ApiResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFoundException exception) {
//        HashMap<String, String> map = new HashMap<>();
//        map.put("message", exception.getMessage());
//        map.put("field", exception.getFieldName());
//        return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        return new ResponseEntity<ApiResponse>(new ApiResponse(exception.getMessage(),false),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        Map<String,String> map = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            map.put(((FieldError)error).getField(),error.getDefaultMessage());
        });
        return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, String>> dataIntegrityViolationException(DataIntegrityViolationException dtvException) {
        HashMap<String, String> map = new HashMap<>();
        map.put("message", dtvException.getMessage());
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }
}
