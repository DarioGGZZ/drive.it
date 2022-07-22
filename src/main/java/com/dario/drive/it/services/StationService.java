package com.dario.drive.it.services;

import com.dario.drive.it.exceptions.StationNotContainsSpecifiedBikeException;
import com.dario.drive.it.models.bo.BikeBO;
import com.dario.drive.it.models.bo.StationBO;
import com.dario.drive.it.models.mappers.BikeMapper;
import com.dario.drive.it.models.mappers.StationMapper;
import com.dario.drive.it.repositories.IStationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class StationService {

    @Autowired
    IStationRepository repository;

    public void createStation(StationBO stationBO) throws EntityExistsException{
        if(repository.existsByName(stationBO.getName())){
            throw new EntityExistsException("Station with name " + stationBO.getName() + " already exists");
        }
        repository.save(StationMapper.convertBOtoDE(stationBO));
    }

    public void updateStation(StationBO updatedStation, Long id){
        updatedStation.setId(id);
        repository.save(StationMapper.convertBOtoDE(updatedStation));
    }

    public StationBO findByName(String name){
        return StationMapper.convertDEtoBO(repository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Station with name " + name + " not exists")));
    }

    public List<BikeBO> getBikesFromStation(String name){
        return findByName(name).getBikes();
    }

    public BikeBO getBikeFromStation(String name, Long id) throws StationNotContainsSpecifiedBikeException{
        StationBO stationBO = findByName(name);
        if(!stationBO.containsBikeWithId(id)){
            throw new StationNotContainsSpecifiedBikeException("The station " + name + " not contains the bike with id " + id);
        }
        BikeBO bike = stationBO.getBikeWithId(id);
        repository.save(StationMapper.convertBOtoDE(stationBO));
        return bike;
    }

}
