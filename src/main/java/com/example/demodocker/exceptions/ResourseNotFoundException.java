package com.example.demodocker.exceptions;

public class ResourseNotFoundException extends  RuntimeException {
    public ResourseNotFoundException(String msg){
        super(msg);
    }
    public ResourseNotFoundException(String msg,Throwable throwable){
        super(msg,throwable);
    }
}