package com.svalero.carsApi.service;

import com.svalero.carsApi.domain.Alquiler;
import com.svalero.carsApi.domain.Coche;
import com.svalero.carsApi.domain.Usuario;
import com.svalero.carsApi.domain.dto.AlquilerDTO;
import com.svalero.carsApi.exception.AlquilerNotFoundException;
import com.svalero.carsApi.exception.CocheNotFoundException;
import com.svalero.carsApi.exception.UsuarioNotFoundException;
import com.svalero.carsApi.repository.AlquilerRepository;
import com.svalero.carsApi.repository.CocheRepository;
import com.svalero.carsApi.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlquilerServiceImp implements AlquilerService {
    private final Logger logger = LoggerFactory.getLogger(AlquilerServiceImp.class);

    @Autowired
    private AlquilerRepository alquilerRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CocheRepository cocheRepository;

    @Override
    public List<Alquiler> listar() {
        return alquilerRepository.findAll();
    }

    @Override
    public Alquiler buscarPorId(long id) throws AlquilerNotFoundException {
        return alquilerRepository.findById(id)
                .orElseThrow(AlquilerNotFoundException::new);

    }

    @Override
    public Alquiler addRent(AlquilerDTO alquilerDTO) throws CocheNotFoundException, UsuarioNotFoundException {
        ModelMapper mapper = new ModelMapper();
        logger.info("Inicio addRent computers");
        Alquiler alquiler = mapper.map(alquilerDTO, Alquiler.class);

        alquiler.setCoche(cocheRepository.findById(alquilerDTO.getCoche())
                .orElseThrow(CocheNotFoundException::new));
        alquiler.setUsuario(usuarioRepository.findById(alquilerDTO.getUsuario())
                .orElseThrow(UsuarioNotFoundException::new));

        alquilerRepository.save(alquiler);
        return alquiler;
    }

    @Override
    public Alquiler eliminarAlquiler(long id) throws AlquilerNotFoundException {
        Alquiler alquiler = alquilerRepository.findById(id)
                .orElseThrow(AlquilerNotFoundException::new);
        alquilerRepository.delete(alquiler);
        return alquiler;
    }

    @Override
    public Alquiler modificarAlquiler(long id, Alquiler newAlquiler) throws AlquilerNotFoundException {
        Alquiler alquiler = alquilerRepository.findById(id)
                .orElseThrow(AlquilerNotFoundException::new);
        alquiler.setFechaInicio(newAlquiler.getFechaInicio());
        alquiler.setFechaFin(newAlquiler.getFechaFin());
        alquiler.setCoche(newAlquiler.getCoche());
        alquiler.setUsuario(newAlquiler.getUsuario());

        return alquilerRepository.save(alquiler);

    }
}
