package com.dario.drive.it.services;

import com.dario.drive.it.models.bo.BikeBO;
import com.dario.drive.it.models.mappers.BikeMapper;
import com.dario.drive.it.repositories.IBikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class BikeService {

    @Autowired
    IBikeRepository repository;

    public void saveBike(BikeBO bikeBO){
        repository.save(BikeMapper.convertBOtoDE(bikeBO));
    }

    public BikeBO getBike(Long id){
        return BikeMapper.convertDEtoBO(repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Bike with id " + id + " not exists")));
    }

    public void updateBike(BikeBO updatedBike, Long id){
        updatedBike.setId(id);
        repository.save(BikeMapper.convertBOtoDE(updatedBike));
    }
}
