package ejbs;

import entities.Trajet;
import entities.Vehicule;

import javax.ejb.Local;
import java.util.List;

@Local
public interface MaFacadeUtilisateur {

    boolean reserverPlace(String login, int idTrajet, int nbPlaces);
    boolean faireCommentaire(String login, int idTrajet);
    boolean donnerNote(String login, int idTrajet);
    Trajet proposerTrajet(String login, String villeDepart, String villeArrivee, List<String> etapes, String date);
    Vehicule ajouterVehicule(String login, String nomVehicule, String modele, int idGabarit, int nbPlaces);
    boolean annulerTrajet(String login, int idTrajet);
    boolean avoirReservations(String login, int idTrajet);
    boolean refuserReservation(String login, int idReservation);
    boolean accepterReservation(String login, int idreservation);
}
