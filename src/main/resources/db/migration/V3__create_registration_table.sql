CREATE TABLE registration (
    id BIGSERIAL PRIMARY KEY,
    runner_id INTEGER NOT NULL,
    race_id INTEGER NOT NULL,
    registration_date DATE NOT NULL,

    CONSTRAINT fk_runner
        FOREIGN KEY (runner_id)
        REFERENCES runner(id),

    CONSTRAINT fk_race
        FOREIGN KEY (race_id)
        REFERENCES race(id),

    CONSTRAINT unique_registration
        UNIQUE (runner_id, race_id)
);