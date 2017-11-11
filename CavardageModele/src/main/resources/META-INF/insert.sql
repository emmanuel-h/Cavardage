INSERT INTO Role ( idRole, message) VALUES (1,'admin');
INSERT INTO Role ( idRole, message) VALUES (2,'utilisateur');

INSERT INTO Utilisateur (login, nom, motDePasse, idRole) VALUES ('admin','admin','c7ad44cbad762a5da0a452f9e854fdc1e0e7a52a38015f23f3eab1d80b931dd472634dfac71cd34ebc35d16ab7fb8a90c81f975113d6c7538dc69dd8de9077ec',1);
INSERT INTO Utilisateur (login, nom, motDePasse, idRole) VALUES ('bob','bob','0416a26ba554334286b1954918ecad7ba6c33575b49df915ff3367b5cef7ecd93b1f0b436636667b27b363011543971f1c81c3151d5ef72733501c1ff33c34af',2);
INSERT INTO Utilisateur (login, nom, motDePasse, idRole) VALUES ('alice','alice','408b27d3097eea5a46bf2ab6433a7234a33d5e49957b13ec7acc2ca08e1a13c75272c90c8d3385d47ede5420a7a9623aad817d9f8a70bd100a0acea7400daa59',2);
INSERT INTO Utilisateur (login, nom, motDePasse, idRole) VALUES ('charlie','charlie','b6b1266e713a640a4f6d7b8450b54dbd45c2874ea634dfc9bb43d5e50ad6ee047b2df87113cbfa2b3b0ae9091cceb1b24bf8e9599f34b9091438e9cca220fa78',2);
INSERT INTO Utilisateur (login, nom, motDePasse, idRole) VALUES ('diane','diane','0f06b055e9e369958f6895a2945725b28e9605497605bc1a0fbd368b401752cb08ce247dde23e47261c75462be61962f25949154408fa736141d78340d0d9fdf',2);
INSERT INTO Utilisateur (login, nom, motDePasse, idRole) VALUES ('eric','eric','7556dd30a844f3037db12ce50b8faa869d9a16a968f769c038764eb1bf304c3497237b7ba1123d8b3b5e3fa3ff8c0bd1ee9cbe4e6ece1fdf12007c5b498f41db',2);
INSERT INTO Utilisateur (login, nom, motDePasse, idRole) VALUES ('fiona','fiona','60de016d80640dd3d3070fdf494d08866b7b04ff4f921f703401b957202e9eb27af0a65a3b8a86815a22fdd4a898b3686668ea6759415c5023e09a182d00d22f',2);
INSERT INTO Utilisateur (login, nom, motDePasse, idRole) VALUES ('gaston','gaston','694f266523da7c3bd916488cb1ebc68f68721611d8b68167e5e3e9e2f370af783937e8bcd4595eb796e3b69c6648aa3ccda8947b67b65b621b4507d6be633e2b',2);
INSERT INTO Utilisateur (login, nom, motDePasse, idRole) VALUES ('herve','herve','5874d04d6186f65f27438452a7bcdac277662ecc92e256296ae86afc97d21778616b7f954430b11f99251a406dce7cc1cbdd3e3c142963bcef1617d75a87f948',2);

INSERT INTO Gabarit (idGabarit, type) VALUES (1,'coupe');
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

INSERT INTO Vehicule (idVehicule,modele,nombrePlaces,statut,nom,gabarit_idGabarit,utilisateur_login) VALUES (1,'Espace',6,'actif','titine',7,'alice');
INSERT INTO Vehicule (idVehicule,modele,nombrePlaces,statut,nom,gabarit_idGabarit,utilisateur_login) VALUES (2,'Punto',4,'actif','poupu',4,'alice');
INSERT INTO Vehicule (idVehicule,modele,nombrePlaces,statut,nom,gabarit_idGabarit,utilisateur_login) VALUES (3,'Megane',5,'actif','la grande suzette',8,'alice');
INSERT INTO Vehicule (idVehicule,modele,nombrePlaces,statut,nom,gabarit_idGabarit,utilisateur_login) VALUES (4,'Clio',5,'actif','niveau d huile correcte',4,'bob');
INSERT INTO Vehicule (idVehicule,modele,nombrePlaces,statut,nom,gabarit_idGabarit,utilisateur_login) VALUES (5,'Saxo',5,'actif','la gaillarde',4,'bob');
INSERT INTO Vehicule (idVehicule,modele,nombrePlaces,statut,nom,gabarit_idGabarit,utilisateur_login) VALUES (6,'Maserati',2,'actif','bologno',9,'charlie');
INSERT INTO Vehicule (idVehicule,modele,nombrePlaces,statut,nom,gabarit_idGabarit,utilisateur_login) VALUES (7,'Safrane',3,'actif','une voiture quelle est bien pour la conduire',1,'diane');
INSERT INTO Vehicule (idVehicule,modele,nombrePlaces,statut,nom,gabarit_idGabarit,utilisateur_login) VALUES (8,'Kit Kat',5,'actif','asiat',2,'eric');
INSERT INTO Vehicule (idVehicule,modele,nombrePlaces,statut,nom,gabarit_idGabarit,utilisateur_login) VALUES (9,'Renault Truck XGR2',60,'actif','le passeur',6,'fiona');
INSERT INTO Vehicule (idVehicule,modele,nombrePlaces,statut,nom,gabarit_idGabarit,utilisateur_login) VALUES (10,'Combi Volkswagen',12,'actif','vamos a la playa',5,'gaston');

INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (1,'21/12/2017','12:00','aVenir',50,1,'Lille_59','Orleans_45');
INSERT INTO Etape (idEtape,prix,trajet_idtrajet,villeetape_nomville) VALUES (1,10,1,'Paris_75');
INSERT INTO Etape (idEtape,prix,trajet_idtrajet,villeetape_nomville) VALUES (2,30,1,'Amiens_80');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (1,1,'accepte',null,1,'bob');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (2,2,'enAttente',2,1,'charlie');


INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (2,'25/12/2017','17:00','aVenir',30,2,'Colmar_68','Nantes_44');
INSERT INTO Etape (idEtape,prix,trajet_idtrajet,villeetape_nomville) VALUES (3,5,2,'Lille_59');
INSERT INTO Etape (idEtape,prix,trajet_idtrajet,villeetape_nomville) VALUES (4,10,2,'Marseille_13');
INSERT INTO Etape (idEtape,prix,trajet_idtrajet,villeetape_nomville) VALUES (5,25,2,'Rouen_76');

INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (3,'10/07/2017','8:30','fini',150,5,'Perpignan_66','Blois_41');
INSERT INTO Etape (idEtape,prix,trajet_idtrajet,villeetape_nomville) VALUES (6,5,3,'Petaouchnok Les Bains_39');
INSERT INTO Etape (idEtape,prix,trajet_idtrajet,villeetape_nomville) VALUES (7,10,3,'La Rochelle_17');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (3,1,'accepte',7,3,'eric');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (7,1,'accepte',7,3,'herve');
INSERT INTO Appreciation (idAppreciation, commentaire, note, donnenote_login, estnote_login, notetrajet_idtrajet) VALUES (1,'ok',3,'bob','eric',3);


INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (4,'26/12/2017','0:00','aVenir',1050,3,'Lyon_69','Amiens_80');

INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (5,'25/12/2017','5:00','aVenir',3,10,'Tours_37','Perpignan_66');
INSERT INTO Etape (idEtape,prix,trajet_idtrajet,villeetape_nomville) VALUES (8,1,5,'Corbie_80');
INSERT INTO Etape (idEtape,prix,trajet_idtrajet,villeetape_nomville) VALUES (9,2,5,'Lille_59');

INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (6,'21/12/2017','13:00','aVenir',45,7,'Lille_59','Orleans_45');

INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (7,'21/12/2017','12:15','aVenir',75,8,'Amiens_80','Orleans_45');
INSERT INTO Etape (idEtape,prix,trajet_idtrajet,villeetape_nomville) VALUES (10,25,7,'Lille_59');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (4,1,'accepte',null,7,'bob');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (5,1,'enAttente',null,7,'alice');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (6,1,'refuse',null,7,'charlie');

INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (8,'21/12/2017','15:00','aVenir',30,6,'Amiens_80','Paris_75');
INSERT INTO Etape (idEtape,prix,trajet_idtrajet,villeetape_nomville) VALUES (11,15,8,'Orleans_45');

INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (9,'21/12/2017','14:02','aVenir',50,10,'Evry_91','Paris_75');
INSERT INTO Etape (idEtape,prix,trajet_idtrajet,villeetape_nomville) VALUES (12,25,9,'Lille_59');


INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (10,'17/08/2017','1:00','annule',102,2,'Lyon_69','Nantes_44');
INSERT INTO Etape (idEtape,prix,trajet_idtrajet,villeetape_nomville) VALUES (13,10,10,'Petaouchnok Les Bains_39');
INSERT INTO Etape (idEtape,prix,trajet_idtrajet,villeetape_nomville) VALUES (14,30,10,'Strasbourg_67');
INSERT INTO Etape (idEtape,prix,trajet_idtrajet,villeetape_nomville) VALUES (15,70,10,'Blois_41');

INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (11,'02/01/2017','18:30','fini',5,5,'Marseille_13','Petaouchnok Les Bains_39');

