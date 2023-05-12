package com.svalero.carsApi.exception;

import com.svalero.carsApi.domain.Coche;

public class CocheNotFoundException extends Exception{

    public CocheNotFoundException(){
        super("Coche not found");
    }
    public CocheNotFoundException(String message){
        super(message);
    }
}
