package com.svalero.carsApi.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class AlquilerDTO {

    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate fechaInicio;
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate fechaFin;
    private long coche;
    private long usuario;

}
