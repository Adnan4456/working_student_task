package com.xing.bikeinventory.exception;

import com.xing.bikeinventory.model.ExceptionDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    //function that will handle any exception type.
    //Also add ErrorDetails class to show  exception in custom layout.
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globleExcpetionHandler(Exception ex, WebRequest request) {

        //using builder method to create object.
         ExceptionDetails exceptionDetails = ExceptionDetails.builder()
                                                .timestamp(new Date())
                                                .message(ex.getMessage())
                                                .details(request.getDescription(false))
                                                    .build();

        return new ResponseEntity<>(exceptionDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
