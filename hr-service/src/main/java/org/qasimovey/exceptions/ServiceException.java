package org.qasimovey.exceptions;

public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    public ServiceException(final String message){
        super(message);
    }


}
