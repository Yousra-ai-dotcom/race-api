package com.takima.race.runner.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.takima.race.race.entities.Race;
import com.takima.race.race.repositories.RaceRepository;
import com.takima.race.runner.entities.Registration;
import com.takima.race.runner.entities.Runner;
import com.takima.race.runner.repositories.RegistrationRepository;
import com.takima.race.runner.repositories.RunnerRepository;

@Service
public class RegistrationService {

    private final RegistrationRepository registrationRepository;
    private final RunnerRepository runnerRepository;
    private final RaceRepository raceRepository;

    public RegistrationService(
            RegistrationRepository registrationRepository,
            RunnerRepository runnerRepository,
            RaceRepository raceRepository
    ) {
        this.registrationRepository = registrationRepository;
        this.runnerRepository = runnerRepository;
        this.raceRepository = raceRepository;
    }

    // 🔥 inscription
    public Registration register(Long raceId, Long runnerId) {

        Runner runner = runnerRepository.findById(runnerId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Race race = raceRepository.findById(raceId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (registrationRepository.existsByRunnerIdAndRaceId(runnerId, raceId)) {
        throw new ResponseStatusException(HttpStatus.CONFLICT, "Already registered");
        }

        long count = registrationRepository.countByRaceId(raceId);
        if (count >= race.getMaxParticipants()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Race full");
        }

        Registration registration = new Registration();
        registration.setRunner(runner);
        registration.setRace(race);
        registration.setRegistrationDate(LocalDate.now());

        return registrationRepository.save(registration);
    }

    public List<Registration> getParticipants(Long raceId) {
        return registrationRepository.findByRaceId(raceId);
    }

    public List<Registration> getRacesByRunner(Long runnerId) {
        return registrationRepository.findByRunnerId(runnerId);
    }
}