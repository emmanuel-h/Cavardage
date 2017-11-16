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
INSERT INTO Gabarit (idGabarit, type) VALUES (9,'sportive');

INSERT INTO Ville (nomVille) VALUES ('Paris_75');
INSERT INTO Ville (nomVille) VALUES ('Lyon_69');
INSERT INTO Ville (nomVille) VALUES ('Marseille_13');
INSERT INTO Ville (nomVille) VALUES ('Orleans_45');
INSERT INTO Ville (nomVille) VALUES ('Nantes_44');
INSERT INTO Ville (nomVille) VALUES ('Lille_59');
INSERT INTO Ville (nomVille) VALUES ('Chateaudun_28');
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
INSERT INTO Vehicule (idVehicule,modele,nombrePlaces,statut,nom,gabarit_idGabarit,utilisateur_login) VALUES (9,'Renault Truck',3,'actif','le passeur',6,'fiona');
INSERT INTO Vehicule (idVehicule,modele,nombrePlaces,statut,nom,gabarit_idGabarit,utilisateur_login) VALUES (10,'Combi Volkswagen',7,'actif','vamos a la playa',5,'gaston');


INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (11,'02/01/2017','18:30','fini',5,5,'Marseille_13','Chateaudun_28');

INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (12,'07/07/2017','8:00','fini',30,4,'Paris_75','Orleans_45');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (57,1,'accepte',null,12,'alice');
INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (13,'07/07/2017','17:30','fini',30,4,'Orleans_45','Paris_75');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (8,1,'accepte',null,13,'alice');

INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (14,'08/07/2017','8:00','fini',30,4,'Paris_75','Orleans_45');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (9,1,'accepte',null,14,'alice');
INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (15,'08/07/2017','17:30','fini',30,4,'Orleans_45','Paris_75');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (10,1,'accepte',null,15,'alice');

INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (16,'09/07/2017','8:00','fini',30,4,'Paris_75','Orleans_45');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (11,1,'accepte',null,16,'alice');
INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (17,'09/07/2017','17:30','fini',30,4,'Orleans_45','Paris_75');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (12,1,'accepte',null,17,'alice');

INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (18,'10/07/2017','8:00','fini',30,4,'Paris_75','Orleans_45');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (13,1,'accepte',null,18,'alice');
INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (19,'10/07/2017','17:30','fini',30,4,'Orleans_45','Paris_75');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (14,1,'accepte',null,19,'alice');

INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (20,'11/07/2017','8:00','fini',30,4,'Paris_75','Orleans_45');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (15,1,'accepte',null,20,'alice');
INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (21,'11/07/2017','17:30','fini',30,4,'Orleans_45','Paris_75');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (16,1,'accepte',null,21,'alice');

INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (22,'12/07/2017','8:00','fini',30,4,'Paris_75','Orleans_45');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (17,1,'accepte',null,22,'alice');
INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (23,'12/07/2017','17:30','fini',30,4,'Orleans_45','Paris_75');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (18,1,'accepte',null,23,'alice');

INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (24,'13/07/2017','8:00','fini',30,4,'Paris_75','Orleans_45');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (19,1,'accepte',null,24,'alice');
INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (25,'13/07/2017','17:30','fini',30,4,'Orleans_45','Paris_75');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (20,1,'accepte',null,25,'alice');

INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (26,'14/07/2017','8:00','fini',30,4,'Paris_75','Orleans_45');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (21,1,'accepte',null,26,'alice');
INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (27,'14/07/2017','17:30','fini',30,4,'Orleans_45','Paris_75');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (22,1,'accepte',null,27,'alice');

INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (28,'15/07/2017','8:00','fini',30,4,'Paris_75','Orleans_45');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (23,1,'accepte',null,28,'alice');
INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (29,'15/07/2017','17:30','fini',30,4,'Orleans_45','Paris_75');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (24,1,'accepte',null,29,'alice');

INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (30,'16/07/2017','8:00','fini',30,4,'Paris_75','Orleans_45');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (25,1,'accepte',null,30,'alice');
INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (31,'16/07/2017','17:30','fini',30,4,'Orleans_45','Paris_75');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (26,1,'accepte',null,31,'alice');

INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (32,'17/07/2017','8:00','fini',30,4,'Paris_75','Orleans_45');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (27,1,'accepte',null,32,'alice');
INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (33,'17/07/2017','17:30','fini',30,4,'Orleans_45','Paris_75');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (28,1,'accepte',null,33,'alice');

INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (34,'18/07/2017','8:00','fini',30,4,'Paris_75','Orleans_45');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (29,1,'accepte',null,34,'alice');
INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (35,'18/07/2017','17:30','fini',30,4,'Orleans_45','Paris_75');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (30,1,'accepte',null,35,'alice');

INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (36,'19/07/2017','8:00','fini',30,4,'Paris_75','Orleans_45');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (31,1,'accepte',null,36,'alice');
INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (37,'19/07/2017','17:30','fini',30,4,'Orleans_45','Paris_75');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (32,1,'accepte',null,37,'alice');

INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (38,'20/07/2017','8:00','fini',30,4,'Paris_75','Orleans_45');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (33,1,'accepte',null,38,'alice');
INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (39,'20/07/2017','17:30','fini',30,4,'Orleans_45','Paris_75');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (34,1,'accepte',null,39,'alice');

INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (40,'21/07/2017','8:00','fini',30,4,'Paris_75','Orleans_45');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (35,1,'accepte',null,40,'alice');
INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (41,'21/07/2017','17:30','fini',30,4,'Orleans_45','Paris_75');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (36,1,'accepte',null,41,'alice');

INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (42,'22/07/2017','8:00','fini',30,4,'Paris_75','Orleans_45');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (37,1,'accepte',null,42,'alice');
INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (43,'22/07/2017','17:30','fini',30,4,'Orleans_45','Paris_75');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (38,1,'accepte',null,43,'alice');

INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (44,'23/07/2017','8:00','fini',30,4,'Paris_75','Orleans_45');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (39,1,'accepte',null,44,'alice');
INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (45,'23/07/2017','17:30','fini',30,4,'Orleans_45','Paris_75');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (40,1,'accepte',null,45,'alice');

INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (46,'24/07/2017','8:00','fini',30,4,'Paris_75','Orleans_45');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (41,1,'accepte',null,46,'alice');
INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (47,'24/07/2017','17:30','fini',30,4,'Orleans_45','Paris_75');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (42,1,'accepte',null,47,'alice');

INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (48,'25/07/2017','8:00','fini',30,4,'Paris_75','Orleans_45');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (43,1,'accepte',null,48,'alice');
INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (49,'25/07/2017','17:30','fini',30,4,'Orleans_45','Paris_75');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (44,1,'accepte',null,49,'alice');

INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (50,'26/07/2017','8:00','fini',30,4,'Paris_75','Orleans_45');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (45,1,'accepte',null,50,'alice');
INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (51,'26/07/2017','17:30','fini',30,4,'Orleans_45','Paris_75');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (46,1,'accepte',null,51,'alice');

INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (52,'27/07/2017','8:00','fini',30,4,'Paris_75','Orleans_45');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (47,1,'accepte',null,52,'alice');
INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (53,'27/07/2017','17:30','fini',30,4,'Orleans_45','Paris_75');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (48,1,'accepte',null,53,'alice');

INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (54,'28/07/2017','8:00','fini',30,4,'Paris_75','Orleans_45');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (49,1,'accepte',null,54,'alice');
INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (55,'28/07/2017','17:30','fini',30,4,'Orleans_45','Paris_75');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (50,1,'accepte',null,55,'alice');

INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (56,'29/07/2017','8:00','fini',30,4,'Paris_75','Orleans_45');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (51,1,'accepte',null,56,'alice');
INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (57,'29/07/2017','17:30','fini',30,4,'Orleans_45','Paris_75');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (52,1,'accepte',null,57,'alice');

INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (58,'30/07/2017','8:00','fini',30,4,'Paris_75','Orleans_45');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (53,1,'accepte',null,58,'alice');
INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (59,'30/07/2017','17:30','fini',30,4,'Orleans_45','Paris_75');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (54,1,'accepte',null,59,'alice');

INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (60,'31/07/2017','8:00','fini',30,4,'Paris_75','Orleans_45');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (55,1,'accepte',null,60,'alice');
INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (61,'31/07/2017','17:30','fini',30,4,'Orleans_45','Paris_75');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (56,1,'accepte',null,61,'alice');

INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (62,'01/02/2018','8:00','aVenir',100,9,'La Rochelle_17','Chateaudun_28');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (58,1,'en attente',null,62,'gaston');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (59,1,'en attente',null,62,'eric');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (60,1,'en attente',null,62,'gaston');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (61,1,'en attente',null,62,'eric');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (62,1,'en attente',null,62,'gaston');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (63,1,'en attente',null,62,'eric');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (64,1,'en attente',null,62,'gaston');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (65,1,'en attente',null,62,'eric');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (66,1,'en attente',null,62,'gaston');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (67,1,'en attente',null,62,'eric');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (68,1,'en attente',null,62,'gaston');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (69,1,'en attente',null,62,'eric');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (70,1,'en attente',null,62,'gaston');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (71,1,'en attente',null,62,'eric');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (72,1,'en attente',null,62,'gaston');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (73,1,'en attente',null,62,'eric');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (74,1,'en attente',null,62,'gaston');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (75,1,'en attente',null,62,'eric');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (76,1,'en attente',null,62,'gaston');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (77,1,'en attente',null,62,'eric');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (78,1,'en attente',null,62,'gaston');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (79,1,'en attente',null,62,'eric');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (80,1,'en attente',null,62,'gaston');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (81,1,'en attente',null,62,'eric');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (82,1,'en attente',null,62,'gaston');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (83,1,'en attente',null,62,'eric');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (84,1,'en attente',null,62,'gaston');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (85,1,'en attente',null,62,'eric');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (86,1,'en attente',null,62,'gaston');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (87,1,'en attente',null,62,'eric');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (88,1,'en attente',null,62,'gaston');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (89,1,'en attente',null,62,'eric');

INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (63,'02/02/2018','8:00','aVenir',100,9,'La Rochelle_17','Chateaudun_28');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (90,1,'en attente',null,63,'gaston');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (91,1,'en attente',null,63,'eric');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (92,1,'en attente',null,63,'gaston');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (93,1,'en attente',null,63,'eric');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (94,1,'en attente',null,63,'gaston');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (95,1,'en attente',null,63,'eric');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (96,1,'en attente',null,63,'gaston');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (97,1,'en attente',null,63,'eric');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (98,1,'en attente',null,63,'gaston');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (99,1,'en attente',null,63,'eric');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (100,1,'en attente',null,63,'gaston');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (101,1,'en attente',null,63,'eric');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (102,1,'en attente',null,63,'gaston');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (103,1,'en attente',null,63,'eric');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (104,1,'en attente',null,63,'gaston');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (105,1,'en attente',null,63,'eric');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (106,1,'en attente',null,63,'gaston');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (107,1,'en attente',null,63,'eric');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (108,1,'en attente',null,63,'gaston');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (109,1,'en attente',null,63,'eric');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (110,1,'en attente',null,63,'gaston');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (111,1,'en attente',null,63,'eric');

INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (64,'03/02/2018','8:00','aVenir',100,9,'La Rochelle_17','Chateaudun_28');

INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (65,'04/02/2018','8:00','aVenir',100,9,'La Rochelle_17','Chateaudun_28');

INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (66,'05/02/2018','8:00','aVenir',100,9,'La Rochelle_17','Chateaudun_28');

INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (67,'06/02/2018','8:00','aVenir',100,9,'La Rochelle_17','Chateaudun_28');

INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (68,'07/02/2018','8:00','aVenir',100,9,'La Rochelle_17','Chateaudun_28');

INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (69,'08/02/2018','8:00','aVenir',100,9,'La Rochelle_17','Chateaudun_28');

INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (70,'09/02/2018','8:00','aVenir',100,9,'La Rochelle_17','Chateaudun_28');

INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (71,'10/02/2018','8:00','aVenir',100,9,'La Rochelle_17','Chateaudun_28');

INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (72,'11/02/2018','8:00','aVenir',100,9,'La Rochelle_17','Chateaudun_28');

INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (73,'12/02/2018','8:00','aVenir',100,9,'La Rochelle_17','Chateaudun_28');

INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (74,'13/02/2018','8:00','aVenir',100,9,'La Rochelle_17','Chateaudun_28');

INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (75,'14/02/2018','8:00','aVenir',100,9,'La Rochelle_17','Chateaudun_28');

INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (76,'15/02/2018','8:00','aVenir',100,9,'La Rochelle_17','Chateaudun_28');

INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (77,'16/02/2018','8:00','aVenir',100,9,'La Rochelle_17','Chateaudun_28');

INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (78,'17/02/2018','8:00','aVenir',100,9,'La Rochelle_17','Chateaudun_28');

INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (79,'18/02/2018','8:00','aVenir',100,9,'La Rochelle_17','Chateaudun_28');

INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (1,'19/02/2018','8:00','aVenir',100,9,'La Rochelle_17','Chateaudun_28');

INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (2,'20/02/2018','8:00','aVenir',100,9,'La Rochelle_17','Chateaudun_28');

INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (3,'21/02/2018','8:00','aVenir',100,9,'La Rochelle_17','Chateaudun_28');

INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (4,'22/02/2018','8:00','aVenir',100,9,'La Rochelle_17','Chateaudun_28');

INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (5,'23/02/2018','8:00','aVenir',100,9,'La Rochelle_17','Chateaudun_28');

INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (6,'24/02/2018','8:00','aVenir',100,9,'La Rochelle_17','Chateaudun_28');

INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (7,'25/02/2018','8:00','aVenir',100,9,'La Rochelle_17','Chateaudun_28');

INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (8,'26/02/2018','8:00','aVenir',100,9,'La Rochelle_17','Chateaudun_28');

INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (9,'27/02/2018','8:00','aVenir',100,9,'La Rochelle_17','Chateaudun_28');

INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (10,'28/02/2018','8:00','aVenir',100,9,'La Rochelle_17','Chateaudun_28');

INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (80,'21/12/2017','12:00','aVenir',50,1,'Lille_59','Orleans_45');
INSERT INTO Etape (idEtape,prix,trajet_idtrajet,villeetape_nomville) VALUES (1,10,80,'Paris_75');
INSERT INTO Etape (idEtape,prix,trajet_idtrajet,villeetape_nomville) VALUES (2,30,80,'Amiens_80');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (1,1,'accepte',null,80,'bob');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (2,2,'en attente',2,80,'charlie');


INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (81,'25/12/2017','17:00','aVenir',30,2,'Colmar_68','Nantes_44');
INSERT INTO Etape (idEtape,prix,trajet_idtrajet,villeetape_nomville) VALUES (3,5,81,'Lille_59');
INSERT INTO Etape (idEtape,prix,trajet_idtrajet,villeetape_nomville) VALUES (4,10,81,'Marseille_13');
INSERT INTO Etape (idEtape,prix,trajet_idtrajet,villeetape_nomville) VALUES (5,25,81,'Rouen_76');

INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (82,'10/07/2017','8:30','fini',150,5,'Perpignan_66','Blois_41');
INSERT INTO Etape (idEtape,prix,trajet_idtrajet,villeetape_nomville) VALUES (6,5,82,'Chateaudun_28');
INSERT INTO Etape (idEtape,prix,trajet_idtrajet,villeetape_nomville) VALUES (7,10,82,'La Rochelle_17');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (3,1,'accepte',7,82,'eric');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (7,1,'accepte',7,82,'herve');
INSERT INTO Appreciation (idAppreciation, commentaire, note, donnenote_login, estnote_login, notetrajet_idtrajet) VALUES (1,'ok',3,'bob','eric',82);


INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (83,'26/12/2017','0:00','aVenir',1050,3,'Lyon_69','Amiens_80');

INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (84,'25/12/2017','5:00','aVenir',3,10,'Tours_37','Perpignan_66');
INSERT INTO Etape (idEtape,prix,trajet_idtrajet,villeetape_nomville) VALUES (8,1,84,'Corbie_80');
INSERT INTO Etape (idEtape,prix,trajet_idtrajet,villeetape_nomville) VALUES (9,2,84,'Lille_59');

INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (85,'21/12/2017','13:00','aVenir',45,7,'Lille_59','Orleans_45');

INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (86,'21/12/2017','12:15','aVenir',75,8,'Amiens_80','Orleans_45');
INSERT INTO Etape (idEtape,prix,trajet_idtrajet,villeetape_nomville) VALUES (10,25,86,'Lille_59');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (4,1,'accepte',null,86,'bob');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (5,1,'en attente',null,86,'alice');
INSERT INTO Reservation (idReservation,nbplace,statut,descenda_idetape,trajetreservation_idtrajet,utilisateurreservation_login) VALUES (6,1,'refuse',null,86,'charlie');

INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (87,'21/12/2017','15:00','aVenir',30,6,'Amiens_80','Paris_75');
INSERT INTO Etape (idEtape,prix,trajet_idtrajet,villeetape_nomville) VALUES (11,15,87,'Orleans_45');

INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (88,'21/12/2017','14:02','aVenir',50,10,'Evry_91','Paris_75');
INSERT INTO Etape (idEtape,prix,trajet_idtrajet,villeetape_nomville) VALUES (12,25,88,'Lille_59');


INSERT INTO Trajet (idTrajet, date, heure,statut,prix,vehiculetrajet_idvehicule,villearrivee_nomville,villedepart_nomville) VALUES (89,'17/08/2017','1:00','annule',102,2,'Lyon_69','Nantes_44');
INSERT INTO Etape (idEtape,prix,trajet_idtrajet,villeetape_nomville) VALUES (13,10,89,'Chateaudun_28');
INSERT INTO Etape (idEtape,prix,trajet_idtrajet,villeetape_nomville) VALUES (14,30,89,'Strasbourg_67');
INSERT INTO Etape (idEtape,prix,trajet_idtrajet,villeetape_nomville) VALUES (15,70,89,'Blois_41');
