package com.svalero.carsApi.service;

import com.svalero.carsApi.domain.Alquiler;
import com.svalero.carsApi.domain.Coche;
import com.svalero.carsApi.domain.Usuario;
import com.svalero.carsApi.domain.dto.AlquilerDTO;
import com.svalero.carsApi.exception.AlquilerNotFoundException;
import com.svalero.carsApi.exception.CocheNotFoundException;
import com.svalero.carsApi.exception.UsuarioNotFoundException;

import java.util.List;

public interface AlquilerService {

    List<Alquiler> listar();

    Alquiler buscarPorId(long id) throws AlquilerNotFoundException;

    Alquiler addRent(AlquilerDTO alquilerDTO) throws CocheNotFoundException, UsuarioNotFoundException;

    Alquiler eliminarAlquiler(long id) throws AlquilerNotFoundException;

    Alquiler modificarAlquiler(long id, Alquiler alquiler) throws AlquilerNotFoundException;

}
