package com.svalero.carsApi.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "alquileres")
public class Alquiler {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "fecha_recogida")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaInicio;
    @Column(name = "fecha_entrega")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaFin;
    @ManyToOne
    @JoinColumn(name = "coche_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Coche coche;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Usuario usuario;

}
