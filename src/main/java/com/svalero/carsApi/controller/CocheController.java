package com.svalero.carsApi.controller;

import com.svalero.carsApi.exception.CocheNotFoundException;
import com.svalero.carsApi.exception.MensajeError;
import com.svalero.carsApi.service.CocheService;
import com.svalero.carsApi.domain.Coche;
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
public class CocheController {

    @Autowired
    private CocheService cocheService;

    private final Logger logger = LoggerFactory.getLogger(CocheController.class);

    @GetMapping("/coches")
    public ResponseEntity<List<Coche>> getCoches(){
        logger.info("llamada al método listar coches");
        return ResponseEntity.ok(cocheService.listar());

    }

    @GetMapping("/coche/{id}")
    public ResponseEntity<Coche> getCoche(@PathVariable long id) throws CocheNotFoundException {
        Coche coche = cocheService.buscarPorId(id);
        logger.info("Obteniendo coche por Id");
        return ResponseEntity.ok(coche);

    }



    @PostMapping("/coches")
    public ResponseEntity<Coche> añadirCoche(@Valid @RequestBody Coche coche){
        Coche newCoche = cocheService.añadirCoche(coche);
        logger.info("Añadiendo un nuevo coche");
        return ResponseEntity.status(HttpStatus.CREATED).body(newCoche);

    }

    @DeleteMapping("/coches/{id}")
    public ResponseEntity<Void> eliminarCoche(@PathVariable long id)throws CocheNotFoundException{
        cocheService.eliminarCoche(id);
        logger.info("eliminando un coche");
        return ResponseEntity.noContent().build();

    }

    @PutMapping("/coches/{id}")
    public ResponseEntity<Coche> modificarCoche(@PathVariable long id, @RequestBody Coche coche) throws CocheNotFoundException{
            Coche newCoche = cocheService.modificarCoche(id,coche);
            logger.info("obtenido un Id del coche a modificar");
            return ResponseEntity.status(HttpStatus.OK).body(newCoche);

    }
    @ExceptionHandler(CocheNotFoundException.class)
    public ResponseEntity<MensajeError> cocheNotFoundException(CocheNotFoundException cnfe){
        logger.error(cnfe.getMessage(), cnfe);
        MensajeError mensajeError = new MensajeError(404,cnfe.getMessage());
        return new ResponseEntity(mensajeError, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<MensajeError> handleException(Exception e){
        logger.error(e.getMessage(),e);
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
