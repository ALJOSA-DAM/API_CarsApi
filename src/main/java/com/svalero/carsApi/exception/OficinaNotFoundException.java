package com.svalero.carsApi.exception;

public class OficinaNotFoundException extends Exception{

    public OficinaNotFoundException(){
        super("Oficina not found");
    }
    public OficinaNotFoundException(String message){
        super(message);
    }
}
