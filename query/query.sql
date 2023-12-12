
INSERT INTO level (id,description, points)
Values (2112987197,'Coastal Quest', 10),
       (2112987197283,'River Rumble',50),
       (2112987111,'Deep Sea Expedition', 100);


INSERT INTO fish (name, average_weight, level_id)
VALUES ('Goldfish', 0.5, 1), -- Average weight in kilograms
       ('Guppy', 0.3, 1),
       ('Tetra', 0.2, 1),
       ('Catfish', 5, 2),
       ('Rainbowfish', 0.8, 2),
       ('Swordtail', 0.6, 2),
       ('Barracuda', 15, 3),
       ('Tuna', 25, 3),
       ('Marlin', 30, 3);