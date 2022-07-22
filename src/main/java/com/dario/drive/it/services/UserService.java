package com.dario.drive.it.services;

import com.dario.drive.it.models.bo.BikeBO;
import com.dario.drive.it.models.bo.UserBO;
import com.dario.drive.it.models.mappers.BikeMapper;
import com.dario.drive.it.models.mappers.UserMapper;
import com.dario.drive.it.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

@Service
public class UserService {

    @Autowired
    IUserRepository repository;

    public void saveUser(UserBO userBO) throws EntityExistsException{
        if(repository.existsByDni(userBO.getDni())){
           throw new EntityExistsException("User with dni " + userBO.getDni() + " already exists");
        }
        userBO.setStatus(true);
        userBO.setTotalHours(0.0d);
        userBO.setUsingBike(false);
        userBO.setBikeId(null);
        repository.save(UserMapper.convertBOtoDE(userBO));
    }

    public void updateUser(UserBO updatedUser, Long id){
        updatedUser.setId(id);
        repository.save(UserMapper.convertBOtoDE(updatedUser));
    }

    public UserBO getUserByDni(Long userId) throws EntityNotFoundException{
        return UserMapper.convertDEtoBO(repository.findByDni(userId)
                .orElseThrow(() -> new EntityNotFoundException("User with id " + userId + " not found")));
    }

    public void activateUser(Long userDni){
        UserBO userBO = this.getUserByDni(userDni);
        userBO.activate();
        repository.save(UserMapper.convertBOtoDE(userBO));
    }

    public void deactivateUser(Long userDni){
        UserBO userBO = this.getUserByDni(userDni);
        userBO.deactivate();
        repository.save(UserMapper.convertBOtoDE(userBO));
    }
}
