package com.takima.race.runner.entities;

import java.time.LocalDate;
import java.util.Objects;

import com.takima.race.race.entities.Race;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Runner runner;

    @ManyToOne
    private Race race;

    private LocalDate registrationDate;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Registration that = (Registration) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    // ===== GETTERS =====
    public Long getId() { return id; }
    public Runner getRunner() { return runner; }
    public Race getRace() { return race; }
    public LocalDate getRegistrationDate() { return registrationDate; }

    // ===== SETTERS =====
    public void setId(Long id) { this.id = id; }
    public void setRunner(Runner runner) { this.runner = runner; }
    public void setRace(Race race) { this.race = race; }
    public void setRegistrationDate(LocalDate registrationDate) { this.registrationDate = registrationDate; }
}