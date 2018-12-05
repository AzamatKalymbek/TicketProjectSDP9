set character_set_server='utf8';
create database ticket_core_db
  DEFAULT CHARACTER SET utf8
  DEFAULT COLLATE utf8_general_ci;

create user ticket_core_dbadmin identified by 'P@ssw0rd';
grant all on ticket_core_db.* to 'ticket_core_dbadmin'@'localhost' identified by 'P@ssw0rd';

use ticket_core_db;

CREATE TABLE IF NOT EXISTS  ticket_core_db.`category` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `is_disabled` TINYINT(1) DEFAULT '0',
  PRIMARY KEY ( `id` )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS ticket_core_db.`role` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(100) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY ( `id` ),
  UNIQUE (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS ticket_core_db.`user` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(256) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `category_id` INT(11) NOT NULL,
  `role_id` INT(11) NOT NULL,
  `is_disabled` TINYINT(1) DEFAULT '0',
  `is_verified` TINYINT(1) DEFAULT '0',
  PRIMARY KEY ( `id` ),
  FOREIGN KEY (`category_id`) REFERENCES `category`(`id`),
  FOREIGN KEY (`role_id`) REFERENCES `role`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS ticket_core_db.`ticket_status` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(100) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY ( `id` ),
  UNIQUE (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS ticket_core_db.`ticket` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `category_id` INT(11) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `text` text DEFAULT NULL,
  `ticket_status_id` INT(11) NOT NULL,
  `created_at` date DEFAULT NULL,
  PRIMARY KEY ( `id` ),
  FOREIGN KEY (`category_id`) REFERENCES `category`(`id`),
  FOREIGN KEY (`ticket_status_id`) REFERENCES `ticket_status`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS ticket_core_db.`offer_status` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(100) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY ( `id` ),
  UNIQUE (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS ticket_core_db.`offer` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `ticket_id` INT(11) NOT NULL,
  `user_id` INT(11) NOT NULL,
  `offer_status_id` INT(11) NOT NULL,
  PRIMARY KEY ( `id` ),
  FOREIGN KEY (`ticket_id`) REFERENCES `ticket`(`id`),
  FOREIGN KEY (`user_id`) REFERENCES `user`(`id`),
  FOREIGN KEY (`offer_status_id`) REFERENCES `offer_status`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS ticket_core_db.`offer_user` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `offer_id` INT(11) NOT NULL,
  `user_id` INT(11) NOT NULL,
  `duration` INT DEFAULT NULL,
  `price` decimal(11,3) DEFAULT NULL,
  PRIMARY KEY ( `id` ),
  FOREIGN KEY (`offer_id`) REFERENCES `offer`(`id`),
  FOREIGN KEY (`user_id`) REFERENCES `user`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS ticket_core_db.`ticket_message` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `ticket_id` INT(11) NOT NULL,
  `sender_id` INT(11) NOT NULL,
  `text` text DEFAULT NULL,
  `created_at` date DEFAULT NULL,
  PRIMARY KEY ( `id` ),
  FOREIGN KEY (`ticket_id`) REFERENCES `ticket`(`id`),
  FOREIGN KEY (`sender_id`) REFERENCES `user`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


ALTER TABLE user ADD username varchar(100) NOT NULL after email;
CREATE UNIQUE INDEX user_username_uindex ON user (username);