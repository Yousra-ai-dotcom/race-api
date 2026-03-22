package com.takima.race.runner.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.takima.race.runner.entities.Registration;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {

    long countByRaceId(Long raceId);

    boolean existsByRunnerIdAndRaceId(Long runnerId, Long raceId);

    List<Registration> findByRaceId(Long raceId);

    List<Registration> findByRunnerId(Long runnerId);
}