package ejbs;

import dtos.TrajetDTO;
import dtos.UtilisateurDTO;
import dtos.VilleDTO;
import entities.Role;
import entities.Trajet;
import entities.Utilisateur;
import entities.Ville;
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
        if(!p.isEmpty()) {
            return new UtilisateurDTO(p.get(0));
        }else{
            throw new UtilisateurNonInscritException();
        }
    }

    @Override
    public List<VilleDTO> getListeVille() {
        Query q = em.createQuery("From Ville v");
        List<Ville> listeTemp = q.getResultList();
        if(!listeTemp.isEmpty()){
            List<VilleDTO> listeVilles = new ArrayList<>();
            for(Ville v : listeTemp){
                listeVilles.add(new VilleDTO(v));
            }
            return listeVilles;
        }
        return new ArrayList<>();
    }

    @Override
    public boolean inscription(String login,String nom, String mdp) throws LoginExistantException {
        Query query = em.createQuery("From Utilisateur u where u.login=:login ");
        query.setParameter("login", login);
        List<Utilisateur> u = query.getResultList();
        if (u.isEmpty()) {
            query = em.createQuery("From Role u where u.message=:message ");
            query.setParameter("message", "utilisateur");
            List<Role> r = query.getResultList();
            if(!r.isEmpty()) {
                Utilisateur new_u = new Utilisateur(login, nom, mdp,r.get(0));
                em.persist(new_u);
                return true;
            }
            return false;
        } else {
            throw new LoginExistantException();
        }
    }

    @Override
    public List<TrajetDTO> rechercheTrajet(String villeDepart,String departementDepart, String villeArrivee,
           String departementArrive, String date) {
        System.out.println(villeDepart+" "+departementDepart +" "+villeArrivee+" "+departementArrive+""+date);
       Query query = em.createQuery("SELECT DISTINCT t From Trajet t, Ville vd, Ville va, Etape e WHERE " +
               "t.villeDepart=vd and vd.nomVille=:villeDepart" +
               " and ((t.villeArrivee=va and va.nomVille=:villeArrivee) or" +
               " (e.villeEtape.nomVille=:villeArrivee and e.trajet=t)) " +
               "and t.date=:date");
        query.setParameter("villeDepart", villeDepart);
        query.setParameter("villeArrivee", villeArrivee);
        query.setParameter("date",date);
        List<Trajet> lt = query.getResultList();
        System.out.println(lt.size());
        List<TrajetDTO> ltd = new ArrayList<>();
        for(Trajet t :lt){
            ltd.add(new TrajetDTO(t));
        }
        return ltd;
    }

    @Override
    public List<TrajetDTO> dernierAjout() {

        List<Trajet>lt = em.createQuery("From Trajet t WHERE t.statut='aVenir' ORDER BY t.idTrajet DESC").setMaxResults(10).getResultList();
        List<TrajetDTO> ltd = new ArrayList<>();
        for(Trajet t :lt){
            ltd.add(new TrajetDTO(t));
        }
        return ltd;

    }
}
