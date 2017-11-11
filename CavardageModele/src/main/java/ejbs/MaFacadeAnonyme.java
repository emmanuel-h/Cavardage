package ejbs;

import dtos.TrajetDTO;
import dtos.UtilisateurDTO;
import dtos.VilleDTO;
import entities.Notification;
import entities.Ville;
import exceptions.DatePosterieureException;
import exceptions.LoginExistantException;
import exceptions.UtilisateurNonInscritException;

import javax.ejb.Local;
import java.text.ParseException;
import java.util.List;

@Local
public interface MaFacadeAnonyme {
    boolean inscription(String login,String nom,String mdp) throws LoginExistantException;
    List<TrajetDTO> dernierAjout();
    List<TrajetDTO> rechercheTrajet(String villeDepart, String departementDepart, String villeArrive, String departementArrive, String date, String prix) throws DatePosterieureException, ParseException;
    List<Ville> getListeVille();
    List<VilleDTO> getListeVilleDTO();
    List<Notification> avoirListeNotification(String login);
}

