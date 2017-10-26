INSERT INTO Role ( idRole, message) VALUES (1,'admin');
INSERT INTO Role ( idRole, message) VALUES (2,'utilisateur');

INSERT INTO Utilisateur (login, nom, motDePasse, idRole) VALUES ('admin','admin','admin',1);
INSERT INTO Utilisateur (login, nom, motDePasse, idRole) VALUES ('bob','bob','bob',2);
INSERT INTO Utilisateur (login, nom, motDePasse, idRole) VALUES ('alice','alice','alice',2);
INSERT INTO Utilisateur (login, nom, motDePasse, idRole) VALUES ('charlie','charlie','charlie',2);

INSERT INTO Gabarit (idGabarit, type) VALUES (1,'coupé');
INSERT INTO Gabarit (idGabarit, type) VALUES (2,'familliale');
INSERT INTO Gabarit (idGabarit, type) VALUES (3,'cabriolet');
INSERT INTO Gabarit (idGabarit, type) VALUES (4,'citadine');
INSERT INTO Gabarit (idGabarit, type) VALUES (5,'combi VW');
INSERT INTO Gabarit (idGabarit, type) VALUES (6,'semi-remorque');
INSERT INTO Gabarit (idGabarit, type) VALUES (7,'monospace');

INSERT INTO Ville (idVille, nomVille, departement) VALUES (1, 'Paris',75);
INSERT INTO Ville (idVille, nomVille, departement) VALUES (2, 'Lyon',69);
INSERT INTO Ville (idVille, nomVille, departement) VALUES (3, 'Marseille',13);
INSERT INTO Ville (idVille, nomVille, departement) VALUES (4, 'Orleans',45);
INSERT INTO Ville (idVille, nomVille, departement) VALUES (5, 'Nantes',44);
INSERT INTO Ville (idVille, nomVille, departement) VALUES (6, 'Lille',59);
INSERT INTO Ville (idVille, nomVille, departement) VALUES (7, 'Peta-aux-chnoques Les Bains',39);
INSERT INTO Ville (idVille, nomVille, departement) VALUES (8, 'Amiens',80);

INSERT INTO Vehicule (idVehicule,modele,nombrePlaces,nom,gabarit_idGabarit) VALUES (1,'Espace',6,'titine',7);

INSERT INTO Utilisateur_vehicule(utilisateur_login,listevehicule_idvehicule) VALUES ('bob',1);


INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_idville,villedepart_idville) VALUES (1,'21/12/2017','12','aVenir',50,1,6,4);

INSERT INTO Etape (idEtape,prix,trajet_idtrajet,villeetape_idville) VALUES (1,10,1,1);
INSERT INTO Etape (idEtape,prix,trajet_idtrajet,villeetape_idville) VALUES (2,30,1,8);

INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (1,1,'accepte',null,1,'alice');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (2,2,'enAttente',2,1,'charlie');
