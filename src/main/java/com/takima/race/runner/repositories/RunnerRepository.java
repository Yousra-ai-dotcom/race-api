package com.takima.race.runner.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.takima.race.runner.entities.Runner;

@Repository
public interface RunnerRepository extends JpaRepository<Runner, Long> {
    
    public Runner findByFirstName(String firstName);
}