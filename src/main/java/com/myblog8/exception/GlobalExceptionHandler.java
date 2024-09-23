package com.myblog8.exception;

import com.myblog8.payload.ErrorDetails;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler
{
    //handles Specific Exception
    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ErrorDetails>
                           handlesResourceNotFoundException(ResourceNotFound exception,
                                                            WebRequest webRequest){

        val errorDetails = new ErrorDetails(new Date(), exception.getMessage(),
                webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails , HttpStatus.NOT_FOUND);

    }

}
