package com.takima.race.race.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.takima.race.race.entities.Race;
import com.takima.race.race.services.RaceService;

@RestController
@RequestMapping("/races")
public class RaceController {

    private final RaceService raceService;

    public RaceController(RaceService raceService) {
        this.raceService = raceService;
    }

    @GetMapping
    public List<Race> getAll(@RequestParam(required = false) String location) {
        return raceService.getAll(location);
    }
    @PutMapping("/{id}")
    public Race update(
        @PathVariable Long id,
        @RequestBody Race race
        ) {
            return raceService.update(id, race);
    }

    @GetMapping("/{id}")
    public Race getById(@PathVariable Long id) {
        return raceService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Race create(
        @RequestBody Race race
    ) {
        return raceService.create(race);
    }

    // 🔥 endpoint demandé dans le TP
    @GetMapping("/{raceId}/participants/count")
    public Map<String, Long> countParticipants(@PathVariable Long raceId) {
        long count = raceService.countParticipants(raceId);
        return Map.of("count", count);
    }
}