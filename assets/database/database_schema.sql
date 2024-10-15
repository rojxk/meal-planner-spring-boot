-- Meal Planner Database Schema
USE meal_planner;

-- Create tables

CREATE TABLE `userdata` (
                            `id` int NOT NULL AUTO_INCREMENT,
                            `username` varchar(50) NOT NULL,
                            `password` char(68) DEFAULT NULL,
                            `enabled` TINYINT,
                            PRIMARY KEY (`id`),
                            UNIQUE KEY `username_unique` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=latin1;

CREATE TABLE `roles` (
                         `username` varchar(50) not null,
                         `role` varchar(50) not null,
                         UNIQUE KEY `roles_idx_1` (`username`,`role`),
                         CONSTRAINT `roles_ibfk_1` FOREIGN KEY (`username`) REFERENCES `userdata` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `measure` (
                           `id` int NOT NULL,
                           `measure` varchar(50) DEFAULT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `category` (
                            `id` int NOT NULL,
                            `category` varchar(50),
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `meal_description` (
                                    `id` int NOT NULL AUTO_INCREMENT,
                                    `meal_description` TEXT DEFAULT NULL,
                                    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `meal` (
                        `id` int NOT NULL AUTO_INCREMENT,
                        `meal_name` varchar(45) DEFAULT NULL,
                        `making_time` int DEFAULT NULL,
                        `portions` int DEFAULT NULL,
                        `description_id` int DEFAULT NULL,
                        `user_id` int DEFAULT NULL,
                        `category_id` int DEFAULT NULL,
                        PRIMARY KEY (`id`),
                        KEY `fk_description` (`description_id`),
                        KEY `fk_user` (`user_id`),
                        KEY `fk_category` (`category_id`),
                        CONSTRAINT `fk_detail` FOREIGN KEY (`description_id`) REFERENCES `meal_description` (`id`),
                        CONSTRAINT `fk_user` FOREIGN KEY (`user_id`) REFERENCES `userdata` (`id`),
                        CONSTRAINT `fk_category` FOREIGN KEY (`category_id`) REFERENCES `category`(`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `ingredient` (
                              `id` int NOT NULL AUTO_INCREMENT,
                              `ingredient` varchar(128) DEFAULT NULL,
                              `quantity` DECIMAL(10, 2) DEFAULT NULL,
                              `meal_id` int DEFAULT NULL,
                              `measure_id` int DEFAULT NULL,
                              PRIMARY KEY (`id`),
                              KEY `fk_meal_idx` (`meal_id`),
                              CONSTRAINT `fk_meal` FOREIGN KEY (`meal_id`) REFERENCES `meal` (`id`),
                              KEY `fk_measure_idx` (`measure_id`),
                              CONSTRAINT `fk_measure` FOREIGN KEY (`measure_id`) REFERENCES `measure`(`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Insert data

INSERT INTO `category` (`id`, `category`) VALUES
                                              (1,'Breakfast'),
                                              (2,'Lunch'),
                                              (3,'Dinner'),
                                              (4,'Snack');

INSERT INTO `measure` (`id`, `measure`) VALUES
                                            (1, 'pcs.'),
                                            (2, 'g'),
                                            (3, 'ml');

-- Inserting user with bcrypted password
INSERT INTO  `userdata` (username, password, enabled)  VALUES
                                                           ('INSERT_USERNAME_HERE','{bcrypt}INSERT_BCRYPT_HASH_HERE', 1);
INSERT INTO `roles`(username, role) VALUES
                                        ('INSERT_USERNAME_HERE', 'ROLE_USER')


