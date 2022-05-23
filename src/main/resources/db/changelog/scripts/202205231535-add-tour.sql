--liquibase formatted sql

--changeset Konstantin:2
CREATE SEQUENCE IF NOT EXISTS seq_tour;

CREATE TABLE IF NOT EXISTS tour
(
    id         BIGINT    NOT NULL
        CONSTRAINT tour_pkey
            PRIMARY KEY,
    name       VARCHAR   NOT NULL,
    location   VARCHAR   NOT NULL,
    begin_date TIMESTAMP NOT NULL,
    end_date   TIMESTAMP NOT NULL,
    status VARCHAR   NOT NULL
);

ALTER SEQUENCE IF EXISTS seq_tour
    OWNED BY tour.id;

CREATE TABLE IF NOT EXISTS user_tour
(
    user_id BIGINT NOT NULL
        CONSTRAINT fk_user_tours_user_id
            REFERENCES users,
    tour_id BIGINT NOT NULL
        CONSTRAINT fk_user_tours_tour_id
            REFERENCES tour,
    PRIMARY KEY (user_id, tour_id)
);


