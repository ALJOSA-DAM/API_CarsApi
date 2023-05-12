package com.svalero.carsApi.exception;

public class AlquilerNotFoundException extends Exception{

    public AlquilerNotFoundException(){
        super("Alquiler not found");
    }
    public AlquilerNotFoundException(String message){

        super(message);
    }
}
