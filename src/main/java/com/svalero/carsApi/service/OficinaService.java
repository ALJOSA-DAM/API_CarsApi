package com.svalero.carsApi.service;

import com.svalero.carsApi.domain.Oficina;
import com.svalero.carsApi.domain.dto.OficinaDTO;
import com.svalero.carsApi.exception.CiudadNotFoundException;
import com.svalero.carsApi.exception.OficinaNotFoundException;

import java.util.List;

public interface OficinaService {

    List<Oficina> listar() throws OficinaNotFoundException;

    List<Oficina> listarPorCiudad(int id);

    Oficina buscarPorId(long id) throws OficinaNotFoundException;

    Oficina addOffice(OficinaDTO oficinaDTO) throws CiudadNotFoundException, OficinaNotFoundException;

    Oficina eliminarOficina(long id) throws OficinaNotFoundException;

    Oficina modificarOficina(long id, Oficina oficina) throws OficinaNotFoundException;

}
