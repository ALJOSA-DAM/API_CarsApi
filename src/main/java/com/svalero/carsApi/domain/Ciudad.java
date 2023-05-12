package com.svalero.carsApi.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity (name = "Ciudad")
public class Ciudad {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    @NotBlank (message = "este dato no puede quedar en blanco")
    @NotNull (message = "solo se adminten datos validos")
    private String nombre;
    @Column
    @NotBlank (message = "este dato no puede quedar en blanco")
    @NotNull (message = "solo se adminten datos validos")
    private String provincia;

    @OneToMany(mappedBy = "ciudad", cascade = CascadeType.ALL)
    private List<Oficina> oficinas;

}
