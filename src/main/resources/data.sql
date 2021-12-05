DROP TABLE IF EXISTS room;

CREATE table IF NOT EXISTS room(
    id INT PRIMARY KEY NOT NULL,
    room_number VARCHAR(100) NOT NULL,
    capacity INT NOT NULL,
    specification_ecran TINYINT(2) NOT NULL,
    specification_pieuvre TINYINT(2) NOT NULL,
    specification_tableau TINYINT(2) NOT NULL,
    specification_webcam TINYINT(2) NOT NULL
);


DROP TABLE IF EXISTS reservation;

CREATE table IF NOT EXISTS reservation(
    id INT PRIMARY KEY NOT NULL,
    type VARCHAR(100) NOT NULL,
    hour_reservation_start DATE NOT NULL,
    hour_reservation_end DATE NOT NULL,
    people_number int NOT NULL,
    fk_room_id INT NOT NULL,
    foreign key (fk_room_id) references room(id)
);

INSERT INTO room VALUES (1,'E1001',23,0,0,0,0),
                        (2,'E1002',10,1,0,0,0),
                        (3,'E1003',8,0,1,0,0),
                        (4,'E1004',4,0,0,1,0),
                        (5,'E2001',4,0,0,0,0),
                        (6,'E2002',15,1,0,0,1),
                        (7,'E2003',7,0,0,0,0),
                        (8,'E2004',9,0,0,1,0),
                        (9,'E3001',13,1,1,0,1),
                        (10,'E3002',8,0,0,0,0),
                        (11,'E3003',9,1,1,0,0),
                        (12,'E3004',4,0,0,0,0);
