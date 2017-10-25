package ejbs;

import exceptions.UtilisateurNonInscritException;

public interface MaFacadeAnonyme {

    public boolean connexion(String login,String mdp) throws UtilisateurNonInscritException;

}
