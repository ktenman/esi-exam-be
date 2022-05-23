--liquibase formatted sql

--changeset Konstantin:0
CREATE TABLE IF NOT EXISTS roles
(
    id   SERIAL
        PRIMARY KEY,
    role_type VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS users
(
    id       BIGSERIAL
        PRIMARY KEY,
    email    VARCHAR(50)
        CONSTRAINT uk6dotkott2kjsp8vw4d0m25fb7
            UNIQUE,
    password VARCHAR(120),
    username VARCHAR(20)
        CONSTRAINT ukr43af9ap4edm43mmtq01oddj6
            UNIQUE
);

CREATE TABLE IF NOT EXISTS user_roles
(
    user_id BIGINT  NOT NULL
        CONSTRAINT fkhfh9dx7w3ubf1co1vdev94g3f
            REFERENCES users,
    role_id INTEGER NOT NULL
        CONSTRAINT fkh8ciramu9cc9q3qcqiv4ue8a6
            REFERENCES roles,
    PRIMARY KEY (user_id, role_id)
);

CREATE SEQUENCE IF NOT EXISTS roles_id_seq;

ALTER SEQUENCE roles_id_seq OWNED BY roles.id;

CREATE SEQUENCE IF NOT EXISTS users_id_seq;

ALTER SEQUENCE users_id_seq OWNED BY users.id;


