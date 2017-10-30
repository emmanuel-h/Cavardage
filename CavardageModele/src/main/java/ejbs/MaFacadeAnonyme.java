package ejbs;

import dtos.TrajetDTO;
import dtos.UtilisateurDTO;
import dtos.VilleDTO;
import entities.Ville;
import exceptions.LoginExistantException;
import exceptions.UtilisateurNonInscritException;

import javax.ejb.Local;
import java.util.List;

@Local
public interface MaFacadeAnonyme {

    UtilisateurDTO connexion(String login, String mdp) throws UtilisateurNonInscritException;
    boolean inscription(String login,String nom,String mdp) throws LoginExistantException;
    List<TrajetDTO> rechercheTrajet(String villeDepart,String departementDepart, String villeArrive,String departementArrive, String date);
    List<TrajetDTO> dernierAjout();
    List<Ville> getListeVille();
    List<VilleDTO> getListeVilleDTO();
}

