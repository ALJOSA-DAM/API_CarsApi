package com.svalero.carsApi.service;


import com.svalero.carsApi.domain.Usuario;
import com.svalero.carsApi.exception.UsuarioNotFoundException;

import java.util.List;

public interface UsuarioService {
    List<Usuario> listar();
    Usuario buscarPorId(long id) throws UsuarioNotFoundException;
    Usuario buscarPorDni(String dni) throws UsuarioNotFoundException;
    Usuario añadirUsuario (Usuario usuario);
    Usuario eliminarUsuario (long id) throws UsuarioNotFoundException;
    Usuario modificarUsuario (long id, Usuario usuario) throws UsuarioNotFoundException;

}
