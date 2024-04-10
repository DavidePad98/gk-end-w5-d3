package bkendw5d3.w5d3.exceptions;

import bkendw5d3.w5d3.PayLoad.ErrorPayLoad;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ManagerException {
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorPayLoad handleBadRequest(BadRequestException ex){
        return new ErrorPayLoad(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorPayLoad handleNotFound(NotFoundException ex){
        return new ErrorPayLoad(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorPayLoad handleGenericErrors(Exception ex){
        ex.printStackTrace();
        return new ErrorPayLoad("Problema lato server! Giuro che lo risolveremo presto!", LocalDateTime.now());
    }
}
