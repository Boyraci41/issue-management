package com.mucahit.issuemanagement.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.Date;

@ControllerAdvice
@RestController
@Slf4j
public class IMExeptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<?> handleExceptions(Exception ex, WebRequest request){
        log.error("Controller Advice Exception ",ex,request);
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),ex.getLocalizedMessage());

        return new ResponseEntity<>(exceptionResponse,HttpStatus.EXPECTATION_FAILED);
    }
}
