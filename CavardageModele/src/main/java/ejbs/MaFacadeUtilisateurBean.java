package ejbs;

import entities.*;
import exceptions.DivisionParZeroException;
import exceptions.UtilisateurNonInscritException;
import exceptions.VilleNonTrouvee;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.*;

@Stateless(name = "UtilisateurBean")
public class MaFacadeUtilisateurBean implements MaFacadeUtilisateur {

    @PersistenceContext(unitName = "monUnite")
    EntityManager em;

    @Override
    public Reservation reserverPlace(String login, int idTrajet, int nbPlaces, int idVilleArrivee) throws VilleNonTrouvee {
        Utilisateur utilisateur = em.find(Utilisateur.class, login);
        Trajet trajet = em.find(Trajet.class,idTrajet);
        List<Etape> etapes = trajet.getListeEtape();
        Reservation reservation = new Reservation();
        reservation.setStatut("attente");
        reservation.setNbPlace(nbPlaces);
        reservation.setTrajetReservation(trajet);
        reservation.setUtilisateurReservation(utilisateur);

        // Teste si la ville d'arrivée est une étape
        if(trajet.getVilleArrivee().getIdVille() != idVilleArrivee) {
            Etape arrivee = null;
            for (Etape etape : etapes) {
                if (etape.getIdEtape() == idVilleArrivee) {
                    arrivee = etape;
                }
            }
            if(null == arrivee){
                throw new VilleNonTrouvee();
            }
            reservation.setDescendA(arrivee);
        } else {
            reservation.setDescendA(null);
        }
        em.persist(reservation);
        return reservation;
    }

    @Override
    public Appreciation donnerAppreciation(String login, int idTrajet, String commentaire, int note) {
        Utilisateur donneNote = em.find(Utilisateur.class,login);
        Trajet trajet = em.find(Trajet.class,idTrajet);
        int idVehicule = trajet.getVehiculeTrajet().getIdVehicule();
        // Le faire sur la bonne table
        Query q = em.createQuery("FROM Personne p WHERE p.Vehicule:=vehicule");
        q.setParameter("vehicule", idVehicule);
        Utilisateur estNote = (Utilisateur) q.getSingleResult();
        Appreciation appreciation = new Appreciation();
        appreciation.setCommentaire(commentaire);
        appreciation.setNote(note);
        appreciation.setDonneNote(donneNote);
        appreciation.setNoteTrajet(trajet);
        appreciation.setEstNote(estNote);
        em.persist(appreciation);
        return appreciation;
    }

    @Override
    public List<Appreciation> avoirNotesTrajet(int idTrajet) {
        Query q = em.createQuery("FROM Appreciation a WHERE a.noteTrajet:=trajet");
        q.setParameter("trajet",idTrajet);
        List<Appreciation> appreciationListe = q.getResultList();
        return appreciationListe;
    }

    @Override
    public List<Appreciation> avoirNotesTotal(String login) {
        Utilisateur utilisateur = em.find(Utilisateur.class,login);
        List<Appreciation> appreciationListe = utilisateur.getEstNote();
        return appreciationListe;
    }

    @Override
    public float moyenneNotes(String login) throws DivisionParZeroException{
        Utilisateur utilisateur = em.find(Utilisateur.class,login);
        List<Appreciation> appreciations = utilisateur.getNote();
        int notes=0;
        for (Appreciation appreciation:appreciations) {
            notes+=appreciation.getNote();
        }
        int moyenne;
        if(appreciations.size() == 0){
            throw new DivisionParZeroException();
        } else {
            moyenne = notes / appreciations.size();
            return moyenne;
        }
    }

    @Override
    public Trajet proposerTrajet(int idVilleDepart, int idVilleArrivee, Map<Integer,Integer> villesPrix, String date, String heure, int idVehicule, int prix) {
        Vehicule vehicule = em.find(Vehicule.class,idVehicule);
        Ville depart = em.find(Ville.class,idVilleDepart);
        Ville arrivee = em.find(Ville.class,idVilleArrivee);
        Trajet trajet = new Trajet();

        // Liste des étapes
        List<Etape> etapeListe = new ArrayList<>();
        Etape etape;
        // On récupère tous les id des villes
        Set etapes = villesPrix.keySet();
        int etapeId;
        Iterator<Integer> it = etapes.iterator();
        // On itère sur tous les id de villes-étapes
        while(it.hasNext()){
            etapeId = it.next();
            //On crée chaque nouvelle étape
            etape = new Etape();
            etape.setTrajet(trajet);
            etape.setPrix(villesPrix.get(etapeId));
            Ville ville = em.find(Ville.class,etapeId);
            etape.setVilleEtape(ville);
            em.persist(etape);
            etapeListe.add(etape);
        }

        // On ajoute tous les élement du trajet
        trajet.setListeEtape(etapeListe);
        trajet.setDate(date);
        trajet.setHeure(heure);
        trajet.setVehiculeTrajet(vehicule);
        trajet.setVilleDepart(depart);
        trajet.setVilleArrivee(arrivee);
        trajet.setPrix(prix);
        trajet.setStatut("aVenir");
        em.persist(trajet);
        return trajet;
    }

    @Override
    public Vehicule ajouterVehicule(String login, String nomVehicule, String modele, int idGabarit, int nbPlaces) {
        Utilisateur utilisateur = em.find(Utilisateur.class,login);
        Gabarit gabarit = em.find(Gabarit.class,idGabarit);
        Vehicule vehicule = new Vehicule();
        vehicule.setGabarit(gabarit);
        vehicule.setModele(modele);
        vehicule.setNom(nomVehicule);
        vehicule.setNombrePlaces(nbPlaces);
        utilisateur.ajouterVehicule(vehicule);
        em.persist(vehicule);
        em.persist(utilisateur);
        return vehicule;
    }

    @Override
    public boolean annulerTrajet(String login, int idTrajet) {
        Utilisateur utilisateur = em.find(Utilisateur.class,login);
        Trajet trajet = em.find(Trajet.class,idTrajet);

        // On vérifie que c'est bien l'utilisateur qui a créé le trajet
        Vehicule vehicule = trajet.getVehiculeTrajet();
        if(!utilisateur.possedeVehicule(vehicule)) {
            return false;
        }
        trajet.setStatut("annule");
        Notification notification;
        String messageNotification = ("Le trajet "+trajet.getVilleDepart()+" - "+trajet.getVilleArrivee() + " du "+trajet.getDate()+" a été annulé");

        //Trouver tous les passagers et leur envoyer la notification
        Utilisateur passager;
        for(Reservation reservation : trajet.getListeReservation()){
            if(reservation.getStatut().equals("enAttente") || reservation.getStatut().equals("accepte")){
                passager = reservation.getUtilisateurReservation();
                notification = new Notification();
                notification.setMessage(messageNotification);
                passager.ajouterNotification(notification);
            }
        }
        return true;
    }

    @Override
    public List<Reservation> avoirReservations(String login, int idTrajet) {
        return null;
    }

    @Override
    public boolean refuserReservation(String login, int idReservation) {
        return false;
    }

    @Override
    public boolean accepterReservation(String login, int idreservation) {
        return false;
    }
}
