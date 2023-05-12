package com.svalero.carsApi.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity (name = "oficina")
public class Oficina {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;
    @Column (name = "num_trabajadores")
    private int numTrabajadores;
    @Column
    @NotBlank(message = "este dato no puede quedar en blanco")
    @NotNull(message = "solo se adminten datos validos")
    private String direccion;
    @Column
    @NotBlank(message = "este dato no puede quedar en blanco")
    @NotNull(message = "solo se adminten datos validos")
    private String nombre;
    @Column
    @Min(value = 9)
    private String telefono;
    @Column
    private String email;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "ciudad_id")
    private Ciudad ciudad;




}
