package com.svalero.carsApi.service;

import com.svalero.carsApi.domain.Usuario;
import com.svalero.carsApi.exception.UsuarioNotFoundException;
import com.svalero.carsApi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImp implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario buscarPorId(long id) throws UsuarioNotFoundException {
        return usuarioRepository.findById(id)
                .orElseThrow(UsuarioNotFoundException::new);
    }

    @Override
    public Usuario buscarPorDni(String dni) throws UsuarioNotFoundException {
        return usuarioRepository.findByDni(dni);
    }

    @Override
    public Usuario añadirUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario eliminarUsuario(long id) throws UsuarioNotFoundException {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(UsuarioNotFoundException::new);

        usuarioRepository.delete(usuario);
        return usuario;
    }

    @Override
    public Usuario modificarUsuario(long id, Usuario newUsuario) throws UsuarioNotFoundException {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(UsuarioNotFoundException::new);
        usuario.setDni(newUsuario.getDni());
        usuario.setNombre(newUsuario.getNombre());
        usuario.setApellidos(newUsuario.getApellidos());
        usuario.setFechaNacimiento(newUsuario.getFechaNacimiento());

        return usuarioRepository.save(usuario);

    }
}
