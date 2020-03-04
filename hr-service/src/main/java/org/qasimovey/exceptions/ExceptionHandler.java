package org.qasimovey.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger logger= LoggerFactory.getLogger(ExceptionHandler.class);

    @org.springframework.web.bind.annotation.ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorInfo> handleNotFoundException(NotFoundException ex) {
        ErrorInfo em = new ErrorInfo(HttpStatus.NOT_FOUND, "NO DATA FOUND", ex.getLocalizedMessage());
        logger.info("no data found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(em);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @org.springframework.web.bind.annotation.ExceptionHandler(ServiceException.class)
    public ResponseEntity<ErrorInfo> handleInternalError(ServiceException ex){
        ErrorInfo errorInfo=new ErrorInfo(HttpStatus.INTERNAL_SERVER_ERROR,"Sistem doesnt work "+ex.getLocalizedMessage() ,ex.getLocalizedMessage());
        logger.info("handleInternalError icinde");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorInfo);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorInfo> handleBadRequestException(BadRequestException ex){
        ErrorInfo ei = new ErrorInfo(HttpStatus.BAD_REQUEST, "Bad Request sent", ex.getLocalizedMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ei);
    }



}
