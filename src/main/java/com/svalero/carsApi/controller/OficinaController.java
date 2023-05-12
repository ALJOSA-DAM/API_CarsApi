package com.svalero.carsApi.controller;

import com.svalero.carsApi.domain.Coche;
import com.svalero.carsApi.domain.Usuario;
import com.svalero.carsApi.exception.*;
import com.svalero.carsApi.service.CiudadService;
import com.svalero.carsApi.service.OficinaService;
import com.svalero.carsApi.domain.Ciudad;
import com.svalero.carsApi.domain.Oficina;
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
public class OficinaController {

    @Autowired
    private OficinaService oficinaService;

    @Autowired
    private CiudadService ciudadService;
    private final Logger logger = LoggerFactory.getLogger(OficinaController.class);


    @GetMapping("/oficinas")
    public List<Oficina> getOficina (@RequestParam(name = "ciudad-oficina", required = false, defaultValue = "")
                                         String ciudadId) throws OficinaNotFoundException{
        List<Oficina> oficinas;

        logger.info("filtrar oficinas por ciudad");
        if (ciudadId.equals("")){
            oficinas = oficinaService.listar();
            logger.info("En caso de que no existan oficinas en la ciudad buscada se muestra un array vacio");
        } else{
            oficinas = oficinaService.listarPorCiudad(Integer.parseInt(ciudadId));
            logger.info("muestra las oficinas que hay en la ciudad buscada");
        }
        return oficinas;
    }
    @GetMapping("/oficina/{id}")
    public ResponseEntity<Oficina> getOficina(@PathVariable long id) throws OficinaNotFoundException {
        Oficina oficina = oficinaService.buscarPorId(id);
        logger.info("listar oficinas por id");
        return ResponseEntity.ok(oficina);
    }

    @PostMapping("/ciudad/{ciudadId}/oficina")
    public ResponseEntity<Oficina> añadirOficina(@RequestBody Oficina oficina, @PathVariable long ciudadId) throws CiudadNotFoundException, OficinaNotFoundException {
        Ciudad ciudad = ciudadService.buscarPorId(ciudadId);
        logger.info("buscando la ciudad por id indicada");
        Oficina newOficina = oficinaService.añadirOficina(oficina, ciudad);
        logger.info("Se crea la nueva oficina");
        return ResponseEntity.status(HttpStatus.CREATED).body(newOficina);
    }


    @DeleteMapping("/oficinas/{id}")
    public Oficina eliminarOficina(@PathVariable long id) throws OficinaNotFoundException {
        Oficina oficina = oficinaService.eliminarOficina(id);
        logger.info("eliminar oficina");
        return oficina;

    }

    @PutMapping("/oficinas/{id}")
    public Oficina modificarOficina(@PathVariable long id, @RequestBody Oficina oficina) throws OficinaNotFoundException{
        Oficina newOficina = oficinaService.modificarOficina(id, oficina);
        logger.info("modificando oficina");
        return newOficina;

    }
    @ExceptionHandler(OficinaNotFoundException.class)
    public ResponseEntity<MensajeError> oficinaNotFoundException(OficinaNotFoundException onfe){
        logger.error(onfe.getMessage(), onfe);
        MensajeError mensajeError = new MensajeError(404,onfe.getMessage());
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
