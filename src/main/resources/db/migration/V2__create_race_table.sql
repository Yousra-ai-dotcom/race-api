CREATE TABLE race (
     id BIGSERIAL PRIMARY KEY,
     name VARCHAR(255) NOT NULL,
     date DATE NOT NULL,
     location VARCHAR(255) NOT NULL,
     max_participants INTEGER NOT NULL
);