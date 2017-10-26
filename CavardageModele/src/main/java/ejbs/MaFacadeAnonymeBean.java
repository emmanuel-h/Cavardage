package ejbs;

import dtos.TrajetDTO;
import dtos.UtilisateurDTO;
import entities.Trajet;
import entities.Utilisateur;
import exceptions.LoginExistantException;
import exceptions.UtilisateurNonInscritException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Stateless(name = "AnonymeBean")
public class MaFacadeAnonymeBean implements MaFacadeAnonyme {

    @PersistenceContext(unitName="monUnite")
    EntityManager em;

    public MaFacadeAnonymeBean() {
    }

    @Override
    public UtilisateurDTO connexion(String login, String mdp) throws UtilisateurNonInscritException {
        Query query = em.createQuery("From Utilisateur u where u.login=:login and u.motDePasse=:mdp");
        query.setParameter("login",login);
        query.setParameter("mdp",mdp);
        List<Utilisateur> p = query.getResultList();
        if(p.size()>0) {
            return new UtilisateurDTO(p.get(0));
        }
        return null;
    }

    @Override
    public UtilisateurDTO inspcription(String login,String nom, String mdp) throws LoginExistantException {
        Query query = em.createQuery("From Utilisateur u where u.login=:login ");
        query.setParameter("login", login);
        List<Utilisateur> u = query.getResultList();
        if (u.size() == 0) {
            Utilisateur new_u = new Utilisateur(login, nom, mdp);
            System.out.println(new_u.toString());
            em.persist(new_u);
            UtilisateurDTO dto = new UtilisateurDTO(new_u);
            System.out.println(dto.toString());
            return dto;
        } else {
            throw new LoginExistantException();
        }
    }

    @Override
    public List<TrajetDTO> rechercheTrajet(String villeDepart, String villeArrive, String date) {
        List<Trajet>lt = em.createQuery("From Trajet t where t.villeDepart=:villeDepart and t.villeArrive=:villeArrive and " +
                "t.date=:date").getResultList();
        List<TrajetDTO> ltd = new ArrayList<>();
        for(Trajet t :lt){
            ltd.add(new TrajetDTO(t));
        }
        return ltd;
    }

    @Override
    public List<TrajetDTO> dernierAjout() {
        List<Trajet>lt = em.createNativeQuery("SELECT * FROM Trajet LIMIT 10 ").getResultList();
        List<TrajetDTO> ltd = new ArrayList<>();
        for(Trajet t :lt){
            ltd.add(new TrajetDTO(t));
        }
        return ltd;

    }
}
