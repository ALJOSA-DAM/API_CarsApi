package com.svalero.carsApi.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MensajeError {
    public int code;
    public String message;
    private Map<String, String> errors;

    public MensajeError(int code, String message){
        this.code = code;
        this.message = message;
        errors = new HashMap<>();
    }
}
