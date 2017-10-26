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

INSERT INTO Ville (idVille, nomVille, departement) VALUES (1, 'Paris',75);
INSERT INTO Ville (idVille, nomVille, departement) VALUES (2, 'Lyon',69);
INSERT INTO Ville (idVille, nomVille, departement) VALUES (3, 'Marseille',13);
INSERT INTO Ville (idVille, nomVille, departement) VALUES (4, 'Orleans',45);
INSERT INTO Ville (idVille, nomVille, departement) VALUES (5, 'Nantes',44);
INSERT INTO Ville (idVille, nomVille, departement) VALUES (6, 'Lille',59);
INSERT INTO Ville (idVille, nomVille, departement) VALUES (7, 'Peta-aux-chnoques Les Bains',39);