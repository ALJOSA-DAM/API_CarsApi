package com.svalero.carsApi.service;

import com.svalero.carsApi.domain.Ciudad;
import com.svalero.carsApi.domain.Coche;
import com.svalero.carsApi.domain.Oficina;
import com.svalero.carsApi.exception.CiudadNotFoundException;
import com.svalero.carsApi.exception.CocheNotFoundException;
import com.svalero.carsApi.exception.OficinaNotFoundException;
import com.svalero.carsApi.repository.OficinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OficinaServiceImp implements OficinaService{

    @Autowired
    private OficinaRepository oficinaRepository;



    @Override
    public List<Oficina> listar()throws OficinaNotFoundException {
        return oficinaRepository.findAll();
    }

    @Override
    public Oficina buscarPorId(long id) throws OficinaNotFoundException {
        return oficinaRepository.findById(id)
                .orElseThrow(OficinaNotFoundException::new);

    }

    @Override
    public List<Oficina> listarPorCiudad(int ciudadId) {

        return oficinaRepository.findByCiudadId(ciudadId);
    }
    @Override
    public Oficina añadirOficina(Oficina oficina, Ciudad ciudad) throws CiudadNotFoundException, OficinaNotFoundException {
        oficina.setCiudad(ciudad);

        return oficinaRepository.save(oficina);
    }

    @Override
    public Oficina eliminarOficina(long id) throws OficinaNotFoundException{
        Oficina oficina = oficinaRepository.findByid(id);
        oficinaRepository.delete(oficina);

        return oficina;
    }

    @Override
    public Oficina modificarOficina(long id, Oficina newOficina) throws OficinaNotFoundException{
        Oficina oficina = oficinaRepository.findByid(id);
        oficina.setDireccion(newOficina.getDireccion());
        oficina.setTelefono(newOficina.getTelefono());
        oficina.setCiudad(newOficina.getCiudad());
        return null;
    }



}
