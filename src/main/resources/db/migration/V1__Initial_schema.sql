CREATE TABLE teams (
  id bigint AUTO_INCREMENT PRIMARY KEY,
  name varchar(255) NOT NULL UNIQUE,
  full_name varchar(255) NOT NULL UNIQUE
);

CREATE TABLE drivers (
  id bigint AUTO_INCREMENT PRIMARY KEY,
  name varchar(255) NOT NULL,
  team_id bigint
);

CREATE TABLE races (
  id bigint AUTO_INCREMENT PRIMARY KEY,
  date date,
  name varchar(255)
);

CREATE TABLE race_participants (
  race_id bigint NOT NULL,
  driver_id bigint NOT NULL
);