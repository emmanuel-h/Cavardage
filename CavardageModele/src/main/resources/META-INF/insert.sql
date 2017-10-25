INSERT INTO Role ( idRole, message) VALUES (1,'admin');
INSERT INTO Role ( idRole, message) VALUES (2,'utilisateur');
INSERT INTO Role ( idRole, message) VALUES (3,'utilisateur');

INSERT INTO Utilisateur (login, nom, motDePasse, roleUtilisateur) VALUES ('admin','admin','admin',1);
INSERT INTO Utilisateur (login, nom, motDePasse, roleUtilisateur) VALUES ('utilisateur1','utilisateur2','utilisateur',2);
INSERT INTO Utilisateur (login, nom, motDePasse, roleUtilisateur) VALUES ('utilisateur2','utilisateur2','utilisateur',3);