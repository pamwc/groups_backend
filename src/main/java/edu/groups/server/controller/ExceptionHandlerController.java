package edu.groups.server.controller;

import edu.groups.server.exception.InvalidJoinCodeException;
import edu.groups.server.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Dawid on 05.11.2017 at 13:38.
 */
@ControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler({ResourceNotFoundException.class, InvalidJoinCodeException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    public String requestHandlingNoHandlerFound(Exception exception) {
        return exception.getMessage();
    }
}
