package com.svalero.carsApi.service;

import com.svalero.carsApi.domain.Coche;
import com.svalero.carsApi.exception.CocheNotFoundException;
import com.svalero.carsApi.repository.CocheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CocheServiceImp implements CocheService{

    @Autowired
    private CocheRepository cocheRepository;
    @Override
    public List<Coche> listar() {

        return cocheRepository.findAll();
    }

    @Override
    public Coche buscarPorId(long id) throws CocheNotFoundException {
        return cocheRepository.findById(id)
                .orElseThrow(CocheNotFoundException::new);

    }

    @Override
    public Coche añadirCoche(Coche coche) {

        return cocheRepository.save(coche);
    }


    @Override
    public void eliminarCoche(long id) throws CocheNotFoundException{
        Coche coche = cocheRepository.findById(id)
                .orElseThrow(CocheNotFoundException::new);

        cocheRepository.delete(coche);


    }

    @Override
    public Coche modificarCoche(long id, Coche newCoche) {
        Coche coche = cocheRepository.findById(id)
                .orElseThrow();
        coche.setMarca(newCoche.getMarca());
        coche.setMatricula(newCoche.getMatricula());
        coche.setDisponible(newCoche.isDisponible());

        return cocheRepository.save(coche);
    }


}
