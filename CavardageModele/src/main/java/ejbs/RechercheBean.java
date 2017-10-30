package ejbs;

import dtos.TrajetDTO;
import dtos.VilleDTO;
import entities.Trajet;
import entities.Ville;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Stateless(name = "RechercheBean")
public class RechercheBean {

    @PersistenceContext(unitName="monUnite")
    private EntityManager em;

    public RechercheBean() {
    }

    public List<TrajetDTO> rechercheTrajet(String villeDepart, String departementDepart, String villeArrivee,
                                           String departementArrivee, String date) {
        Query query = em.createQuery("SELECT DISTINCT t From Trajet t, Ville vd, Ville va, Etape e WHERE " +
                "t.villeDepart=vd and vd.nomVille=:villeDepart" +
                " and ((t.villeArrivee=va and va.nomVille=:villeArrivee) or" +
                " (e.villeEtape.nomVille=:villeArrivee and e.trajet=t)) " +
                "and t.date=:date");
        query.setParameter("villeDepart", villeDepart+"_"+departementDepart);
        query.setParameter("villeArrivee", villeArrivee+"_"+departementArrivee);
        query.setParameter("date",date);
        List<Trajet> lt = query.getResultList();
        List<TrajetDTO> ltd = new ArrayList<>();
        for(Trajet t :lt){
            ltd.add(new TrajetDTO(t));
        }
        return ltd;
    }

    public List<VilleDTO> getListeVillesDTO(){
        Query q = em.createQuery("From Ville v");
        List<Ville> listeVille = q.getResultList();
        if(!listeVille.isEmpty()){
            List<VilleDTO> villeDTOS = new ArrayList<>();
            for(Ville v : listeVille){
                villeDTOS.add(new VilleDTO(v.getNomVille()));
            }
            return villeDTOS;
        }
        return new ArrayList<>();
    }

    public List<Ville> getListeVilles() {
        Query q = em.createQuery("From Ville v");
        List<Ville> listeTemp = q.getResultList();
        if(!listeTemp.isEmpty()){
            return listeTemp;
        }
        return new ArrayList<>();
    }
}
