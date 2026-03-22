package com.takima.race.race.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.takima.race.race.entities.Race;
import com.takima.race.race.repositories.RaceRepository;
import com.takima.race.runner.repositories.RegistrationRepository;

@Service
public class RaceService {

    private final RaceRepository raceRepository;
    private final RegistrationRepository registrationRepository;

    public RaceService(RaceRepository raceRepository, RegistrationRepository registrationRepository) {
        this.raceRepository = raceRepository;
        this.registrationRepository = registrationRepository;
    }

    public List<Race> getAll(String location) {
        if (location != null) {
            return raceRepository.findByLocation(location);
        }
        return raceRepository.findAll();
    }

    public Race getById(Long id) {
        return raceRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Race %s not found", id)
                )
        );
    }

    public Race create(Race race) {
        return raceRepository.save(race);
    }

    public long countParticipants(Long raceId) {

        // vérifier que la race existe
        raceRepository.findById(raceId).orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Race %s not found", raceId)
                )
        );
        //return 0; //TODO: implémenter la logique pour compter les participants à une course
        return registrationRepository.countByRaceId(raceId);
    }

    public Race update(Long id, Race race) {

        Race existing = raceRepository.findById(id).orElseThrow(() ->
            new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("Race %s not found", id)
            )
        );

        existing.setName(race.getName());
        existing.setDate(race.getDate());
        existing.setLocation(race.getLocation());
        existing.setMaxParticipants(race.getMaxParticipants());

        return raceRepository.save(existing);
    }
}