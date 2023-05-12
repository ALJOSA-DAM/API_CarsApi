package com.svalero.carsApi.controller;

import com.svalero.carsApi.domain.Coche;
import com.svalero.carsApi.exception.CiudadNotFoundException;
import com.svalero.carsApi.exception.CocheNotFoundException;
import com.svalero.carsApi.exception.MensajeError;
import com.svalero.carsApi.service.CiudadService;
import com.svalero.carsApi.domain.Ciudad;
import jakarta.validation.Valid;
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
public class CiudadController {
    @Autowired
    private CiudadService ciudadService;
    private final Logger logger = LoggerFactory.getLogger(CiudadController.class);


    @GetMapping("/ciudades")
    public ResponseEntity<List<Ciudad>> getCiudades(){
        logger.info("listando ciudades");
        return ResponseEntity.ok(ciudadService.listar());
    }

    @GetMapping("/ciudad/{id}")
    public ResponseEntity<Ciudad> getCiudad(@PathVariable long id) throws CiudadNotFoundException {
        Ciudad ciudad = ciudadService.buscarPorId(id);
        logger.info("Buscar ciudad por id");
        return ResponseEntity.ok(ciudad);
    }



    @PostMapping("/ciudades")
    public ResponseEntity <Ciudad> añadirCiudad(@RequestBody Ciudad ciudad){
        Ciudad newCiudad = ciudadService.añadirCiudad(ciudad);
        logger.info("añadir una nueva ciudad");
       return ResponseEntity.status(HttpStatus.CREATED).body(newCiudad);
    }

    @DeleteMapping("/ciudades/{id}")
    public ResponseEntity<Void> eliminarCiudad(@PathVariable long id)throws CiudadNotFoundException{
        ciudadService.eliminarCiudad(id);
        logger.info("eliminar ciudad");
        return ResponseEntity.noContent().build();

    }

    @PutMapping("/ciudades/{id}")
    public ResponseEntity<Ciudad> modificarCiudad(@PathVariable long id, @RequestBody Ciudad ciudad) throws CiudadNotFoundException{
        Ciudad newCiudad = ciudadService.modificarCiudad(id,ciudad);
        logger.info("modificar ciudad");
        return ResponseEntity.status(HttpStatus.OK).body(newCiudad);

    }
    @ExceptionHandler(CiudadNotFoundException.class)
    public ResponseEntity<MensajeError> ciudadNotFoundException(CiudadNotFoundException cnfe){
        logger.error(cnfe.getMessage(), cnfe);
        MensajeError mensajeError = new MensajeError(404,cnfe.getMessage());
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
