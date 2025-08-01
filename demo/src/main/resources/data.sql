-- Insertion des personnages
INSERT INTO character (name, race, level) VALUES ('Dragonborn', 'Nordique', 50);
INSERT INTO character (name, race, level) VALUES ('Lydia', 'Nordique', 30);

-- Insertion des quêtes
INSERT INTO quest (title, description, difficulty, reward, character_id) 
VALUES ('Tuer le dragon Alduin', 'Vaincre le dragon Alduin pour sauver Tamriel', 'difficile', 'Épée Draconique', 1);

INSERT INTO quest (title, description, difficulty, reward, character_id) 
VALUES ('Livrer message à Jarl Balgruuf', 'Apporter un message important au Jarl de Blancherive', 'facile', '100 pièces d''or', 2);
