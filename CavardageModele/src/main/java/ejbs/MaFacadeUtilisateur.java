package ejbs;


import dtos.*;
import entities.*;
import exceptions.*;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Local;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * Interface permettant de gérer les différentes actions provenant d'un utilisateur authentifié
 */
@Local
public interface MaFacadeUtilisateur {

    /**
     * Fait une demande de réservation d'une place dans une trajet
     * @param login                     Le login de l'utilisateur réservant une place
     * @param idTrajet                  L'identifiant du trajet pour lequel la demande de réservation est faite
     * @param nbPlaces                  Le nombre de places voulant être réservées
     * @param idVilleArrivee            L'identifiant de la ville dans laquelle l'utilisateur souhaite descendre
     * @return                          La réservation créée
     * @throws VilleNonTrouvee          Si la ville d'arrivée n'a pas été trouvée dans la base de données
     * @throws AccesInterditException   Si le statut du trajet est à "fini"
     */
    Reservation reserverPlace(String login, int idTrajet, int nbPlaces, String idVilleArrivee) throws VilleNonTrouvee, AccesInterditException;

    /**
     * Donne une appréciation à un utilisateur pour un trajet
     * @param login             Le login de l'utilisateur courant
     * @param idTrajet          Le trajet pour lequel l'appréciation est faite
     * @param commentaire       Le commentaire de l'appréciation
     * @param note              La note de l'appréciation
     * @param loginDestinataire L'identifiant de l'utilisateur noté
     * @return                  L'appreciation créée
     */
    Appreciation donnerAppreciation(String login, int idTrajet, String commentaire, int note, String loginDestinataire);

    /**
     * Renvoie toutes les appreciations concernant un utilisateur
     * @param login
     * @return
     */
    List<AppreciationDTO> avoirToutesAppreciations(String login);

    /**
     * Avoir la moyenen des notes pour un utilisateur
     * @param login                     L'utilisateur voulu
     * @return                          La moyenne des notes arrondie au dixième
     * @throws DivisionParZeroException Si l'utilisateur n'a pas encore reçu de note
     */
    float moyenneNotes(String login) throws DivisionParZeroException;

    /**
     * Ajouter un véhicule pour un utilisateur
     * @param login                             L'utilisateur courant
     * @param nomVehicule                       Le nom du véhicule
     * @param modele                            Le modèle du véhicule
     * @param gabarit                           Le gabarit du véhicule
     * @param nbPlaces                          Le nombre de places disponibles dans le véhicule
     * @return                                  Le véhicule créé
     * @throws VehiculeDejaExistantException    Si le véhicule fait déjà parti de la liste des véhicules de l'utilisateur
     * @throws GabaritException                 Si le gabarit n'est pas dans la base de données
     */
    Vehicule ajouterVehicule(String login, String nomVehicule, String modele, String gabarit, int nbPlaces) throws VehiculeDejaExistantException, GabaritException;

    /**
     * Supprime un véhicule de la liste de véhicules d'un utilisateur
     * @param login                     Le login de l'utilisateur courant
     * @param idVehicule                L'identifiant du véhicule à supprimer
     * @return                          true si le véhicule a bien été supprimé, false sinon
     * @throws PasVehiculeUtilisateur   Si le véhicule n'appartient pas à l'utilisateur
     */
    boolean supprimerVehicule(String login, int idVehicule) throws PasVehiculeUtilisateur;

    /**
     * Renvoie la liste des véhicules d'un utilisateur
     * @param login L'identifiant d'un utilisateur
     * @return      La liste des véhicules
     */
    List<VehiculeDTO> listeVehicules(String login);

    /**
     * Annule un trajet
     * @param login                     L'identifiant de l'utilisateur conducteur de ce trajet
     * @param idTrajet                  L'identifiant du trajet
     * @return                          true si le trajet a été annulé, false sinon
     * @throws PasConducteurException   Si l'utilisateur n'est pas le conducteur du trajet
     */
    boolean annulerTrajet(String login, int idTrajet) throws PasConducteurException;

    /**
     * Avoir la liste des réservations acceptées pour un trajet
     * @param login                     Le login de l'utilisateur
     * @param idTrajet                  L'identifiant du trajet
     * @return                          La liste des réservations acceptées
     * @throws PasConducteurException   Si l'utilisateur n'est pas le conducteur du trajet
     */
    List<ReservationDTO> avoirReservationsAcceptees(String login, int idTrajet) throws PasConducteurException;

    /**
     * Avoir la liste des réservations en attente pour un trajet
     * @param login                     Le login de l'utilisateur
     * @param idTrajet                  L'identifiant du trajet
     * @return                          La liste des réservations en attente
     * @throws PasConducteurException   Si l'utilisateur n'est pas le conducteur du trajet
     */
    List<ReservationDTO> avoirReservationsEnAttente(String login, int idTrajet) throws PasConducteurException;

    /**
     * Refuse une demande de réservation
     * @param login                     Le login de l'utilisateur
     * @param idReservation             L'identifiant de la réservation
     * @return                          true si la réservation a bien été refusée, false sinon
     * @throws PasConducteurException   Si l'utilisateur n'est pas le conducteur du trajet
     */
    boolean refuserReservation(String login, int idReservation) throws PasConducteurException;

    /**
     * Accepte une demande de réservation
     * @param login                     Le login de l'utilisateur
     * @param idReservation             L'identifiant de la réservation
     * @return                          true si la réservation a bien été annulée, false sinon
     * @throws PasConducteurException   Si l'utilisateur n'est pas le conducteur du trajet
     */
    boolean accepterReservation(String login, int idReservation) throws PasConducteurException;

    /**
     * Annule une réservation
     * @param login         Le login de l'utilisateur
     * @param idReservation L'identifiant de la réservation
     * @return              true si la réservation a bien été annulée, false sinon
     */
    boolean annulerReservation(String login,int idReservation);

    /**
     * Supprime une notification
     * @param login             Le login de l'utilisateur
     * @param idNotification    L'identifiant de la notification à supprimer
     * @return                  true si la notification a bien été supprimée, false sinon
     */
    boolean supprimerNotification(String login, int idNotification);

    /**
     * Renvoie l'historique des trajets de l'utilisateur
     * @param login L'identifiant de l'utilisateur
     * @return      La liste des trajets
     */
    List<HistoriqueDTO> historiqueUtilisateur(String login);

    /**
     * Retourne un trajet passé pour l'utilisateur
     * @param login L'identifiant de l'utilisateur
     * @param id    L'identifiant du trajet
     * @return
     */
    HistoriqueDTO uniqueHistoriqueUtilisateur(String login, int id);

    /**
     * Renvoie la liste des gabarits
     * @return  La liste des gabarits
     */
    List<Gabarit> listeGabarits();

    /**
     * Change le mot de passe de l'utilisateut
     * @param login         L'identifiant de l'utilisateur
     * @param motDePasse    Le nouveau mot de passe de l'utilisateur
     * @return              true si le mot de passe a bien été changé, false sinon
     */
    boolean changerMotDePasse(String login, String motDePasse);

    /**
     * Vérifie si le mot de passe est bien celui de l'utilisateur
     * @param login         L'identifiant de l'utilisateur
     * @param motDePasse    Le mot de passe testé
     * @return              true si le mot de passe est le bon, false sinon
     */
    boolean verifierMotDePasse(String login, String motDePasse);

    /**
     * Supprime le compte d'un utilisateur
     * @param login L'identifiant de l'utilisateur
     * @return      true si l'utilisateur a bien été supprimé, false sinon
     */
    boolean supprimerUtilisateur(String login) throws PasConducteurException, PasVehiculeUtilisateur;

    /**
     * Renvoie la liste des villes existantes dans la base de données
     * @return  La liste des villes
     */
    List<VilleDTO> getListeVilleDTO();

    /**
     * Recherche un trajet par rapport à certains critères
     * @param villeDepart               La ville de départ du trajet
     * @param departementDepart         Le département de la ville de départ
     * @param villeArrive               La ville d'arrivée du trajet
     * @param departementArrive         Le département de la ville d'arrivée
     * @param date                      La date du trajet
     * @param prix                      Le prix maximum (non obligatoire)
     * @return                          La liste des trajets correspondants aux critères
     * @throws DateAnterieureException  Si la date est antérieure à aujourd'hui
     * @throws ParseException           Si la date n'est pas dans un format valide
     */
    List<TrajetDTO> rechercheTrajet(String villeDepart, String departementDepart, String villeArrive, String departementArrive, String date, String prix) throws DateAnterieureException, ParseException, VilleNonTrouvee;

    /**
     * Ajoute un trajet dans la base de données
     * @param login                     L'identifiant de l'utilisateur
     * @param villeDepart               La ville de départ du trajet
     * @param villeArrivee              La ville d'arrivée du trajet
     * @param nomVehicule               Le nom du véhicule utilisé pour le trajet
     * @param etapes                    Les différentes étapes du trajet (non obligatoire)
     * @param date                      La date du trajet
     * @param heure                     L'heure du trajet
     * @param prix                      Le prix du trajet
     * @throws PrixInferieurException   Si le prix du trajet est inférieur au prix d'une étape
     * @throws EtapeException           S'il y a une erreur dans la saisie d'une étape
     * @throws VehiculeException        Si le véhicule n'existe pas
     */
    void ajouterTrajet(String login, String villeDepart, String villeArrivee, String nomVehicule, String[] etapes, String date, String heure, String prix) throws PrixInferieurException, EtapeException, VehiculeException, ParseException;

    /**
     * Avoir les détails concernant un trajet
     * @param login                     L'identifiant de l'utilisateur
     * @param idTrajet                  L'identifiant du trajet
     * @param type                      Quels détails afficher (suivant l'endroit où a été fait la demande)
     * @return                          Le trajet voulu
     * @throws AccesInterditException   Si l'utilisateur n'a pas le droit d'afficher ce trajet
     */
    TrajetDTO avoirTrajet(String login, int idTrajet, String type) throws AccesInterditException;

    /**
     * Crée une notification
     * @param login     L'identifiant de l'utilisateur
     * @param message   Le message de la notification
     * @return          La notification créée
     */
    Notification creerNotification(String login, String message);

    /**
     * Renvoie toutes les notifications d'un utilisateur
     * @param login L'identifiant de l'utilisateur
     * @return      La liste des notifications
     */
    List<Notification> avoirListeNotification(String login);

    /**
     * Avoir le nombre de places restantes dans un véhicule
     * @param idTrajet  L'identifiant du trajet
     * @return          Le nombre de palces restantes
     */
    int avoirNbPlacesRestantes(int idTrajet);

    /**
     * Accéder aux détails d'une réservation
     * @param login                     L'identifiant de l'utilisateur
     * @param idReservation             L'identifiant de la réservation
     * @return                          La réservation
     * @throws AccesInterditException   Si l'utilisateur n'a pas le droit d'accéder à cette réservation
     */
    ReservationDTO avoirReservationDTO(String login,int idReservation) throws AccesInterditException;

    /**
     * Avoir la liste des trajets d'un utilisateur
     * @param login L'identifiant de l'utilisateur
     * @return      La liste des trajets
     */
    List<TrajetDTO> avoirListeTrajet(String login);

    /**
     * Avoir la liste des personnes participant à un trajet
     * @param login     L'identifiant de l'utilisateur
     * @param idTrajet  L'identifiant du trajet
     * @return          La liste des personnes
     */
    List<UtilisateurDTO> avoirPersonnesTrajet(String login, int idTrajet);

    /**
     * Supprimer toutes les reservations d'un trajet
     * @param login     L'identifiant de l'utilisateur
     * @param idTrajet  L'identifiant du trajet
     */
    void supprimerToutesReservationsTrajet(String login, int idTrajet) throws PasConducteurException;


    /**
     * Supprime toutes les notifications d'un utilisateur
     * @param login l'utilisateur
     * @throws AccesInterditException Si l'utilisateur actuel n'est pas celui connecté
     */
    void supprimerToutesLesNotifications(String login) throws AccesInterditException;

    /**
     * Avoir le prix moyen des trajets ayant la même ville de départ et d'arrivée
     * @param villeDepart       La ville de départ du trajet
     * @param villeArrivee      la ville d'arrivée du trajet
     * @return                  Le prix moyen des trajets arrondis au dixième
     * @throws VilleNonTrouvee  Si une des deux villes n'est pas dans la base de données
     */
    float avoirPrixMoyen(String villeDepart, String villeArrivee) throws VilleNonTrouvee;

    /**
     * Donne la liste des trajets à venir en tant que conducteur ou passager
     * @param login L'identifiant de l'utilisateur
     * @return      La liste des trajets
     */
    Map<String, Object> avoirListeTrajetAVenir(String login);

    /**
     * Teste si la date est postérieure ou antérieure à aujourd'hui
     * @param dateTest          La date à tester
     * @return                  true si la date est postérieure, false si elle est antérieure
     * @throws ParseException   Si le format de la date n'est pas valide
     */
    boolean datePosterieure(String dateTest)  throws ParseException;
}
