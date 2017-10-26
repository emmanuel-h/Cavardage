INSERT INTO Role ( idRole, message) VALUES (1,'admin');
INSERT INTO Role ( idRole, message) VALUES (2,'utilisateur');
INSERT INTO Role ( idRole, message) VALUES (3,'utilisateur');

INSERT INTO Utilisateur (login, nom, motDePasse, roleUtilisateur) VALUES ('admin','admin','admin',1);
INSERT INTO Utilisateur (login, nom, motDePasse, roleUtilisateur) VALUES ('utilisateur1','utilisateur2','utilisateur',2);
INSERT INTO Utilisateur (login, nom, motDePasse, roleUtilisateur) VALUES ('utilisateur2','utilisateur2','utilisateur',3);

INSERT INTO Gabarit (idGabarit, type) VALUES (1,'coupé');
INSERT INTO Gabarit (idGabarit, type) VALUES (2,'familliale');
INSERT INTO Gabarit (idGabarit, type) VALUES (3,'cabriolet');
INSERT INTO Gabarit (idGabarit, type) VALUES (4,'citadine');
INSERT INTO Gabarit (idGabarit, type) VALUES (5,'combi VW');
INSERT INTO Gabarit (idGabarit, type) VALUES (6,'semi-remorque');