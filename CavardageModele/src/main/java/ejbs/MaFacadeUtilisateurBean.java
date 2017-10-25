package ejbs;

import entities.Trajet;
import entities.Vehicule;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class MaFacadeUtilisateurBean implements MaFacadeUtilisateur {
    @Override
    public boolean reserverPlace(String login, int idTrajet, int nbPlaces) {
        return false;
    }

    @Override
    public boolean faireCommentaire(String login, int idTrajet) {
        return false;
    }

    @Override
    public boolean donnerNote(String login, int idTrajet) {
        return false;
    }

    @Override
    public Trajet proposerTrajet(String login, String villeDepart, String villeArrivee, List<String> etapes, String date) {
        return null;
    }

    @Override
    public Vehicule ajouterVehicule(String login, String nomVehicule, String modele, int idGabarit, int nbPlaces) {
        return null;
    }

    @Override
    public boolean annulerTrajet(String login, int idTrajet) {
        return false;
    }

    @Override
    public boolean avoirReservations(String login, int idTrajet) {
        return false;
    }

    @Override
    public boolean refuserReservation(String login, int idReservation) {
        return false;
    }

    @Override
    public boolean accepterReservation(String login, int idreservation) {
        return false;
    }
}
