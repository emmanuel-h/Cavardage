package ejbs;

import dtos.VilleDTO;
import entities.Gabarit;
import entities.Ville;

import javax.ejb.Local;
import java.util.List;

@Local
public interface MaFacadeAdministrateur {

    boolean ajouterVille(String nomVille,String departement);

    boolean supprimerVille(String nomVille,String departement);

    boolean ajouterGabarit(String nomGabarit);

    boolean supprimerGabarit(String nomGabarit);

    List<VilleDTO> getListeVilles();

    List<String> getListeGabarits();
}
