package com.svalero.carsApi.repository;

import com.svalero.carsApi.domain.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends CrudRepository <Usuario, Long> {

    List<Usuario> findAll();

    Usuario findByDni(String dni);
}
