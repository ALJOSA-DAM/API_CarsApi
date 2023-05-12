package com.svalero.carsApi.service;

import com.svalero.carsApi.domain.Alquiler;
import com.svalero.carsApi.domain.Coche;
import com.svalero.carsApi.domain.Usuario;
import com.svalero.carsApi.exception.AlquilerNotFoundException;
import com.svalero.carsApi.exception.CocheNotFoundException;

import java.util.List;

public interface AlquilerService {

    List<Alquiler> listar();
    Alquiler buscarPorId(long id) throws AlquilerNotFoundException;
    Alquiler añadirAlquiler(Alquiler alquiler, Usuario usuario, Coche coche);
    Alquiler eliminarAlquiler(long id) throws AlquilerNotFoundException;
    Alquiler modificarAlquiler(long id, Alquiler alquiler)throws AlquilerNotFoundException;

}
