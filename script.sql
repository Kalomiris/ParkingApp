CREATE TABLE vehicles
(
  number_plate VARCHAR(50) NOT NULL
    CONSTRAINT vehicles_number_plate_pk
    PRIMARY KEY,
  type_vehicle VARCHAR(20),
  owner_id     VARCHAR(20)
);

CREATE TABLE parkpositions
(
  vehicle_id  VARCHAR(50)
    CONSTRAINT parkpositions_vehicles_number_plate_fk
    REFERENCES vehicles,
  time        VARCHAR,
  employee_id VARCHAR(20),
  index       INTEGER
);

CREATE UNIQUE INDEX parkpositions_vehicle_id_uindex
  ON parkpositions (vehicle_id);

CREATE TABLE owners
(
  id         VARCHAR(50) NOT NULL
    CONSTRAINT owner_id_pk
    PRIMARY KEY,
  first_name VARCHAR(50),
  last_name  VARCHAR(20)
);

ALTER TABLE vehicles
  ADD CONSTRAINT vehicles_owners_id_fk
FOREIGN KEY (owner_id) REFERENCES owners;

CREATE TABLE employees
(
  first_name VARCHAR(20),
  last_name  VARCHAR(20),
  shift      INTEGER,
  id         VARCHAR(20) NOT NULL
    CONSTRAINT employees_id_pk
    PRIMARY KEY
);

CREATE UNIQUE INDEX employees_id_uindex
  ON employees (id);


