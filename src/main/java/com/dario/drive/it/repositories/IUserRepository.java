package com.dario.drive.it.repositories;

import com.dario.drive.it.models.de.UserDE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface IUserRepository extends JpaRepository<UserDE, Long> {

    boolean existsByDni(Long dni);

    Optional<UserDE> findByDni(Long dni);
}
