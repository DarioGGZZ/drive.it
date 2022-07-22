package com.dario.drive.it.controllers;

import com.dario.drive.it.controllers.mappers.UserApiMapper;
import com.dario.drive.it.controllers.to.ResponseTO;
import com.dario.drive.it.controllers.to.UserTO;
import com.dario.drive.it.enums.UserStatusEnum;
import com.dario.drive.it.services.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

@RestController
@Log4j2
public class UserController implements IUserController{

    @Autowired
    UserService userService;

    @Override
    public ResponseEntity<ResponseTO> createUser(UserTO user) {
        log.info("INICIO DE LA CREACION DE USUARIO");
        ResponseEntity<ResponseTO> response;
        try{
            user.setStatus(UserStatusEnum.ACTIVE.name());
            userService.saveUser(UserApiMapper.convertTOtoBO(user));
            response = new ResponseEntity<>(ResponseTO.builder().message(HttpStatus.CREATED.name()).build(), HttpStatus.CREATED);
            log.info("FIN DE LA CREACION DE USUARIO");
        } catch (EntityExistsException e){
            log.error("OCURRIO UN ERROR CON LA CREACION DE USUARIO: " + e);
            response = new ResponseEntity<>(ResponseTO.builder().message(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @Override
    public ResponseEntity<?> getUserByUserDni(Long userDni) {
        log.info("INICIO DE LA OBTENCION DEL USUARIO");
        ResponseEntity<?> response;
        try{
            UserTO user = UserApiMapper.convertBOtoTO(userService.getUserByDni(userDni));
            response = new ResponseEntity<>(user, HttpStatus.OK);
            log.info("FIN DE LA OBTENCION DEL USUARIO");
        } catch (EntityNotFoundException e){
            log.error("OCURRIO UN ERROR CON LA OBTENCION DE USUARIO: " + e);
            response = new ResponseEntity<>(ResponseTO.builder().message(e.getMessage()).build(), HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @Override
    public ResponseEntity<ResponseTO> activateUser(Long userDni) {
        log.info("INICIO DEL PROCESO DE ACTIVACION DEL USUARIO");
        ResponseEntity<ResponseTO> response;
        try {
            userService.activateUser(userDni);
            response = new ResponseEntity<>(ResponseTO.builder().message(HttpStatus.OK.name()).build(), HttpStatus.OK);
            log.info("USUARIO ACTIVADO CON EXITO");
        } catch (EntityNotFoundException e) {
            response = new ResponseEntity<>(ResponseTO.builder().message(e.getMessage()).build(), HttpStatus.NOT_FOUND);
            log.error("OCURRIO UN ERROR: " + e);
        }
        return response;
    }

    @Override
    public ResponseEntity<ResponseTO> deactivateUser(Long userDni) {
        log.info("INICIO DEL PROCESO DE DESACTIVACION DEL USUARIO");
        ResponseEntity<ResponseTO> response;
        try {
            userService.deactivateUser(userDni);
            response = new ResponseEntity<>(ResponseTO.builder().message(HttpStatus.OK.name()).build(), HttpStatus.OK);
            log.info("USUARIO DESACTIVADO CON EXITO");
        } catch (EntityNotFoundException e) {
            response = new ResponseEntity<>(ResponseTO.builder().message(e.getMessage()).build(), HttpStatus.NOT_FOUND);
            log.error("OCURRIO UN ERROR: " + e);
        }
        return response;
    }
}
