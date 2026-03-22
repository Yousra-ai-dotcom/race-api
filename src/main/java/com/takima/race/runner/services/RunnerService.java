package com.takima.race.runner.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.takima.race.runner.entities.Runner;
import com.takima.race.runner.repositories.RunnerRepository;

@Service
public class RunnerService {

    private final RunnerRepository runnerRepository;

    public RunnerService(RunnerRepository runnerRepository) {
        this.runnerRepository = runnerRepository;
    }

    public List<Runner> getAll() {
        return runnerRepository.findAll();
    }

    public Runner getById(Long id) {
        return runnerRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Runner %s not found", id)
                )
        );
    }

    public Runner create(Runner runner) {
        if (runner.getEmail() == null || !runner.getEmail().contains("@")) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Invalid email"
                );
            }
        return runnerRepository.save(runner);
    }

    public void delete (Long id){
        runnerRepository.deleteById(id);
    }


    public Runner update(Long id, Runner runner) {

    Runner existing = runnerRepository.findById(id).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND)
    );

    if (runner.getEmail() == null || !runner.getEmail().contains("@")) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid email");
    }

    existing.setFirstName(runner.getFirstName());
    existing.setLastName(runner.getLastName());
    existing.setEmail(runner.getEmail());
    existing.setAge(runner.getAge());

    return runnerRepository.save(existing);
}
}
