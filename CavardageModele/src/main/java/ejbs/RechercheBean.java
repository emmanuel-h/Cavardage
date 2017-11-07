package ejbs;

import dtos.TrajetDTO;
import dtos.VilleDTO;
import entities.Trajet;
import entities.Ville;
import exceptions.DatePosterieureException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@SuppressWarnings("unchecked")

@Stateless(name = "RechercheBean")
public class RechercheBean {

    @PersistenceContext(unitName="monUnite")
    private EntityManager em;
    @EJB
    Automate automate;

    public RechercheBean() {
    }

    public List<TrajetDTO> rechercheTrajet(String villeDepart, String departementDepart, String villeArrivee,
                                           String departementArrivee, String date, String prix) throws ParseException, DatePosterieureException {

            if(!automate.testDate(date)){
                throw new DatePosterieureException("Vous ne pouvez pas rechercher un trajet à une date antérieure");
            }
            String mq="SELECT DISTINCT t From Trajet t, Etape e WHERE " +
                    "t.villeDepart.nomVille=:villeDepart" +
                    " and ((t.villeArrivee.nomVille=:villeArrivee) or" +
                    " (e.villeEtape.nomVille=:villeArrivee and e.trajet=t)) " +
                    "and t.date=:date and t.statut='aVenir'";
            Query query;
            if(null != prix && !prix.equals("")){
                if(Integer.parseInt(prix)>0 ) {
                    query=em.createQuery("SELECT DISTINCT t From Trajet t, Etape e WHERE " +
                            "t.villeDepart.nomVille=:villeDepart" +
                            " and ((t.villeArrivee.nomVille=:villeArrivee and t.prix<= :prix) or" +
                            " (e.villeEtape.nomVille=:villeArrivee and e.trajet=t and e.prix<= :prix)) " +
                            "and t.date=:date and t.statut='aVenir'");
                    query.setParameter("prix",Integer.parseInt(prix));
                }else{
                    query=em.createQuery(mq);
                }
            }else{
                query=em.createQuery(mq);
            }
            query.setParameter("villeDepart", villeDepart+"_"+departementDepart);
            query.setParameter("villeArrivee", villeArrivee+"_"+departementArrivee);
            query.setParameter("date",date);
            List<Trajet> lt = query.getResultList();
            List<TrajetDTO> ltd = new ArrayList<>();
            for(Trajet t :lt){
                ltd.add(new TrajetDTO(t));
            }
            Collections.sort(ltd);
            return ltd;

    }

    public List<VilleDTO> getListeVillesDTO(){
        Query q = em.createQuery("SELECT v FROM Ville v");
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
        Query q = em.createQuery("SELECT v FROM Ville v");
        List<Ville> listeTemp = q.getResultList();
        if(!listeTemp.isEmpty()){
            return listeTemp;
        }
        return new ArrayList<>();
    }
}
