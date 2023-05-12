package com.svalero.carsApi.controller;

import com.svalero.carsApi.exception.AlquilerNotFoundException;
import com.svalero.carsApi.exception.CocheNotFoundException;
import com.svalero.carsApi.exception.MensajeError;
import com.svalero.carsApi.exception.UsuarioNotFoundException;
import com.svalero.carsApi.repository.CocheRepository;
import com.svalero.carsApi.repository.UsuarioRepository;
import com.svalero.carsApi.service.AlquilerService;
import com.svalero.carsApi.domain.Alquiler;
import com.svalero.carsApi.domain.Coche;
import com.svalero.carsApi.domain.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class AlquilerController {
    @Autowired
    private AlquilerService alquilerService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CocheRepository cocheRepository;

    private final Logger logger = LoggerFactory.getLogger(AlquilerController.class);

    @GetMapping("/alquileres")
    public ResponseEntity<List<Alquiler>> getAlquiler(){
        logger.info("Obtener lista de alquileres registrados");
        return ResponseEntity.ok(alquilerService.listar());
    }

    @GetMapping("/alquiler/{id}")
    public ResponseEntity<Alquiler> getAlquiler(@PathVariable long id) throws AlquilerNotFoundException {
        Alquiler alquiler = alquilerService.buscarPorId(id);
        logger.info("Alquiler filtrado por id");
        return ResponseEntity.ok(alquiler);
    }

    @PostMapping("/usuario/{usuarioId}/coche/{cocheId}/alquileres")
    public Alquiler añadirAlquiler(@RequestBody Alquiler alquiler, @PathVariable long usuarioId, @PathVariable long cocheId) throws CocheNotFoundException, UsuarioNotFoundException {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(UsuarioNotFoundException::new);
        logger.info("Buscando usuario por id");
        Coche coche = cocheRepository.findById(cocheId)
                .orElseThrow(CocheNotFoundException::new);
        logger.info("Buscando coche por id");

        Alquiler newAlquiler = alquilerService.añadirAlquiler(alquiler, usuario, coche);
        logger.info("Añadiendo un nuevo alquiler");
        return newAlquiler;
    }
    @DeleteMapping("/alquileres/{id}")
    public Alquiler eliminarAlquiler(@PathVariable long id) throws AlquilerNotFoundException{
        Alquiler alquiler = alquilerService.eliminarAlquiler(id);
        logger.info("Borrando un alquiler");
        return alquiler;

    }

    @PutMapping("/alquileres/{id}")
    public Alquiler modificarAlquiler(@PathVariable long id, @RequestBody Alquiler alquiler)throws AlquilerNotFoundException{
        Alquiler newAlquiler = alquilerService.modificarAlquiler(id,alquiler);
        logger.info("modificar un alquiler");
        return newAlquiler;

    }

    @ExceptionHandler(AlquilerNotFoundException.class)
    public ResponseEntity<MensajeError> alquilerNotFoundException(AlquilerNotFoundException anfe){
        logger.error(anfe.getMessage(), anfe);
        MensajeError mensajeError = new MensajeError(404,anfe.getMessage());
        return new ResponseEntity(mensajeError, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<MensajeError> handleException(Exception e){
        logger.error(e.getMessage(), e);
        MensajeError mensajeError = new MensajeError(500,"Internal server Error");
        return new ResponseEntity(mensajeError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MensajeError> handleBadRequestException(MethodArgumentNotValidException manve){
        Map<String, String> errors = new HashMap<>();
        manve.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        MensajeError badRequestMensajeError = new MensajeError(400, "Bad Request", errors);
        return new ResponseEntity<>(badRequestMensajeError, HttpStatus.BAD_REQUEST);
    }


}
