/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  mati
 * Created: 18-ene-2021
 */
CREATE USER 'usrstore'@'localhost' IDENTIFIED BY 'pswstore';
-- Create database.
CREATE DATABASE dbstore
  DEFAULT CHARACTER SET utf8
  DEFAULT COLLATE utf8_general_ci;
-- Grant permissions.
GRANT SELECT, INSERT, UPDATE, DELETE ON dbstore.* TO 'usrstore'@'localhost';
-- Use database.
USE dbstore;
-- Create table 'countries'
CREATE TABLE `products` (
`id` INT(4) NOT NULL AUTO_INCREMENT,
`code` VARCHAR(15) NOT NULL UNIQUE,
`description` VARCHAR(128) DEFAULT NULL,
`price` DOUBLE DEFAULT 0.0,
`stock` INT DEFAULT 0,
PRIMARY KEY (`id`)
) ENGINE=InnoDB;
INSERT INTO `products` (`id`, `code`, `description`, `price`, `stock`) 
VALUES
(1, 'code01', 'desc01', 2874, 73),
(2, 'code02', 'desc02', 357, 77),
(3, 'code03', 'desc03', 453, 78),
(4, 'code04', 'desc04', 859, 77),
(5, 'code05', 'desc05', 2600, 68),
(6, 'code06', 'desc06', 528, 78),
(7, 'code07', 'desc07', 5129,56),
(8, 'code08', 'desc08', 994, 70),
(9, 'code09', 'desc09', 1610, 73),
(10, 'code10', 'desc10', 4493,76);
