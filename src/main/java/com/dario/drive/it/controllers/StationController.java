package com.dario.drive.it.controllers;

import com.dario.drive.it.controllers.mappers.BikeApiMapper;
import com.dario.drive.it.controllers.mappers.StationApiMapper;
import com.dario.drive.it.controllers.services.ApiService;
import com.dario.drive.it.controllers.to.BikeTO;
import com.dario.drive.it.controllers.to.ResponseTO;
import com.dario.drive.it.controllers.to.StationTO;
import com.dario.drive.it.exceptions.StationNotContainsSpecifiedBikeException;
import com.dario.drive.it.exceptions.ThereAreNoSlotsAvailableException;
import com.dario.drive.it.services.StationService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@RestController
@Log4j2
public class StationController implements IStationController{

    @Autowired
    ApiService apiService;

    @Autowired
    StationService stationService;

    @Override
    public ResponseEntity<ResponseTO> addStation(StationTO station) {
        log.info("INICIO DE LA CREACION DE ESTACION");
        ResponseEntity<ResponseTO> response;
        try{
            station.setAvailableBikes(0);
            station.setBikes(new ArrayList<>());
            stationService.createStation(StationApiMapper.convertTOtoBO(station));
            response = new ResponseEntity<>(ResponseTO.builder().message(HttpStatus.CREATED.name()).build(), HttpStatus.CREATED);
        } catch (EntityExistsException e){
            log.error("OCURRIO UN ERROR CON LA CREACION DE ESTACION: " + e);
            response = new ResponseEntity<>(ResponseTO.builder().message(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
        log.info("FIN DE LA CREACION DE ESTACION");
        return response;
    }

    @Override
    public ResponseEntity<?> getStationByName(String name) {
        log.info("INICIO DE LA OBTENCION DE LA ESTACION CON NOMBRE " + name);
        ResponseEntity<?> response;
        try {
            StationTO station = StationApiMapper.convertBOtoTO(stationService.findByName(name));
            response = new ResponseEntity<>(station, HttpStatus.OK);
        } catch (EntityNotFoundException e){
            log.error("OCURRIO UN ERROR EN LA OBTENCION DE LA ESTACION: " + e);
            response = new ResponseEntity<>(ResponseTO.builder().message(e.getMessage()).build(), HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @Override
    public ResponseEntity<?> getBikesFromStation(String name) {
        log.info("INICIO DE LA OBTENCION DE BICICLETAS DE LA ESTACION " + name);
        ResponseEntity<?> response;
        try {
            List<BikeTO> bikes = BikeApiMapper.convertBOlistToTOList(stationService.getBikesFromStation(name));
            response = new ResponseEntity<>(bikes, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            log.error("OCURRIO UN ERROR EN LA OBTENCION DE LA ESTACION: " + e);
            response = new ResponseEntity<>(ResponseTO.builder().message(e.getMessage()).build(), HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @Override
    public ResponseEntity<ResponseTO> getBikeFromStationToUser(String name, Long id, Long dni) {
        log.info("INICIO DEL PROCESO DE ALQUILER DE BICI PARA EL USUARIO " + name);
        ResponseEntity<ResponseTO> response;
        try {
            apiService.getBikeFromStationToUser(name, id, dni);
            response = new ResponseEntity<>(
                    ResponseTO.builder().message("Bici con id " + id + " otorgada con exito al usuario con dni " + dni).build(),
                    HttpStatus.OK);
        } catch (StationNotContainsSpecifiedBikeException e){
            log.error("OCURRIO UN ERROR: " + e);
            response = new ResponseEntity<>(ResponseTO.builder().message(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        } catch (EntityNotFoundException e){
            log.error("OCURRIO UN ERROR: " + e);
            response = new ResponseEntity<>(ResponseTO.builder().message(e.getMessage()).build(), HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @Override
    public ResponseEntity<ResponseTO> returnBikeFromUserToStation(String name, Long dni, double timeUsed) {
        log.info("INICIO DEL PROCESO DE ALQUILER DE BICI PARA EL USUARIO " + name);
        ResponseEntity<ResponseTO> response;
        try {
            apiService.userReturnBikeToStation(name, dni, timeUsed);
            response = new ResponseEntity<>(
                    ResponseTO.builder().message("Usuario con dni " + dni + " devolvio la bici con exito. Tiempo del viaje " + timeUsed).build(),
                    HttpStatus.OK);
        } catch (ThereAreNoSlotsAvailableException e){
            log.error("OCURRIO UN ERROR: " + e);
            response = new ResponseEntity<>(ResponseTO.builder().message(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        } catch (EntityNotFoundException e){
            log.error("OCURRIO UN ERROR: " + e);
            response = new ResponseEntity<>(ResponseTO.builder().message(e.getMessage()).build(), HttpStatus.NOT_FOUND);
        }
        return response;
    }
}
