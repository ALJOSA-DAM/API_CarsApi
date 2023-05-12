package com.svalero.carsApi.repository;

import com.svalero.carsApi.domain.Oficina;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OficinaRepository extends CrudRepository <Oficina, Long> {

    List<Oficina> findAll();
    Oficina findByid(long id);

    List<Oficina> findByCiudadId(int ciudadId);
}
