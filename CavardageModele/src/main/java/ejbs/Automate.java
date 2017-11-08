package ejbs;

import dtos.PrixMoyenDTO;
import entities.Notification;
import entities.Trajet;
import entities.Utilisateur;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@SuppressWarnings("unchecked")

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
        Query q = em.createQuery("SELECT t FROM Trajet t WHERE t.statut=:statutFini OR t.statut=:statutAVenir");
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
        System.out.println("1");
        Query q = em.createQuery("SELECT t.prix FROM Trajet t where t.villeDepart.nomVille=:villeDepart AND t.villeArrivee.nomVille=:villeArrivee");
        q.setParameter("villeDepart", villeDepart);
        q.setParameter("villeArrivee", villeArrivee);
        System.out.println("2");
        List<Integer> listePrix = q.getResultList();
        System.out.println("3");
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

    public boolean datePosterieure(String dateTest) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        format.setLenient(false);
        boolean result = false;
        Date current_date = new Date();
        String dateCourante = format.format(current_date);
        Date date = format.parse(dateTest);
        Date temp = new Date(3600 * 1000);
        Date dateFinale = new Date(current_date.getTime() + temp.getTime());
        System.out.println("date 1 : " + dateTest);
        System.out.println("date 2 : " + dateCourante);
        System.out.println("date 3 : " + dateFinale);
        if (date.compareTo(dateFinale) >= 0) {
            result = true;
        }
        return result;
    }

    public boolean testDate(String dateString) throws ParseException {

        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        formatDate.setLenient(false);
        Date dateCourante = new Date();
        SimpleDateFormat formatHeure = new SimpleDateFormat("HH:mm");
        Date heureCourante = new Date();
        String heure2 = formatHeure.format(heureCourante);
        formatDate.format(dateCourante);
        Date dateTest = formatDate.parse(dateString+" "+heure2);
        Date temp = new Date(3600 * 1000);
        Date dateFinale = new Date(dateTest.getTime() + temp.getTime());
        System.out.println("date courante "+ dateCourante);
        System.out.println("date test "+ dateTest);
        return dateFinale.compareTo(dateCourante)>=0;

    }
}
