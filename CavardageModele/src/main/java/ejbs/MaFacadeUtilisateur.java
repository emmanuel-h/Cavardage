package ejbs;

import dtos.*;
import entities.*;
import exceptions.*;

import javax.ejb.Local;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

@Local
public interface MaFacadeUtilisateur {

    Reservation reserverPlace(String login, int idTrajet, int nbPlaces, String idVilleArrivee) throws VilleNonTrouvee;
    Appreciation donnerAppreciation(String login, int idTrajet, String commentaire, int note, String loginDestinataire);
    List<Appreciation> avoirNotesTrajet(int idTrajet);
    List<AppreciationDTO> avoirToutesAppreciations(String login);
    float moyenneNotes(String login) throws DivisionParZeroException;
    Vehicule ajouterVehicule(String login, String nomVehicule, String modele, String gabarit, int nbPlaces) throws VehiculeDejaExistantException, GabaritException;
    boolean supprimerVehicule(String login, int idVehicule);
    List<VehiculeDTO> listeVehicules(String login);
    boolean annulerTrajet(String login, int idTrajet) throws PasConducteurException;
    List<ReservationDTO> avoirReservationsAcceptees(String login, int idTrajet) throws PasConducteurException;
    List<ReservationDTO> avoirReservationsEnAttente(String login, int idTrajet) throws PasConducteurException;
    boolean refuserReservation(String login, int idReservation) throws PasConducteurException;
    boolean accepterReservation(String login, int idReservation) throws PasConducteurException;
    boolean annulerReservation(String login,int idReservation);
    boolean supprimerNotification(String login, int idNotification);
    List<HistoriqueDTO> historiqueUtilisateur(String login);
    HistoriqueDTO uniqueHistoriqueUtilisateur(String login, int id);
    List<Gabarit> listeGabarits();
    boolean changerMotDePasse(String login, String motDePasse);
    boolean verifierMotDePasse(String login, String motDePasse);
    boolean supprimerUtilisateur(String login);
    List<Ville> getListeVilles();
    List<VilleDTO> getListeVilleDTO();
    List<TrajetDTO> rechercheTrajet(String villeDepart, String departementDepart, String villeArrive, String departementArrive, String date, String prix) throws DatePosterieureException, ParseException;
    void ajouterTrajet(String login, String villeDepart, String villeArrivee, String nomVehicule, String[] etapes, String date, String heure, String minute, String prix) throws PrixInferieurException, EtapeException, VehiculeException;
    TrajetDTO avoirTrajet(int idTrajet);
    Notification creerNotification(String login,String message);
    List<Notification> avoirListeNotification(String login);
    int avoirNbPlacesRestantes(int idTrajet);
    ReservationDTO avoirReservationDTO(int idReservation);
    List<TrajetDTO> avoirListeTrajet(String login);
    List<UtilisateurDTO> avoirPersonnesTrajet(String login, int idTrajet);
    List<PrixMoyenDTO> avoirPrixMoyen();
    float avoirPrixMoyen(String villeDepart, String villeArrivee) throws VilleNonTrouvee;
    Map<String, Object> avoirListeTrajetAVenir(String login);
    boolean datePosterieure(String dateTest)  throws ParseException;
}
