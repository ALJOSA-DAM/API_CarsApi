package com.svalero.carsApi.repository;

import com.svalero.carsApi.domain.Ciudad;
import com.svalero.carsApi.domain.Coche;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CiudadRepository extends CrudRepository <Ciudad, Long> {

    List<Ciudad> findAll();


}

