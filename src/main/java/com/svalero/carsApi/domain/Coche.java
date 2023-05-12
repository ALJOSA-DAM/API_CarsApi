package com.svalero.carsApi.domain;

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
@Entity (name = "coche")
public class Coche {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    @NotBlank (message = "No puede quedar en blanco")
    @NotNull (message = "El dato introducido es nulo")
    private String marca;
    @Column
    @NotBlank (message = "No puede quedar en blanco")
    @NotNull (message = "El dato introducido es nulo")
    private String matricula;
    @Column
    private boolean disponible;

    @OneToMany(mappedBy = "coche", cascade = CascadeType.ALL)
    private List<Alquiler> alquileres;

}
