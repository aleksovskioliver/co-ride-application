CREATE TABLE locations
(
    id      BIGSERIAL PRIMARY KEY,
    city    VARCHAR(255) NOT NULL,
    lat     DOUBLE PRECISION,
    lng     DOUBLE PRECISION,
    country VARCHAR(255)
);

CREATE TABLE vehicles
(
    id    BIGSERIAL PRIMARY KEY,
    model VARCHAR(255) NOT NULL,
    make  VARCHAR(255) NOT NULL,
    seats INTEGER
);
CREATE TABLE users
(
    id           BIGSERIAL PRIMARY KEY,
    first_name   VARCHAR(255) NOT NULL,
    last_name    VARCHAR(255) NOT NULL,
    phone_number VARCHAR(20)  NOT NULL,
    email        VARCHAR(255) NOT NULL,
    password     VARCHAR(255) NOT NULL,
    role         VARCHAR(50)  NOT NULL,
    vehicle_id   BIGINT,
    CONSTRAINT fk_vehicle FOREIGN KEY (vehicle_id) REFERENCES vehicles (id)
);

CREATE TABLE reservations
(
    id               BIGSERIAL PRIMARY KEY,
    driver_id        BIGINT      NOT NULL,
    start_time       TIMESTAMP   NOT NULL,
    end_time         TIMESTAMP   NOT NULL,
    pickup_location  BIGINT      NOT NULL,
    dropout_location BIGINT      NOT NULL,
    trip_cost        INT         NOT NULL,
    status           VARCHAR(50) NOT NULL,
    available_seats  INT         NOT NULL,
    CONSTRAINT fk_driver FOREIGN KEY (driver_id) REFERENCES users (id),
    CONSTRAINT fk_pickup_location FOREIGN KEY (pickup_location) REFERENCES locations (id),
    CONSTRAINT fk_dropout_location FOREIGN KEY (dropout_location) REFERENCES locations (id)
);

CREATE TABLE reservation_customers
(
    reservation_id BIGINT NOT NULL,
    customer_id    BIGINT NOT NULL,
    CONSTRAINT pk_reservation_customers PRIMARY KEY (reservation_id, customer_id),
    CONSTRAINT fk_reservation_customers_reservation FOREIGN KEY (reservation_id) REFERENCES reservations (id),
    CONSTRAINT fk_reservation_customers_customer FOREIGN KEY (customer_id) REFERENCES users (id)
);

CREATE TABLE user_reservations
(
    user_id        BIGINT NOT NULL,
    reservation_id BIGINT NOT NULL,
    CONSTRAINT pk_user_reservations PRIMARY KEY (user_id, reservation_id),
    CONSTRAINT fk_user_reservations_user FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT fk_user_reservations_reservation FOREIGN KEY (reservation_id) REFERENCES reservations (id)
);



