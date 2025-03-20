CREATE TABLE `teams` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKa510no6sjwqcx153yd5sm4jrr` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `drivers` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `team_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKcay8hmpefrncg4apmusc0mkth` (`team_id`),
  CONSTRAINT `FKcay8hmpefrncg4apmusc0mkth` FOREIGN KEY (`team_id`) REFERENCES `teams` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `races` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `race_participants` (
  `race_id` bigint NOT NULL,
  `driver_id` bigint NOT NULL,
  KEY `FKdsbcqx36dhvv2eadqk3i67eyu` (`driver_id`),
  KEY `FK8rntitvjnh5vfacaj7afrhcul` (`race_id`),
  CONSTRAINT `FK8rntitvjnh5vfacaj7afrhcul` FOREIGN KEY (`race_id`) REFERENCES `races` (`id`),
  CONSTRAINT `FKdsbcqx36dhvv2eadqk3i67eyu` FOREIGN KEY (`driver_id`) REFERENCES `drivers` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;