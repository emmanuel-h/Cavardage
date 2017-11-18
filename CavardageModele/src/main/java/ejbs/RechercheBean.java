package ejbs;

import dtos.TrajetDTO;
import dtos.VilleDTO;
import entities.Trajet;
import entities.Ville;
import exceptions.DateAnterieureException;
import exceptions.VilleNonTrouvee;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@SuppressWarnings("unchecked")
@DeclareRoles({"admin","utilisateur"})
@Stateless(name = "RechercheBean")
public class RechercheBean {

    /**
     * L'entityManager
     */
    @PersistenceContext(unitName="monUnite")
    private EntityManager em;

    /**
     * L'automate
     */
    @EJB
    private Automate automate;

    /**
     * Effectue une recherche de trajet suivant des caractéristiques
     * @param villeDepart               La ville de départ du trajet
     * @param departementDepart         Le département de la ville de départ
     * @param villeArrivee              La ville d'arrivée du trajet
     * @param departementArrivee        Le département de la vilel d'arrivée
     * @param date                      La date du trajet
     * @param prix                      Le prix maximum (non obligatoire)
     * @return                          La liste des trajtes correspondants aux critères de recherche
     * @throws ParseException           Si la date du trajet n'est pas dans un format acceptable
     * @throws DateAnterieureException  Si la date est antérieure à aujourd'hui
     */
    @PermitAll
    public List<TrajetDTO> rechercheTrajet(String villeDepart, String departementDepart, String villeArrivee,
                                           String departementArrivee, String date, String prix) throws ParseException, DateAnterieureException, VilleNonTrouvee {

            verificationDateVille(date,villeDepart,departementDepart,villeArrivee,departementArrivee);
            String mq="SELECT DISTINCT t From Trajet t, Etape e WHERE " +
                    "t.villeDepart.nomVille=:villeDepart" +
                    " and ((t.villeArrivee.nomVille=:villeArrivee) or" +
                    " (e.villeEtape.nomVille=:villeArrivee and e.trajet=t)) " +
                    "and t.date>=:date and t.statut='aVenir' ORDER BY t.date,t.heure";
            Query query;
            if(null != prix && !prix.equals("")){
                if(Integer.parseInt(prix)>0 ) {
                    query=em.createQuery("SELECT DISTINCT t From Trajet t, Etape e WHERE " +
                            "t.villeDepart.nomVille=:villeDepart" +
                            " and ((t.villeArrivee.nomVille=:villeArrivee and t.prix<= :prix) or" +
                            " (e.villeEtape.nomVille=:villeArrivee and e.trajet=t and e.prix<= :prix)) " +
                            "and t.date>=:date and t.statut='aVenir' ORDER BY t.date,t.heure");
                    query.setParameter("prix",Integer.parseInt(prix));
                }else{
                    query=em.createQuery(mq);
                }
            }else{
                query=em.createQuery(mq);
            }
            query.setParameter("villeDepart", villeDepart+"_"+departementDepart);
            query.setParameter("villeArrivee", villeArrivee+"_"+departementArrivee);
            query.setParameter("date",automate.stringToDate(date));
            query.setMaxResults(10);
            List<Trajet> lt = query.getResultList();
            List<TrajetDTO> ltd = new ArrayList<>();
            for(Trajet t :lt){
                ltd.add(new TrajetDTO(t));
            }
            return ltd;

    }
    @PermitAll
    public List<TrajetDTO> rechercheTrajetHeure(String villeDepart, String departementDepart, String villeArrivee,
                                           String departementArrivee, String date, String heure, String prix) throws ParseException, DateAnterieureException, VilleNonTrouvee {

        SimpleDateFormat formatDTO = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatBase = new SimpleDateFormat("yyyy-MM-dd");
        Date dateBase = formatDTO.parse(date);
        formatBase.format(dateBase);
        String dateCorrect = formatBase.format(dateBase);
        verificationDateVille(dateCorrect,villeDepart,departementDepart,villeArrivee,departementArrivee);
        String mq="SELECT DISTINCT t From Trajet t, Etape e WHERE " +
                "t.villeDepart.nomVille=:villeDepart" +
                " and ((t.villeArrivee.nomVille=:villeArrivee) or" +
                " (e.villeEtape.nomVille=:villeArrivee and e.trajet=t)) " +
                "and ((t.date>:date) or (t.heure>:heure and t.date=:date)) and t.statut='aVenir' ORDER BY t.date,t.heure";
        Query query;
        if(null != prix && !prix.equals("")){
            if(Integer.parseInt(prix)>0 ) {
                query=em.createQuery("SELECT DISTINCT t From Trajet t, Etape e WHERE " +
                        "t.villeDepart.nomVille=:villeDepart" +
                        " and ((t.villeArrivee.nomVille=:villeArrivee and t.prix<= :prix) or" +
                        " (e.villeEtape.nomVille=:villeArrivee and e.trajet=t and e.prix<= :prix)) " +
                        "and ((t.date>:date) or (t.heure>:heure and t.date=:date)) and t.statut='aVenir' ORDER BY t.date,t.heure");
                query.setParameter("prix",Integer.parseInt(prix));
            }else{
                query=em.createQuery(mq);
            }
        }else{
            query=em.createQuery(mq);
        }
        query.setParameter("villeDepart", villeDepart+"_"+departementDepart);
        query.setParameter("villeArrivee", villeArrivee+"_"+departementArrivee);
        query.setParameter("date",automate.stringToDate(dateCorrect));
        query.setParameter("heure",automate.stringToTime(heure+":00"));
        query.setMaxResults(10);
        List<Trajet> lt = query.getResultList();
        List<TrajetDTO> ltd = new ArrayList<>();
        for(Trajet t :lt){
            ltd.add(new TrajetDTO(t));
        }
        return ltd;

    }

    private boolean verificationDateVille(String date, String villeDepart, String departementDepart, String villeArrivee, String departementArrivee) throws VilleNonTrouvee, DateAnterieureException, ParseException {
        if(!automate.testDate(date)){
            throw new DateAnterieureException("Vous ne pouvez pas rechercher un trajet à une date antérieure");
        }
        Query q = em.createQuery("SELECT v FROM Ville v where v.nomVille=:villeDepart or v.nomVille=:villeArrivee");
        q.setParameter("villeDepart", villeDepart + "_" + departementDepart);
        q.setParameter("villeArrivee", villeArrivee + "_" + departementArrivee);
        List<Ville> listVilles = q.getResultList();
        int size = listVilles.size();
        if(villeDepart.equals(villeArrivee) && departementDepart.equals(departementArrivee)){
            throw new VilleNonTrouvee("Les deux villes sont identiques");
        }
        if(2 != size){
            throw new VilleNonTrouvee("Une des villes recherchée n'existe pas");
        }
        return true;
    }


    /**
     * Renvoie la liste des villes existantes dans la base de données
     * @return  La liste des villes
     */
    @PermitAll
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
}
