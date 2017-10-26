INSERT INTO Role ( idRole, message) VALUES (1,'admin');
INSERT INTO Role ( idRole, message) VALUES (2,'utilisateur');
INSERT INTO Role ( idRole, message) VALUES (3,'utilisateur');

INSERT INTO Utilisateur (login, nom, motDePasse, idRole) VALUES ('admin','admin','admin',1);
INSERT INTO Utilisateur (login, nom, motDePasse, idRole) VALUES ('utilisateur1','utilisateur2','utilisateur',2);
INSERT INTO Utilisateur (login, nom, motDePasse, idRole) VALUES ('utilisateur2','utilisateur2','utilisateur',3);

INSERT INTO Gabarit (idGabarit, type) VALUES (0,'coupé');
INSERT INTO Gabarit (idGabarit, type) VALUES (1,'familliale');
INSERT INTO Gabarit (idGabarit, type) VALUES (2,'cabriolet');
INSERT INTO Gabarit (idGabarit, type) VALUES (3,'citadine');
INSERT INTO Gabarit (idGabarit, type) VALUES (4,'combi VW');
INSERT INTO Gabarit (idGabarit, type) VALUES (5,'semi-remorque');

INSERT INTO Ville (idVille, nomVille) VALUES (0, 'Paris');
INSERT INTO Ville (idVille, nomVille) VALUES (1, 'Lyon');
INSERT INTO Ville (idVille, nomVille) VALUES (2, 'Marseille');
INSERT INTO Ville (idVille, nomVille) VALUES (3, 'Orleans');
INSERT INTO Ville (idVille, nomVille) VALUES (4, 'Nantes');
INSERT INTO Ville (idVille, nomVille) VALUES (5, 'Lille');
INSERT INTO Ville (idVille, nomVille) VALUES (6, 'Peta-aux-chnoques Les Bains');