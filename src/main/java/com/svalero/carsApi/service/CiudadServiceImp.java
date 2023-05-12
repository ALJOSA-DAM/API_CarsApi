package com.svalero.carsApi.service;

import com.svalero.carsApi.domain.Ciudad;
import com.svalero.carsApi.domain.Usuario;
import com.svalero.carsApi.exception.CiudadNotFoundException;
import com.svalero.carsApi.exception.UsuarioNotFoundException;
import com.svalero.carsApi.repository.CiudadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CiudadServiceImp implements CiudadService{

    @Autowired
    private CiudadRepository ciudadRepository;
    @Override
    public List<Ciudad> listar() {
        return ciudadRepository.findAll();
    }

    @Override
    public Ciudad buscarPorId(long id) throws CiudadNotFoundException {

        return ciudadRepository.findById(id)
                .orElseThrow(CiudadNotFoundException::new);
    }

    @Override
    public Ciudad añadirCiudad(Ciudad ciudad) {
        return ciudadRepository.save(ciudad);
    }
    @Override
    public Ciudad eliminarCiudad(long id) throws CiudadNotFoundException {
        Ciudad ciudad = ciudadRepository.findById(id)
                .orElseThrow(CiudadNotFoundException::new);

        ciudadRepository.delete(ciudad);
        return ciudad;
    }

    @Override
    public Ciudad modificarCiudad(long id, Ciudad newCiudad) throws CiudadNotFoundException{
        Ciudad ciudad = ciudadRepository.findById(id)
                .orElseThrow(CiudadNotFoundException::new);
        ciudad.setNombre(newCiudad.getNombre());
        ciudad.setProvincia(newCiudad.getProvincia());

        return ciudadRepository.save(ciudad);

    }

}
