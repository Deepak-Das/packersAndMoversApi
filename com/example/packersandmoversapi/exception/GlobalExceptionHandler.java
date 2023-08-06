package com.example.packersandmoversapi.exception;

import com.example.packersandmoversapi.model.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ApiResponse> AlreadyExistException(UserAlreadyExistException ex){
        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setMessage("User already register");
        apiResponse.setError_status("Email exists");
        return new  ResponseEntity<>(apiResponse, HttpStatus.FOUND);
    }

    @ExceptionHandler(UserNotExistException.class)
    public ResponseEntity<ApiResponse> responseNotFoundException(UserNotExistException ex){
        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setMessage("User Not register");
        apiResponse.setError_status("Email Not exists");
        return new  ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EndPointNotExistException.class)
    public ResponseEntity<ApiResponse> endPointNotExistException(EndPointNotExistException ex){
        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setMessage("Not have any such endpoint");
        apiResponse.setError_status("Not Found");
        return new  ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> methodArgumentNotValidException(MethodArgumentNotValidException ex){
        Map<String,String> apiResponse = new HashMap<>();
        ex.getAllErrors().forEach(error ->
                apiResponse.put(((FieldError) error).getField(), error.getDefaultMessage())
        );

        return new  ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> responseNotFoundException(ResourceNotFoundException ex){
        String msg=ex.getMessage();
        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setMessage(msg);
        apiResponse.setError_status("404 Not Found");
        return new  ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }




}
