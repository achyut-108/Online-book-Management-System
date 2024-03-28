CREATE TABLE `author` (
  `id` int NOT NULL AUTO_INCREMENT,
  `author_name` varchar(150) NOT NULL,
  `author_description` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`,`author_name`),
  UNIQUE KEY `author_name_UNIQUE` (`author_name`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `category_name` varchar(150) NOT NULL,
  `category_description` varchar(1000) NOT NULL,
  PRIMARY KEY (`id`,`category_name`),
  UNIQUE KEY `category_name_UNIQUE` (`category_name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `book` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `book_name` varchar(150) NOT NULL,
  `author_id` int NOT NULL,
  `category_id` int NOT NULL,
  `edition` int NOT NULL,
  PRIMARY KEY (`id`,`book_name`,`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `book_detail` (
  `detail_id` bigint NOT NULL AUTO_INCREMENT,
  `book_id` bigint NOT NULL,
  `num_of_copies` int NOT NULL,
  `issued_copies` int DEFAULT NULL,
  `published_date` datetime DEFAULT NULL,
  `price` decimal(20,2) NOT NULL,
  `isbn` varchar(200) NOT NULL,
  `available_for_issue` varchar(1) NOT NULL DEFAULT 'Y',
  PRIMARY KEY (`detail_id`,`book_id`),
  UNIQUE KEY `book_id_UNIQUE` (`book_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `book_order` (
  `order_id` varchar(200) NOT NULL,
  `book_id` bigint NOT NULL,
  `customer_id` varchar(200) NOT NULL,
  `issue_date` datetime NOT NULL,
  `required_return_date` datetime NOT NULL,
  `num_of_copies_issued` int NOT NULL,
  `request_status` varchar(10) NOT NULL DEFAULT 'PENDING',
  `return_status` varchar(10) DEFAULT 'PENDING',
  `actual_return_date` datetime DEFAULT NULL,
  `fine` double DEFAULT NULL,
  `requested_date` datetime NOT NULL,
  PRIMARY KEY (`order_id`,`issue_date`,`book_id`,`customer_id`),
  UNIQUE KEY `order_id_UNIQUE` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `` (`id`,`author_name`,`author_description`) VALUES (1,'B.S. Grewal',NULL);
INSERT INTO `` (`id`,`author_name`,`author_description`) VALUES (5,'H.C. Verma',NULL);
INSERT INTO `` (`id`,`author_name`,`author_description`) VALUES (6,'Morris Mano',NULL);
INSERT INTO `` (`id`,`author_name`,`author_description`) VALUES (8,'Robin Sharma','Famous author of Monk who sold his ferrari');
INSERT INTO `` (`id`,`author_name`,`author_description`) VALUES (9,'S Sharma',NULL);

INSERT INTO `` (`id`,`category_name`,`category_description`) VALUES (1,'Engineering','Related to any field of Engineering');
INSERT INTO `` (`id`,`category_name`,`category_description`) VALUES (2,'Mathematics','Related to any field of Mathematics');
INSERT INTO `` (`id`,`category_name`,`category_description`) VALUES (3,'Physics','Related to any field of Physics');

INSERT INTO `` (`id`,`book_name`,`author_id`,`category_id`,`edition`) VALUES (4,'Engineering Mathematics',1,2,1);
INSERT INTO `` (`id`,`book_name`,`author_id`,`category_id`,`edition`) VALUES (7,'Engineering Design',9,1,1);
INSERT INTO `` (`id`,`book_name`,`author_id`,`category_id`,`edition`) VALUES (8,'Concepts Of Physics',5,3,1);

INSERT INTO `` (`detail_id`,`book_id`,`num_of_copies`,`issued_copies`,`published_date`,`price`,`isbn`,`available_for_issue`) VALUES (2,4,1,1,'2024-06-24 05:30:00',780.98,'sdfr4563','Y');
INSERT INTO `` (`detail_id`,`book_id`,`num_of_copies`,`issued_copies`,`published_date`,`price`,`isbn`,`available_for_issue`) VALUES (3,7,1,NULL,'2024-06-24 05:30:00',780.98,'sdfr8563','Y');
INSERT INTO `` (`detail_id`,`book_id`,`num_of_copies`,`issued_copies`,`published_date`,`price`,`isbn`,`available_for_issue`) VALUES (4,8,1,NULL,'2024-06-24 05:30:00',780.98,'sdfr8563','Y');
