package ejbs;

import entities.Gabarit;
import entities.Ville;

import javax.ejb.Local;
import java.util.List;

@Local
public interface MaFacadeAdministrateur {

    boolean ajouterVille(String nomVille);

    boolean supprimerVille(String nomVille);

    boolean ajouterGabarit(String nomGabarit);

    boolean supprimerGabarit(String nomGabarit);

    List<String> getListeVilles();

    List<String> getListeGabarits();
}
