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

    public UtilisateurDTO connexion(String login, String mdp) throws UtilisateurNonInscritException;
    public boolean inscription(String login,String nom,String mdp) throws LoginExistantException;
    public List<TrajetDTO> rechercheTrajet(String villeDepart,String departementDepart, String villeArrive,String departementArrive, String date);
    public List<TrajetDTO> dernierAjout();
    public List<Ville> getListeVille();
    List<VilleDTO> getListeVilleDTO();
}

