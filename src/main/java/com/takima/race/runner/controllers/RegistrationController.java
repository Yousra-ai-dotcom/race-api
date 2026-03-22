package com.takima.race.runner.controllers;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.takima.race.runner.services.RegistrationService;

@RestController
@RequestMapping("/races")
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    // ✅ Inscrire un runner
    @PostMapping("/{raceId}/registrations")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(
            @PathVariable Long raceId,
            @RequestBody Map<String, Long> body
    ) {
        Long runnerId = body.get("runnerId");
        registrationService.register(raceId, runnerId);
    }

    // ✅ Get participants
    @GetMapping("/{raceId}/registrations")
    public Object getParticipants(@PathVariable Long raceId) {
        return registrationService.getParticipants(raceId);
    }
}