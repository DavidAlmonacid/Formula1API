ALTER TABLE races
MODIFY date date NOT NULL UNIQUE,
MODIFY name varchar(255) NOT NULL UNIQUE;