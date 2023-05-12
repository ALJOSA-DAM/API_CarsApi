package com.svalero.carsApi.exception;

public class CiudadNotFoundException extends Exception{

    public CiudadNotFoundException(){
        super("Ciudad not found");
    }
    public CiudadNotFoundException(String message){
        super(message);
    }

}
