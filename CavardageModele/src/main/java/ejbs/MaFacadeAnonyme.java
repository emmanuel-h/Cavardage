package ejbs;

import dtos.TrajetDTO;
import dtos.VilleDTO;
import entities.Notification;
import exceptions.DateAnterieureException;
import exceptions.LoginExistantException;
import exceptions.VilleNonTrouvee;

import javax.ejb.Local;
import java.text.ParseException;
import java.util.List;

/**
 * Interface permettant d'effectuer des actions sur le site sans s'être authentifié
 */
@Local
public interface MaFacadeAnonyme {

    /**
     * Inscrit un nouvel utilisateur
     * @param login                     Le login du nouvel utilisateur
     * @param nom                       Le nom du nouvel utilisateur
     * @param mdp                       Le mot de passe du nouvel utilisateur
     * @return                          true si l'inscription s'est bien déroulée, false sinon
     * @throws LoginExistantException   Si le login est déjà pris
     */
    boolean inscription(String login,String nom,String mdp) throws LoginExistantException;

    /**
     * Renvoie la liste des dix derniers trajets créés sur le site
     * @return la liste des trajets
     */
    List<TrajetDTO> dernierAjout();

    /**
     * Recherche un trajet selon différents critères
     * @param villeDepart               La ville de départ du trajet
     * @param departementDepart         Le département de la ville de départ
     * @param villeArrive               La ville d'arrivée du trajet
     * @param departementArrive         Le département de la ville d'arrivée
     * @param date                      La date de départ
     * @param prix                      Le prix maximum (non obligatoire)
     * @return                          La liste des trajets correspondants aux critères
     * @throws DateAnterieureException  Si la date rentrée est antérieure à aujourd'hui
     * @throws ParseException           Si le format de la date n'était pas valide
     */
    List<TrajetDTO> rechercheTrajet(String villeDepart, String departementDepart, String villeArrive, String departementArrive, String date, String prix) throws DateAnterieureException, ParseException, VilleNonTrouvee;

    /**
     * Renvoie la liste des villes existantes dans la base de données
     * @return  La liste des villes
     */
    List<VilleDTO> getListeVilleDTO();
}

