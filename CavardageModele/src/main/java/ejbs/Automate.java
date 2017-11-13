package ejbs;

import entities.Notification;
import entities.Utilisateur;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@SuppressWarnings("unchecked")
@DeclareRoles({"admin","utilisateur"})
@Stateless(name = "Automate")
public class Automate {

    /**
     * L'EntityManager
     */
    @PersistenceContext(unitName="monUnite")
    private EntityManager em;

    /**
     * Permet de créer une notification pour un utilisateur
     * @param login     Le login de l'utilisateur
     * @param message   Le message de la notification
     * @return          La notification créée
     */
    @RolesAllowed({"utilisateur","admin"})
    public Notification creerNotification(String login, String message) {
        Utilisateur utilisateur = em.find(Utilisateur.class,login);
        Notification notification = new Notification();
        notification.setMessage(message);
        em.persist(notification);
        utilisateur.ajouterNotification(notification);
        em.persist(utilisateur);
        return notification;
    }

    /**
     * Calcule le prix moyen des trajets correspondants à certaines villes
     * @param villeDepart   La ville de départ du trajet
     * @param villeArrivee  La vilel d'arrivée du trajet
     * @return              Le prix moyen du trajet
     */
    @RolesAllowed("utilisateur")
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
            prixMoyen /= (float)listePrix.size();

            return prixMoyen;
        }
    }

    /**
     * Teste si une date est postérieure à aujourd'hui
     * @param dateTest          La date à tester
     * @return                  true si la date est postérieure, alse si elle est antérieure
     * @throws ParseException   Si la date n'est pas dans un format valide
     */
    @PermitAll
    public boolean datePosterieure(String dateTest) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        format.setLenient(false);
        boolean result = false;
        Date current_date = new Date();
        Date date = format.parse(dateTest);
        Date temp = new Date(3600 * 1000);
        Date dateFinale = new Date(current_date.getTime() + temp.getTime());
        if (date.compareTo(dateFinale) >= 0) {
            result = true;
        }
        return result;
    }

    /**
     * Teste si une date est dans le bon format, et si elle est au moins une heure plus tard que la date actuelle
     * @param dateString        La date à tester
     * @return                  true si le test est passé, false sinon
     * @throws ParseException   Si le format de la date n'est pas valide
     */
    @PermitAll
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
        return dateFinale.compareTo(dateCourante)>=0;
    }

    /**
     * Permet de récupérer le hash SHA-512 en héxadécimal d'un mot de passe
     * @param message   Le message a hasher
     * @return          Le message hashé
     */
    @PermitAll
    public String recupererHash(String message){
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            Logger.getAnonymousLogger().log(Level.INFO,"L'algorithme de hashage n'a pas été trouvé");
        }
        if (null == messageDigest) {
            return null;
        }
        messageDigest.update(message.getBytes());
        byte messageBytes[] = messageDigest.digest();
        StringBuilder hashCodeBuffer = new StringBuilder();
        for (byte messageByte : messageBytes) {
            hashCodeBuffer.append(Integer.toString((messageByte & 0xff) + 0x100, 16).substring(1));
        }
        return hashCodeBuffer.toString();
    }
}
