package ejbs;

import dtos.StatistiquesDTO;
import dtos.VilleDTO;
import entities.Ville;
import exceptions.GabaritException;
import exceptions.VilleExistante;
import exceptions.VilleNonTrouvee;

import javax.ejb.Local;
import java.util.List;

/**
 * Interface permettant d'effectuer des tâches d'administration du site
 */
@Local
public interface MaFacadeAdministrateur {

    /**
     * Ajoute une ville dans la base de données
     * @param nomVille          Le nom de la nouvelle ville
     * @param departement       Le département de la ville
     * @return                  true si la ville a bien été créée, false sinon
     * @throws VilleExistante   Si la ville existait déjà
     */
    boolean ajouterVille(String nomVille,String departement) throws VilleExistante;

    /**
     * Supprime une ville existant dans la base de données
     * @param nomVille          Le nom de la ville à supprimer
     * @param departement       Le département de la ville
     * @return                  true si la ville a bien été supprimée, false sinon
     * @throws VilleNonTrouvee  Si la ville n'existait pas dans la base
     */
    boolean supprimerVille(String nomVille, String departement) throws VilleNonTrouvee;

    /**
     * Ajouter un gabarit dans la base de données
     * @param nomGabarit        Le nom du gabarit à rajouter
     * @return                  true si le gabarit a bien été rajouté
     * @throws GabaritException Si le gabarit existait déjà dans la base
     */
    boolean ajouterGabarit(String nomGabarit) throws GabaritException;

    /**
     * Supprime un gabarit dans la base de données
     * @param gabaritASupprimer     Le nom du gabarit à supprimer
     * @param gabaritARemplacer     Le nom du gabarit remplaçant celui supprimé
     * @throws GabaritException     Si un des deux gabarits n'est pas trouvé
     */
    void supprimerGabarit(String gabaritASupprimer, String gabaritARemplacer) throws GabaritException;

    /**
     * Renvoie la liste des gabarits disponibles
     * @return  La liste des gabarits
     */
    List<String> getListeGabarits();

    /**
     * Renvoie la liste des villes existantes
     * @return  La liste des villes
     */
    List<VilleDTO> getListeVilleDTO();

    /**
     * Récupère différentes statistiques pour l'administration du site
     * @return  Les statistiques
     */
    StatistiquesDTO recupererStatistiques();
}
