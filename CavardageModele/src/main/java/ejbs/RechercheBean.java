package ejbs;

import dtos.TrajetDTO;
import dtos.VilleDTO;
import entities.Trajet;
import entities.Ville;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.text.SimpleDateFormat;
import java.util.*;

@Stateless(name = "RechercheBean")
public class RechercheBean {

    @PersistenceContext(unitName="monUnite")
    private EntityManager em;

    public RechercheBean() {
    }

    public List<TrajetDTO> rechercheTrajet(String villeDepart, String departementDepart, String villeArrivee,
                                           String departementArrivee, String date, String prix) {
            String mq="SELECT DISTINCT t From Trajet t, Ville vd, Ville va, Etape e WHERE " +
                    "t.villeDepart=vd and vd.nomVille=:villeDepart" +
                    " and ((t.villeArrivee=va and va.nomVille=:villeArrivee) or" +
                    " (e.villeEtape.nomVille=:villeArrivee and e.trajet=t)) " +
                    "and t.date=:date and t.statut='aVenir'";
            Query query=null;
            if(null != prix && !prix.equals("")){
                if(Integer.parseInt(prix)>0 ) {
                    query=em.createQuery("SELECT DISTINCT t From Trajet t, Ville vd, Ville va, Etape e WHERE " +
                            "t.villeDepart=vd and vd.nomVille=:villeDepart" +
                            " and ((t.villeArrivee=va and va.nomVille=:villeArrivee and t.prix<= :prix) or" +
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
