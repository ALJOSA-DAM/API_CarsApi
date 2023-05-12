package com.svalero.carsApi.service;

import com.svalero.carsApi.domain.Ciudad;
import com.svalero.carsApi.domain.Usuario;
import com.svalero.carsApi.exception.CiudadNotFoundException;
import com.svalero.carsApi.exception.UsuarioNotFoundException;

import java.util.List;

public interface CiudadService {
    List<Ciudad> listar();
    Ciudad buscarPorId(long id)throws CiudadNotFoundException;
    Ciudad añadirCiudad (Ciudad ciudad);
    Ciudad eliminarCiudad (long id) throws CiudadNotFoundException;
    Ciudad modificarCiudad (long id, Ciudad ciudad) throws CiudadNotFoundException;
}
