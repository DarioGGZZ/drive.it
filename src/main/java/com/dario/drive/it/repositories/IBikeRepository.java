package com.dario.drive.it.repositories;

import com.dario.drive.it.models.de.BikeDE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBikeRepository extends JpaRepository<BikeDE, Long> {


}
