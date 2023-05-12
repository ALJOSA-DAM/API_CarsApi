package com.svalero.carsApi.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OficinaDTO {

    private int numTrabajadores;
    private String direccion;
    private String nombre;
    private String telefono;
    private String email;
    private long ciudad;

}
