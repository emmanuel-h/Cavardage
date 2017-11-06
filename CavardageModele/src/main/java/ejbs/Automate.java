package ejbs;

import dtos.PrixMoyenDTO;
import entities.Notification;
import entities.Trajet;
import entities.Utilisateur;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Stateless(name = "Automate")
public class Automate {

    @PersistenceContext(unitName="monUnite")
    private EntityManager em;

    public Notification creerNotification(String login, String message) {
        Utilisateur utilisateur = em.find(Utilisateur.class,login);
        Notification notification = new Notification();
        notification.setMessage(message);
        em.persist(notification);
        utilisateur.ajouterNotification(notification);
        em.persist(utilisateur);
        return notification;
    }

    public List<PrixMoyenDTO> prixMoyen(){
        Query q = em.createQuery("FROM Trajet t WHERE t.statut=:statutFini OR t.statut=:statutAVenir");
        q.setParameter("statutFini", "fini");
        q.setParameter("statutAVenir", "aVenir");
        List<Trajet> listeTrajets = q.getResultList();

        List<PrixMoyenDTO> listePrixMoyen = new ArrayList<>();
        boolean update = false;
        int listePrixMoyenSize = 0;
        for(Trajet t : listeTrajets){
            for(int i = 0; i < listePrixMoyenSize; i++){
                PrixMoyenDTO p = listePrixMoyen.get(i);
                if(t.getVilleDepart().getNomVille().equals(p.getVille1()) && t.getVilleArrivee().getNomVille().equals(p.getVille2())){
                    p.setPrix((p.getPrix() + t.getPrix()) / 2f);
                    update = true;
                    break;
                }
            }
            if(!update){
                PrixMoyenDTO p = new PrixMoyenDTO(t.getVilleDepart().getNomVille(), t.getVilleArrivee().getNomVille(), t.getPrix());
                listePrixMoyen.add(p);
                listePrixMoyenSize++;
            }
            update = false;
        }

        return listePrixMoyen;

    }

    public float prixMoyen(String villeDepart, String villeArrivee){
        Query q = em.createQuery("SELECT t.prix FROM Trajet t where t.villeDepart.nomVille=:villeDepart AND t.villeArrivee.nomVille=:villeArrivee");
        q.setParameter("villeDepart", villeDepart);
        q.setParameter("villeArrivee", villeArrivee);
        List<Integer> listePrix = q.getResultList();

        if(listePrix.isEmpty()){
            return -1;
        }else {
            int prixMoyen = 0;
            for (int n : listePrix) {
                prixMoyen += n;
            }
            prixMoyen /= listePrix.size();

            return prixMoyen;
        }
    }

    public float prixMoyen(Trajet t){
        return prixMoyen(t.getVilleDepart().getNomVille(), t.getVilleArrivee().getNomVille());
    }
}
