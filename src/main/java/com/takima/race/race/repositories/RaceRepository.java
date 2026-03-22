package com.takima.race.race.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.takima.race.race.entities.Race;

@Repository
public interface RaceRepository extends JpaRepository<Race, Long> {

    public Race findByName(String name);
    List<Race> findByLocation(String location);
}