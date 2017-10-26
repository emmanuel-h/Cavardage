package ejbs;

import entities.Appreciation;
import entities.Reservation;
import entities.Trajet;
import entities.Vehicule;
import exceptions.DivisionParZeroException;
import exceptions.PasConducteurException;
import exceptions.VilleNonTrouvee;

import javax.ejb.Local;
import java.util.List;
import java.util.Map;

@Local
public interface MaFacadeUtilisateur {

    Reservation reserverPlace(String login, int idTrajet, int nbPlaces, int idVilleArrivee) throws VilleNonTrouvee;
    Appreciation donnerAppreciation(String login, int idTrajet, String commentaire, int note);
    List<Appreciation> avoirNotesTrajet(int idTrajet);
    List<Appreciation> avoirNotesTotal(String login);
    float moyenneNotes(String login) throws DivisionParZeroException;
    Trajet proposerTrajet(int idVilleDepart, int idVilleArrivee, Map<Integer,Integer> villesPrix, String date, String heure, int idVehicule, int prix);
    Vehicule ajouterVehicule(String login, String nomVehicule, String modele, int idGabarit, int nbPlaces);
    boolean annulerTrajet(String login, int idTrajet) throws PasConducteurException;
    List<Reservation> avoirReservationsEnAttente(String login, int idTrajet) throws PasConducteurException;
    boolean refuserReservation(String login, int idReservation);
    boolean accepterReservation(String login, int idreservation);
    boolean supprimerNotification(String login, int idNotification);
}
