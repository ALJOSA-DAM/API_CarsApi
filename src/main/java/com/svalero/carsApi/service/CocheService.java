package com.svalero.carsApi.service;

import com.svalero.carsApi.domain.Coche;
import com.svalero.carsApi.exception.CocheNotFoundException;

import java.util.List;

public interface CocheService {

    List<Coche> listar();

    List<Coche> listar(String brand, String model, String license);

    Coche buscarPorId(long id) throws CocheNotFoundException;

    Coche añadirCoche(Coche coche);

    void eliminarCoche(long id) throws CocheNotFoundException;

    Coche modificarCoche(long id, Coche coche) throws CocheNotFoundException;

}
