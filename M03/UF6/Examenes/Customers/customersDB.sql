/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  mati
 * Created: 01-mar-2021
 */

CREATE USER customerssusr@localhost IDENTIFIED by 'customerspwd';
CREATE DATABASE customersdb
    DEFAULT CHARACTER SET utf8
    DEFAULT COLLATE utf8_general_ci;

GRANT SELECT, INSERT, UPDATE, DELETE ON customersdb.* TO customerssusr@localhost;

USE customersdb;


CREATE TABLE customers (
    id INT (4) NOT NULL AUTO_INCREMENT,
    name VARCHAR (20),
    phone INT (10) NOT NULL UNIQUE,
    year_discharge INT (4),
    PRIMARY KEY (id)
);

INSERT INTO customers (name,phone,year_discharge)
 VALUES ('Sasha','667132059',2001),
('Kim','655636612',2010),
('Karyn','680845315',2013),
('Pepe','664124494',2005),
('Pepe','670549023',2009),
('Merritt','643670201',2019),
('Mikayla','690620462',2010),
('Kim','646876145',2020),
('Pepe','640106475',2007),
('Igor','609951863',2004);


