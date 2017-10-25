package ejbs;

import dtos.TrajetDTO;
import dtos.UtilisateurDTO;
import exceptions.UtilisateurNonInscritException;

import java.util.List;

public interface MaFacadeAnonyme {

    public UtilisateurDTO connexion(String login, String mdp) throws UtilisateurNonInscritException;
    public List<TrajetDTO> rechercheTrajet(String villeDepart, String villeArrive, String date);
    public List<TrajetDTO> dernierAjout();
}
