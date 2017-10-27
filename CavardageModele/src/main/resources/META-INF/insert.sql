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

INSERT INTO Ville (nomVille) VALUES ('Paris_75');
INSERT INTO Ville (nomVille) VALUES ('Lyon_69');
INSERT INTO Ville (nomVille) VALUES ('Marseille_13');
INSERT INTO Ville (nomVille) VALUES ('Orleans_45');
INSERT INTO Ville (nomVille) VALUES ('Nantes_44');
INSERT INTO Ville (nomVille) VALUES ('Lille_59');
INSERT INTO Ville (nomVille) VALUES ('Petaouchnok Les Bains_39');
INSERT INTO Ville (nomVille) VALUES ('Amiens_80');
INSERT INTO Ville (nomVille) VALUES ('Colmar_68');
INSERT INTO Ville (nomVille) VALUES ('Corbie_80');
INSERT INTO Ville (nomVille) VALUES ('Rouen_76');
INSERT INTO Ville (nomVille) VALUES ('Bourges_18');
INSERT INTO Ville (nomVille) VALUES ('Tours_37');
INSERT INTO Ville (nomVille) VALUES ('Blois_41');
INSERT INTO Ville (nomVille) VALUES ('Evry_91');
INSERT INTO Ville (nomVille) VALUES ('Strasbourg_67');
INSERT INTO Ville (nomVille) VALUES ('Perpignan_66');
INSERT INTO Ville (nomVille) VALUES ('La Rochelle_17');

INSERT INTO Vehicule (idVehicule,modele,nombrePlaces,nom,gabarit_idGabarit) VALUES (1,'Espace',6,'titine',7);
INSERT INTO Vehicule (idVehicule,modele,nombrePlaces,nom,gabarit_idGabarit) VALUES (2,'Punto',4,'poupu',4);
INSERT INTO Vehicule (idVehicule,modele,nombrePlaces,nom,gabarit_idGabarit) VALUES (3,'Megane',5,'la grande suzette',8);
INSERT INTO Vehicule (idVehicule,modele,nombrePlaces,nom,gabarit_idGabarit) VALUES (4,'Clio',5,'niveau d huile correcte',4);
INSERT INTO Vehicule (idVehicule,modele,nombrePlaces,nom,gabarit_idGabarit) VALUES (5,'Saxo',5,'la gaillarde',4);
INSERT INTO Vehicule (idVehicule,modele,nombrePlaces,nom,gabarit_idGabarit) VALUES (6,'Maserati',2,'bologno',9);
INSERT INTO Vehicule (idVehicule,modele,nombrePlaces,nom,gabarit_idGabarit) VALUES (7,'Safrane',3,'une voiture quelle est bien pour la conduire',1);
INSERT INTO Vehicule (idVehicule,modele,nombrePlaces,nom,gabarit_idGabarit) VALUES (8,'Kit Kat',5,'asiat',2);
INSERT INTO Vehicule (idVehicule,modele,nombrePlaces,nom,gabarit_idGabarit) VALUES (9,'Renault Truck XGR2',60,'le passeur',6);
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


INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (1,'21/12/2017','12h00','aVenir',50,1,'Lille_59','Orleans_45');
INSERT INTO Etape (idEtape,prix,trajet_idtrajet,villeetape_nomville) VALUES (1,10,1,'Paris_75');
INSERT INTO Etape (idEtape,prix,trajet_idtrajet,villeetape_nomville) VALUES (2,30,1,'Amiens_80');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (1,1,'accepte',null,1,'alice');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (2,2,'enAttente',2,1,'charlie');


INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (2,'25/12/2017','17h00','aVenir',30,2,'Colmar_68','Nantes_44');
INSERT INTO Etape (idEtape,prix,trajet_idtrajet,villeetape_nomville) VALUES (3,5,2,'Lille_59');
INSERT INTO Etape (idEtape,prix,trajet_idtrajet,villeetape_nomville) VALUES (4,10,2,'Marseille_13');
INSERT INTO Etape (idEtape,prix,trajet_idtrajet,villeetape_nomville) VALUES (5,25,2,'Rouen_76');

INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (3,'10/07/2017','8h30','fini',150,5,'Perpignan_66','Blois_41');
INSERT INTO Etape (idEtape,prix,trajet_idtrajet,villeetape_nomville) VALUES (6,5,3,'Petaouchnok Les Bains_39');
INSERT INTO Etape (idEtape,prix,trajet_idtrajet,villeetape_nomville) VALUES (7,10,3,'La Rochelle_17');

INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (4,'26/12/2017','0h00','aVenir',1050,3,'Lyon_69','Amiens_80');

INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (5,'25/12/2017','5h00','aVenir',3,10,'Tours_37','Perpignan_66');
INSERT INTO Etape (idEtape,prix,trajet_idtrajet,villeetape_nomville) VALUES (8,1,5,'Corbie_80');
INSERT INTO Etape (idEtape,prix,trajet_idtrajet,villeetape_nomville) VALUES (9,2,5,'Lille_59');

INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (6,'21/12/2017','13h00','aVenir',45,7,'Lille_59','Orleans_45');

INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (7,'21/12/2017','12h15','aVenir',75,8,'Amiens_80','Orleans_45');
INSERT INTO Etape (idEtape,prix,trajet_idtrajet,villeetape_nomville) VALUES (10,25,7,'Lille_59');


INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (8,'21/12/2017','15h00','aVenir',30,6,'Amiens_80','Paris_75');
INSERT INTO Etape (idEtape,prix,trajet_idtrajet,villeetape_nomville) VALUES (11,15,8,'Orleans_45');

INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (9,'21/12/2017','14h02','aVenir',50,10,'Evry_91','Paris_75');
INSERT INTO Etape (idEtape,prix,trajet_idtrajet,villeetape_nomville) VALUES (12,25,9,'Lille_59');


INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (10,'17/08/2017','1h00','annule',102,2,'Lyon_69','Nantes_44');
INSERT INTO Etape (idEtape,prix,trajet_idtrajet,villeetape_nomville) VALUES (13,10,10,'Petaouchnok Les Bains_39');
INSERT INTO Etape (idEtape,prix,trajet_idtrajet,villeetape_nomville) VALUES (14,30,10,'Strasbourg_67');
INSERT INTO Etape (idEtape,prix,trajet_idtrajet,villeetape_nomville) VALUES (15,70,10,'Blois_41');

INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (11,'02/01/2017','18h30','fini',5,5,'Marseille_13','Petaouchnok Les Bains_39');

