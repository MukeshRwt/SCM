package com.scm.helper;

public class ResourceNotFoundException  extends RuntimeException{

    public ResourceNotFoundException(String message){

    }
    public ResourceNotFoundException(){
        super("Resource not found");

    }

}
