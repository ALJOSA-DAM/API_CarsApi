package com.svalero.carsApi.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity (name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    @NotBlank(message = "este dato no puede quedar en blanco")
    @NotNull(message = "solo se adminten datos validos")
    private String dni;
    @Column
    @NotBlank(message = "este dato no puede quedar en blanco")
    @NotNull(message = "solo se adminten datos validos")
    private String nombre;
    @Column
    private String apellidos;
    @Column (name = "fecha_nacimiento")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaNacimiento;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Alquiler> alquileres;
}
