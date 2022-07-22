package com.dario.drive.it.controllers;

import com.dario.drive.it.controllers.mappers.BikeApiMapper;
import com.dario.drive.it.controllers.to.BikeTO;
import com.dario.drive.it.controllers.to.ResponseTO;
import com.dario.drive.it.enums.BikeAvailabilityEnum;
import com.dario.drive.it.services.BikeService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

@RestController
@Log4j2
public class BikeController implements IBikeController{

    @Autowired
    BikeService bikeService;

    @Override
    public ResponseEntity<ResponseTO> addBike() {
        log.info("INICIO DE LA CREACION DE LA BICICLETA");
        ResponseEntity<ResponseTO> response;
        try{
            bikeService.saveBike(BikeApiMapper.convertTOtoBO(
                    BikeTO.builder()
                            .status(BikeAvailabilityEnum.FREE)
                            .usefulLife(1000.0d)
                            .travelHours(0.0d)
                            .build()));
            response = new ResponseEntity<>(ResponseTO.builder().message(HttpStatus.CREATED.name()).build(), HttpStatus.CREATED);
            log.info("FIN DE LA CREACION DE LA BICICLETA");
        } catch (EntityExistsException e){
            log.error("OCURRIO UN ERROR CON LA CREACION DE LA BICICLETA: " + e);
            response = new ResponseEntity<>(ResponseTO.builder().message(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @Override
    public ResponseEntity<?> getBikeById(Long id) {
        log.info("INICIO DE LA OBTENCION DE LA BICICLETA");
        ResponseEntity<?> response;
        try {
            BikeTO bikeTO = BikeApiMapper.convertBOtoTO(bikeService.getBike(id));
            response = new ResponseEntity<>(bikeTO, HttpStatus.OK);
            log.info("FIN DE LA OBTENCION DE LA BICICLETA");
        } catch (EntityNotFoundException e){
            log.error("OCURRIO UN ERROR CON LA OBTENCION DE LA BICICLETA: " + e);
            response = new ResponseEntity<>(ResponseTO.builder().message(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
        return response;
    }
}
