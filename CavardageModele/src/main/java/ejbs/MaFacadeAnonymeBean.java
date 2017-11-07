package ejbs;

import dtos.TrajetDTO;
import dtos.UtilisateurDTO;
import dtos.VilleDTO;
import entities.*;
import exceptions.DatePosterieureException;
import exceptions.LoginExistantException;
import exceptions.UtilisateurNonInscritException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.text.ParseException;
import java.util.*;

@SuppressWarnings("unchecked")

@Stateless(name = "AnonymeBean")
public class MaFacadeAnonymeBean implements MaFacadeAnonyme {

    @PersistenceContext(unitName="monUnite")
    private EntityManager em;
    @EJB
    private RechercheBean rechercheBean;

    public MaFacadeAnonymeBean() {
    }

    @Override
    public UtilisateurDTO connexion(String login, String mdp) throws UtilisateurNonInscritException {
        Query query = em.createQuery("SELECT u FROM Utilisateur u WHERE u.login=:login AND u.motDePasse=:mdp");
        query.setParameter("login",login);
        query.setParameter("mdp",mdp);
        List<Utilisateur> p = query.getResultList();
        if(!p.isEmpty()) {
            return new UtilisateurDTO(p.get(0));
        }else{
            throw new UtilisateurNonInscritException("Echec à la connexion");
        }
    }

    @Override
    public boolean inscription(String login,String nom, String mdp) throws LoginExistantException {
        Query query = em.createQuery("SELECT u FROM Utilisateur u WHERE u.login=:login ");
        query.setParameter("login", login);
        List<Utilisateur> u = query.getResultList();
        if (u.isEmpty()) {
            query = em.createQuery("SELECT r FROM Role r WHERE r.message=:message ");
            query.setParameter("message", "utilisateur");
            List<Role> r = query.getResultList();
            if(!r.isEmpty()) {
                Utilisateur new_u = new Utilisateur(login, nom, mdp,r.get(0));
                em.persist(new_u);
                return true;
            }
            return false;
        } else {
            throw new LoginExistantException("L'utilisateur veut un login déjà pris");
        }
    }

    @Override
    public List<TrajetDTO> rechercheTrajet(String villeDepart, String departementDepart, String villeArrive, String departementArrive, String date, String prix) throws DatePosterieureException, ParseException {
        return rechercheBean.rechercheTrajet(villeDepart,departementDepart,villeArrive,departementArrive,date,prix);
    }

    @Override
    public List<TrajetDTO> dernierAjout() {

        List<Trajet>lt = em.createQuery("SELECT t FROM Trajet t WHERE t.statut='aVenir' ORDER BY t.idTrajet DESC").setMaxResults(10).getResultList();
        List<TrajetDTO> ltd = new ArrayList<>();
        for(Trajet t :lt){
            ltd.add(new TrajetDTO(t));
        }
        Collections.sort(ltd);
        return ltd;

    }

    @Override
    public List<Ville> getListeVille() {
        return rechercheBean.getListeVilles();
    }

    @Override
    public List<VilleDTO> getListeVilleDTO() {
        return rechercheBean.getListeVillesDTO();
    }

    @Override
    public List<Notification> avoirListeNotification(String login){
        Query q = em.createQuery("SELECT u.notifications FROM Utilisateur u WHERE u.login=:login");
        q.setParameter("login", login);
        return q.getResultList();
    }
}
