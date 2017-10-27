package ejbs;

import dtos.HistoriqueDTO;
import dtos.VehiculeDTO;
import dtos.VilleDTO;
import entities.*;
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
    Trajet proposerTrajet(String idVilleDepart, String idVilleArrivee, Map<String,Integer> villesPrix, String date, String heure, int idVehicule, int prix);
    Vehicule ajouterVehicule(String login, String nomVehicule, String modele, int idGabarit, int nbPlaces);
    boolean supprimerVehicule(int idVehicule);
    List<VehiculeDTO> listeVehicules(String login);
    boolean annulerTrajet(String login, int idTrajet) throws PasConducteurException;
    List<Reservation> avoirReservationsEnAttente(String login, int idTrajet) throws PasConducteurException;
    boolean refuserReservation(String login, int idReservation) throws PasConducteurException;
    boolean accepterReservation(String login, int idReservation) throws PasConducteurException;
    boolean supprimerNotification(String login, int idNotification);
    List<HistoriqueDTO> historiqueUtilisateur(String login);
    List<Gabarit> listeGabarits();
    boolean changerMotDePasse(String login, String motDePasse);
    boolean verifierMotDePasse(String login, String motDePasse);
    boolean supprimerUtilisateur(String login);
    List<Ville> getListeVilles();
    List<VilleDTO> getListeVilleDTO();
    void preAjoutVille(String login, String villeDepart, String villeArrivee, String nomVehicule, String[] etapes, String[] prixEtapes, String date, String heure, String prix);

}
