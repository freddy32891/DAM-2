CREATE USER flightsusr@localhost IDENTIFIED by 'flightspwd';
CREATE DATABASE flightsdb
    DEFAULT CHARACTER SET utf8
    DEFAULT COLLATE utf8_general_ci;

GRANT SELECT, INSERT, UPDATE, DELETE ON flightsdb.* TO flightsusr@localhost;
USE flightsdb;

CREATE TABLE flights (
    id INT (4) NOT NULL AUTO_INCREMENT,
    code VARCHAR (10) NOT NULL UNIQUE,
    capacity int (2) DEFAULT 0,
    date DATE NOT NULL,
    time TIME NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE passengers (
    id INT (4) NOT NULL AUTO_INCREMENT,
    name VARCHAR (20),
    phone INT (10) NOT NULL UNIQUE,
    minor BOOLEAN DEFAULT true,
    PRIMARY KEY (id)
);

CREATE TABLE flightpassenger(
    flight_id INT (4) NOT NULL,
    passenger_id INT (4) NOT NULL,
    PRIMARY KEY (flight_id, passenger_id)
);

ALTER TABLE flightspassengers ADD FOREIGN KEY fk_flight (flight_id) REFERENCES flights(id);
ALTER TABLE flightspassengers ADD FOREIGN KEY fk_passenger (passenger_id) REFERENCES passengers(id);

INSERT INTO `flights` ( `code`, `capacity`, `date`, `time`) 
VALUES
('code01', 5, '2019/01/30', '20:01'),
('code02', 3,'2023/02/20','10:12'),
('code03', 5,'2023/02/20','12:34'),
('code04', 9,'2023/02/20','15:56');

INSERT INTO passengers (name,phone,minor)
 VALUES ('Sasha','667132059',true),
('Kim','655636612',false),
('Karyn','680845315',true),
('Rina','664124494',false),
('Nash','670549023',true),
('Merritt','643670201',true),
('Mikayla','690620462',true),
('Fletcher','646876145',true),
('Jonas','640106475',true),
('Igor','609951863',true);

INSERT INTO flightpassenger (flight_id,passenger_id)
values
(4,6),
(4,10),
(4,8),
(2,10),
(4,1),
(2,9),
(1,4),
(4,2),
(4,5),
(1,5),
(3,6),
(2,4),
(1,6);