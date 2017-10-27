INSERT INTO Role ( idRole, message) VALUES (1,'admin');
INSERT INTO Role ( idRole, message) VALUES (2,'utilisateur');

INSERT INTO Utilisateur (login, nom, motDePasse, idRole) VALUES ('admin','admin','admin',1);
INSERT INTO Utilisateur (login, nom, motDePasse, idRole) VALUES ('bob','bob','bob',2);
INSERT INTO Utilisateur (login, nom, motDePasse, idRole) VALUES ('alice','alice','alice',2);
INSERT INTO Utilisateur (login, nom, motDePasse, idRole) VALUES ('charlie','charlie','charlie',2);
INSERT INTO Utilisateur (login, nom, motDePasse, idRole) VALUES ('diane','diane','diane',2);
INSERT INTO Utilisateur (login, nom, motDePasse, idRole) VALUES ('eric','eric','eric',2);
INSERT INTO Utilisateur (login, nom, motDePasse, idRole) VALUES ('fiona','fiona','fiona',2);
INSERT INTO Utilisateur (login, nom, motDePasse, idRole) VALUES ('gaston','gaston','gaston',2);
INSERT INTO Utilisateur (login, nom, motDePasse, idRole) VALUES ('herve','herve','herve',2);

INSERT INTO Gabarit (idGabarit, type) VALUES (1,'coupé');
INSERT INTO Gabarit (idGabarit, type) VALUES (2,'break');
INSERT INTO Gabarit (idGabarit, type) VALUES (3,'cabriolet');
INSERT INTO Gabarit (idGabarit, type) VALUES (4,'citadine');
INSERT INTO Gabarit (idGabarit, type) VALUES (5,'combi VW');
INSERT INTO Gabarit (idGabarit, type) VALUES (6,'semi-remorque');
INSERT INTO Gabarit (idGabarit, type) VALUES (7,'monospace');
INSERT INTO Gabarit (idGabarit, type) VALUES (8,'berline');
INSERT INTO Gabarit (idGabarit, type) VALUES (9,'sportif');

INSERT INTO Ville (idVille, nomVille, departement) VALUES (1, 'Paris',75);
INSERT INTO Ville (idVille, nomVille, departement) VALUES (2, 'Lyon',69);
INSERT INTO Ville (idVille, nomVille, departement) VALUES (3, 'Marseille',13);
INSERT INTO Ville (idVille, nomVille, departement) VALUES (4, 'Orleans',45);
INSERT INTO Ville (idVille, nomVille, departement) VALUES (5, 'Nantes',44);
INSERT INTO Ville (idVille, nomVille, departement) VALUES (6, 'Lille',59);
INSERT INTO Ville (idVille, nomVille, departement) VALUES (7, 'Peta-aux-chnoques Les Bains',39);
INSERT INTO Ville (idVille, nomVille, departement) VALUES (8, 'Amiens',80);
INSERT INTO Ville (idVille, nomVille, departement) VALUES (9, 'Colmar',68);
INSERT INTO Ville (idVille, nomVille, departement) VALUES (10, 'Corbie',80);
INSERT INTO Ville (idVille, nomVille, departement) VALUES (11, 'Rouen',76);
INSERT INTO Ville (idVille, nomVille, departement) VALUES (12, 'Bourges',18);
INSERT INTO Ville (idVille, nomVille, departement) VALUES (13, 'Tours',37);
INSERT INTO Ville (idVille, nomVille, departement) VALUES (14, 'Blois',41);
INSERT INTO Ville (idVille, nomVille, departement) VALUES (15, 'Evry',91);
INSERT INTO Ville (idVille, nomVille, departement) VALUES (16, 'Strasbourg',67);
INSERT INTO Ville (idVille, nomVille, departement) VALUES (17, 'Perpignan',66);
INSERT INTO Ville (idVille, nomVille, departement) VALUES (18, 'La Rochelle',17);

INSERT INTO Vehicule (idVehicule,modele,nombrePlaces,nom,gabarit_idGabarit) VALUES (1,'Espace',6,'titine',7);
INSERT INTO Vehicule (idVehicule,modele,nombrePlaces,nom,gabarit_idGabarit) VALUES (2,'Punto',4,'poupu',4);
INSERT INTO Vehicule (idVehicule,modele,nombrePlaces,nom,gabarit_idGabarit) VALUES (3,'Megane',5,'la grande suzette',8);
INSERT INTO Vehicule (idVehicule,modele,nombrePlaces,nom,gabarit_idGabarit) VALUES (4,'Clio',5,'niveau d huile correcte',4);
INSERT INTO Vehicule (idVehicule,modele,nombrePlaces,nom,gabarit_idGabarit) VALUES (5,'Saxo',5,'la gaillarde',4);
INSERT INTO Vehicule (idVehicule,modele,nombrePlaces,nom,gabarit_idGabarit) VALUES (6,'Maserati',2,'bologno',9);
INSERT INTO Vehicule (idVehicule,modele,nombrePlaces,nom,gabarit_idGabarit) VALUES (7,'Safrane',3,'une voiture quelle est bien pour la conduire',1);
INSERT INTO Vehicule (idVehicule,modele,nombrePlaces,nom,gabarit_idGabarit) VALUES (8,'Kit Kat',5,'asiat',2);
INSERT INTO Vehicule (idVehicule,modele,nombrePlaces,nom,gabarit_idGabarit) VALUES (9,'Renault Truck XGR2',60,'le passeur',6;
INSERT INTO Vehicule (idVehicule,modele,nombrePlaces,nom,gabarit_idGabarit) VALUES (10,'Combi Volkswagen',12,'vamos a la playa',5);

INSERT INTO Utilisateur_vehicule(utilisateur_login,listevehicule_idvehicule) VALUES ('alice',1);
INSERT INTO Utilisateur_vehicule(utilisateur_login,listevehicule_idvehicule) VALUES ('alice',2);
INSERT INTO Utilisateur_vehicule(utilisateur_login,listevehicule_idvehicule) VALUES ('alice',3);
INSERT INTO Utilisateur_vehicule(utilisateur_login,listevehicule_idvehicule) VALUES ('bob',4);
INSERT INTO Utilisateur_vehicule(utilisateur_login,listevehicule_idvehicule) VALUES ('bob',5);
INSERT INTO Utilisateur_vehicule(utilisateur_login,listevehicule_idvehicule) VALUES ('charlie',6);
INSERT INTO Utilisateur_vehicule(utilisateur_login,listevehicule_idvehicule) VALUES ('diane',7);
INSERT INTO Utilisateur_vehicule(utilisateur_login,listevehicule_idvehicule) VALUES ('eric',8);
INSERT INTO Utilisateur_vehicule(utilisateur_login,listevehicule_idvehicule) VALUES ('fiona',9);
INSERT INTO Utilisateur_vehicule(utilisateur_login,listevehicule_idvehicule) VALUES ('gaston',10);


INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_idville,villedepart_idville) VALUES (1,'21/12/2017','12','aVenir',50,1,6,4);
INSERT INTO Etape (idEtape,prix,trajet_idtrajet,villeetape_idville) VALUES (1,10,1,1);
INSERT INTO Etape (idEtape,prix,trajet_idtrajet,villeetape_idville) VALUES (2,30,1,8);
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (1,1,'accepte',null,1,'alice');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (2,2,'enAttente',2,1,'charlie');


INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_idville,villedepart_idville) VALUES (2,'25/12/2017','17','aVenir',30,2,9,5);
INSERT INTO Etape (idEtape,prix,trajet_idtrajet,villeetape_idville) VALUES (3,5,2,6);
INSERT INTO Etape (idEtape,prix,trajet_idtrajet,villeetape_idville) VALUES (4,10,2,3);
INSERT INTO Etape (idEtape,prix,trajet_idtrajet,villeetape_idville) VALUES (5,25,2,11);

INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_idville,villedepart_idville) VALUES (3,'10/07/2017','8h30','fini',150,5,17,14);
INSERT INTO Etape (idEtape,prix,trajet_idtrajet,villeetape_idville) VALUES (6,5,3,7);
INSERT INTO Etape (idEtape,prix,trajet_idtrajet,villeetape_idville) VALUES (7,10,3,18);

INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_idville,villedepart_idville) VALUES (4,'21/12/2017','12','aVenir',50,1,6,4);


INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_idville,villedepart_idville) VALUES (5,'21/12/2017','12','aVenir',50,1,6,4);


INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_idville,villedepart_idville) VALUES (6,'21/12/2017','12','aVenir',50,1,6,4);


INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_idville,villedepart_idville) VALUES (7,'21/12/2017','12','aVenir',50,1,6,4);


INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_idville,villedepart_idville) VALUES (8,'21/12/2017','12','aVenir',50,1,6,4);


INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_idville,villedepart_idville) VALUES (9,'21/12/2017','12','aVenir',50,1,6,4);


INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_idville,villedepart_idville) VALUES (10,'21/12/2017','12','aVenir',50,1,6,4);


INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_idville,villedepart_idville) VALUES (11,'21/12/2017','12','aVenir',50,1,6,4);


INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_idville,villedepart_idville) VALUES (12,'21/12/2017','12','aVenir',50,1,6,4);


INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_idville,villedepart_idville) VALUES (13,'21/12/2017','12','aVenir',50,1,6,4);

