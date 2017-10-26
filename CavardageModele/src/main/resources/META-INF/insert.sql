INSERT INTO Role ( idRole, message) VALUES (1,'admin');
INSERT INTO Role ( idRole, message) VALUES (2,'utilisateur');

INSERT INTO Utilisateur (login, nom, motDePasse, idRole) VALUES ('admin','admin','admin',1);
INSERT INTO Utilisateur (login, nom, motDePasse, idRole) VALUES ('utilisateur1','utilisateur2','utilisateur',2);
INSERT INTO Utilisateur (login, nom, motDePasse, idRole) VALUES ('utilisateur2','utilisateur2','utilisateur',2);

INSERT INTO Gabarit (idGabarit, type) VALUES (1,'coupé');
INSERT INTO Gabarit (idGabarit, type) VALUES (2,'familliale');
INSERT INTO Gabarit (idGabarit, type) VALUES (3,'cabriolet');
INSERT INTO Gabarit (idGabarit, type) VALUES (4,'citadine');
INSERT INTO Gabarit (idGabarit, type) VALUES (5,'combi VW');
INSERT INTO Gabarit (idGabarit, type) VALUES (6,'semi-remorque');

INSERT INTO Ville (idVille, nomVille) VALUES (1, 'Paris');
INSERT INTO Ville (idVille, nomVille) VALUES (2, 'Lyon');
INSERT INTO Ville (idVille, nomVille) VALUES (3, 'Marseille');
INSERT INTO Ville (idVille, nomVille) VALUES (4, 'Orleans');
INSERT INTO Ville (idVille, nomVille) VALUES (5, 'Nantes');
INSERT INTO Ville (idVille, nomVille) VALUES (6, 'Lille');
INSERT INTO Ville (idVille, nomVille) VALUES (7, 'Peta-aux-chnoques Les Bains');