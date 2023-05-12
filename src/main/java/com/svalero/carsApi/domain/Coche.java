package com.svalero.carsApi.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "coches")
public class Coche {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    @NotBlank(message = "No puede quedar en blanco")
    @NotNull(message = "El dato introducido es nulo")
    private String marca;
    @Column
    @NotBlank(message = "No puede quedar en blanco")
    @NotNull(message = "El dato introducido es nulo")
    private String modelo;
    @Column
    @NotBlank(message = "No puede quedar en blanco")
    @NotNull(message = "El dato introducido es nulo")
    private String matricula;
    @Column
    private boolean disponible;
    @OneToMany(mappedBy = "coche")
    @JsonBackReference(value = "coche-alquiler")
    private List<Alquiler> alquileres;

}
