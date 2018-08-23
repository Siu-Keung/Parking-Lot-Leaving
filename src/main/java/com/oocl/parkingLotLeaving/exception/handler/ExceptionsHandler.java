package com.oocl.parkingLotLeaving.exception.handler;

import com.oocl.parkingLotLeaving.exception.IllegalArgumentsException;
import com.oocl.parkingLotLeaving.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Dylan Wei
 * @date 2018-08-23 17:43
 */
@ControllerAdvice
@ResponseBody
public class ExceptionsHandler {

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler
    public String handleIllegalArgumentsException(IllegalArgumentsException e){
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler
    public String handleResourceNotFoundException(ResourceNotFoundException e){
        return e.getMessage();
    }

}
