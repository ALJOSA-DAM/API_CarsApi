package com.svalero.carsApi.exception;

public class UsuarioNotFoundException extends Exception{
    public UsuarioNotFoundException(){
        super("Usuario not found");
    }
    public UsuarioNotFoundException(String message){

        super(message);
    }
}
