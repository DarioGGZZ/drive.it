package com.dario.drive.it.repositories;

import com.dario.drive.it.models.de.StationDE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IStationRepository extends JpaRepository<StationDE, Long> {

    boolean existsByName(String name);

    Optional<StationDE> findByName(String name);
}
