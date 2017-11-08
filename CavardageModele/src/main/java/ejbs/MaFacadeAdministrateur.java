package ejbs;

import dtos.StatistiquesDTO;
import dtos.VilleDTO;
import entities.Ville;
import exceptions.GabaritException;
import exceptions.VilleExistante;
import exceptions.VilleNonTrouvee;

import javax.ejb.Local;
import java.util.List;

@Local
public interface MaFacadeAdministrateur {

    boolean ajouterVille(String nomVille,String departement) throws VilleExistante;

    boolean supprimerVille(String nomVille, String departement) throws VilleNonTrouvee;

    boolean ajouterGabarit(String nomGabarit) throws GabaritException;

    void supprimerGabarit(String gabaritASupprimer, String gabaritARemplacer) throws GabaritException;

    List<Ville> getListeVilles();

    List<String> getListeGabarits();

    List<VilleDTO> getListeVilleDTO();

    StatistiquesDTO recupererStatistiques();
}
