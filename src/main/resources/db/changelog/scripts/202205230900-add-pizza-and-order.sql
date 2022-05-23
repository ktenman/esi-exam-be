--liquibase formatted sql

--changeset Konstantin:1
CREATE SEQUENCE IF NOT EXISTS seq_pizza;

CREATE TABLE IF NOT EXISTS pizza
(
    id   BIGINT  NOT NULL
        CONSTRAINT pizza_pkey
            PRIMARY KEY,
    name VARCHAR NOT NULL,
    type VARCHAR
);

ALTER SEQUENCE IF EXISTS seq_pizza
    OWNED BY pizza.id;

CREATE SEQUENCE IF NOT EXISTS seq_orders;

CREATE TABLE IF NOT EXISTS orders
(
    id         BIGINT    NOT NULL
        CONSTRAINT orders_pkey
            PRIMARY KEY,
    ordered_at TIMESTAMP NOT NULL,
    user_id    BIGINT    NOT NULL
        CONSTRAINT fk_orders_users
            REFERENCES users,
    pizza_id    BIGINT    NOT NULL
        CONSTRAINT fk_orders_pizza
            REFERENCES pizza
);

ALTER SEQUENCE IF EXISTS seq_orders
    OWNED BY orders.id;


