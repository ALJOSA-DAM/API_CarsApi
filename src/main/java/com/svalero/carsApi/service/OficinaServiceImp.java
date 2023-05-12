package com.svalero.carsApi.service;

import com.svalero.carsApi.domain.Oficina;
import com.svalero.carsApi.domain.dto.OficinaDTO;
import com.svalero.carsApi.exception.CiudadNotFoundException;
import com.svalero.carsApi.exception.OficinaNotFoundException;
import com.svalero.carsApi.repository.CiudadRepository;
import com.svalero.carsApi.repository.OficinaRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OficinaServiceImp implements OficinaService {
    private final Logger logger = LoggerFactory.getLogger(OficinaServiceImp.class);

    @Autowired
    private OficinaRepository oficinaRepository;
    @Autowired
    private CiudadRepository ciudadRepository;

    @Override
    public List<Oficina> listar() throws OficinaNotFoundException {
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
    public Oficina addOffice(OficinaDTO oficinaDTO) throws CiudadNotFoundException, OficinaNotFoundException {
        ModelMapper mapper = new ModelMapper();
        logger.info("Inicio addOffice");
        Oficina oficina = mapper.map(oficinaDTO, Oficina.class);

        oficina.setCiudad(ciudadRepository.findById(oficinaDTO.getCiudad())
                .orElseThrow(CiudadNotFoundException::new));

        oficinaRepository.save(oficina);
        return oficina;
    }

    @Override
    public Oficina eliminarOficina(long id) throws OficinaNotFoundException {
        Oficina oficina = oficinaRepository.findByid(id);
        oficinaRepository.delete(oficina);

        return oficina;
    }

    @Override
    public Oficina modificarOficina(long id, Oficina newOficina) throws OficinaNotFoundException {
        Oficina oficina = oficinaRepository.findByid(id);
        oficina.setDireccion(newOficina.getDireccion());
        oficina.setTelefono(newOficina.getTelefono());
        oficina.setCiudad(newOficina.getCiudad());
        return null;
    }

}
