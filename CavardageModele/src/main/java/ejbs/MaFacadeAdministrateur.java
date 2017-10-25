package ejbs;

import entities.Gabarit;
import entities.Ville;

import javax.ejb.Local;

@Local
public interface MaFacadeAdministrateur {

    boolean ajouterVille(String nomVille);

    boolean supprimerVille(String nomVille);

    boolean ajouterGabarit(String nomGabarit);

    boolean supprimerGabarit(String nomGabarit);
}
