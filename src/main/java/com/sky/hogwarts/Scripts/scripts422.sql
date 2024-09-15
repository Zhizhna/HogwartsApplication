CREATE TABLE Person (
    id BIGINT PRIMARY KEY AUTOINCREMENT,
    name VARCHAR(255) NOT NULL,
    age INT CHECK (age >= 17),
    has_license BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE Car (
    id BIGINT PRIMARY KEY AUTOINCREMENT,
    brand VARCHAR(255) NOT NULL,
    model VARCHAR(255) NOT NULL,
    price DECIMAL(10, 2) NOT NULL
);


CREATE TABLE PersonCar (
    person_id BIGINT,
    car_id BIGINT,
    PRIMARY KEY (person_id, car_id),
    FOREIGN KEY (person_id) REFERENCES Person(id),
    FOREIGN KEY (car_id) REFERENCES Car(id)
);