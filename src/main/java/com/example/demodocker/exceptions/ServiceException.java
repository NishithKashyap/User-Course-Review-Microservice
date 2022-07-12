package com.example.demodocker.exceptions;

public class ServiceException extends  RuntimeException {
    public ServiceException(String msg) {
        super(msg);
    }
}