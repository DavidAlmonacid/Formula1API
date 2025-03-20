CREATE TABLE teams (
  id bigint AUTO_INCREMENT PRIMARY KEY,
  name varchar(255) NOT NULL UNIQUE
);

CREATE TABLE drivers (
  id bigint AUTO_INCREMENT PRIMARY KEY,
  name varchar(255) NOT NULL,
  team_id bigint,
  FOREIGN KEY (team_id) REFERENCES teams(id)
);

CREATE TABLE races (
  id bigint AUTO_INCREMENT PRIMARY KEY,
  date date,
  name varchar(255)
);

CREATE TABLE race_participants (
  race_id bigint NOT NULL,
  driver_id bigint NOT NULL,
  FOREIGN KEY (driver_id) REFERENCES drivers(id),
  FOREIGN KEY (race_id) REFERENCES races(id)
);