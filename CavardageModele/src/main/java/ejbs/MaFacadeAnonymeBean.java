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

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.text.SimpleDateFormat;
import java.util.*;

@Stateless(name = "AnonymeBean")
public class MaFacadeAnonymeBean implements MaFacadeAnonyme {

    @PersistenceContext(unitName="monUnite")
    EntityManager em;
    @EJB
    RechercheBean rechercheBean;

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
    public List<TrajetDTO> rechercheTrajet(String villeDepart, String departementDepart, String villeArrive, String departementArrive, String date, String prix) {
        return rechercheBean.rechercheTrajet(villeDepart,departementDepart,villeArrive,departementArrive,date,prix);
    }

    @Override
    public List<TrajetDTO> dernierAjout() {

        List<Trajet>lt = em.createQuery("From Trajet t WHERE t.statut='aVenir' ORDER BY t.idTrajet DESC").setMaxResults(10).getResultList();
        List<TrajetDTO> ltd = new ArrayList<>();
        for(Trajet t :lt){
            ltd.add(new TrajetDTO(t));
        }
        Collections.sort(ltd, new Comparator<TrajetDTO>() {
            @Override
            public int compare(TrajetDTO o1, TrajetDTO o2) {

                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                int result=0;
                try {
                    Date date1 = format.parse(o1.getDate()+" "+o1.getHeure());
                    Date date2 = format.parse(o2.getDate()+" "+o2.getHeure());
                    if (date1.compareTo(date2) > 0) {
                        result=1;
                        System.out.println(date1+" is after "+date2);
                    } else if (date1.compareTo(date2) < 0) {
                        result=-1;
                        System.out.println(date1+" is before end "+date2);
                    } else if (date1.compareTo(date2) == 0) {
                        result=0;
                        System.out.println(date1+" is equal to "+date2);
                    }
                    }catch(Exception e){
                    e.printStackTrace();
                    System.out.println(e.toString());
                }
                return result;
            }
        });
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
}
