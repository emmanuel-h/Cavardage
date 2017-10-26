package ejbs;

import dtos.HistoriqueDTO;
import entities.*;
import exceptions.DivisionParZeroException;
import exceptions.PasConducteurException;
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
        reservation.setStatut("enAttente");
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

        Vehicule vehicule = trajet.getVehiculeTrajet();
        Utilisateur conducteur = trouverUtilisateur(vehicule);
        Notification notification = new Notification();
        String messageNotification = "Une nouvelle réservation est arrivée pour le trajet "+trajet.getVilleDepart().getNomVille()+" - "+trajet.getVilleArrivee().getNomVille();
        notification.setMessage(messageNotification);
        conducteur.ajouterNotification(notification);
        em.persist(notification);
        em.persist(conducteur);
        return reservation;
    }

    @Override
    public Appreciation donnerAppreciation(String login, int idTrajet, String commentaire, int note) {
        Utilisateur donneNote = em.find(Utilisateur.class,login);
        Trajet trajet = em.find(Trajet.class,idTrajet);

        // Le faire sur la bonne table
        Vehicule vehicule = trajet.getVehiculeTrajet();
        Utilisateur estNote = trouverUtilisateur(vehicule);
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
    public boolean annulerTrajet(String login, int idTrajet) throws PasConducteurException{
        Utilisateur utilisateur = em.find(Utilisateur.class,login);
        Trajet trajet = em.find(Trajet.class,idTrajet);

        // On vérifie que c'est bien l'utilisateur qui a créé le trajet
        verifierUtilisateurEstConducteur(utilisateur,trajet);

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
                em.persist(notification);
                em.persist(passager);
            }
        }
        return true;
    }

    @Override
    public List<Reservation> avoirReservationsEnAttente(String login, int idTrajet) throws PasConducteurException {
        Utilisateur utilisateur = em.find(Utilisateur.class,login);
        Trajet trajet = em.find(Trajet.class,idTrajet);

        verifierUtilisateurEstConducteur(utilisateur,trajet);

        List<Reservation> reservationListe = new ArrayList<>();
        for(Reservation reservation : trajet.getListeReservation()){
            if(reservation.getStatut().equals("enAttente")){
                reservationListe.add(reservation);
            }
        }
        return reservationListe;
    }

    @Override
    public boolean refuserReservation(String login, int idReservation) throws PasConducteurException {
        Reservation reservation = em.find(Reservation.class,idReservation);
        String statut="refuse";
        String messageNotification = "Votre réservation pour le trajet "+reservation.getTrajetReservation().getVilleDepart().getNomVille()+" - "+reservation.getDescendA().getVilleEtape().getNomVille()+" a été refusée";
        gererReservation(login,reservation,messageNotification,statut);
        return true;
    }

    @Override
    public boolean accepterReservation(String login, int idReservation) throws PasConducteurException{
        Reservation reservation = em.find(Reservation.class,idReservation);
        String messageNotification = "Votre réservation pour le trajet "+reservation.getTrajetReservation().getVilleDepart().getNomVille()+" - "+reservation.getDescendA().getVilleEtape().getNomVille()+" a été acceptée";
        String statut="accepte";
        gererReservation(login,reservation,messageNotification,statut);
        return true;
    }

    @Override
    public boolean supprimerNotification(String login, int idNotification) {
        Utilisateur utilisateur = em.find(Utilisateur.class,login);
        Notification notification = em.find(Notification.class,idNotification);
        return utilisateur.supprimerNotification(notification);
    }

    private Utilisateur trouverUtilisateur(Vehicule vehicule){
        Query q = em.createQuery("FROM Utilisateur u WHERE u.Vehicule:=vehicule");
        q.setParameter("vehicule", vehicule.getIdVehicule());
        Utilisateur utilisateur = (Utilisateur) q.getSingleResult();
        return utilisateur;
    }

    private boolean verifierUtilisateurEstConducteur(Utilisateur utilisateur, Trajet trajet) throws PasConducteurException{
        Vehicule vehicule = trajet.getVehiculeTrajet();
        if(!utilisateur.possedeVehicule(vehicule)) {
            throw new PasConducteurException();
        } else {
            return true;
        }
    }

    private void gererReservation(String login, Reservation reservation, String messageNotification, String statut) throws PasConducteurException{
        Utilisateur utilisateur = em.find(Utilisateur.class,login);
        verifierUtilisateurEstConducteur(utilisateur,reservation.getTrajetReservation());
        reservation.setStatut(statut);
        Utilisateur passager = reservation.getUtilisateurReservation();
        Notification notification = new Notification();
        notification.setMessage(messageNotification);
        passager.ajouterNotification(notification);
        em.persist(notification);
        em.persist(passager);
    }

    public List<HistoriqueDTO> historiqueUtilisateur(String login){
        Utilisateur utilisateur = em.find(Utilisateur.class, login);
        List<HistoriqueDTO> listeHisto = new ArrayList<>();
        for(Vehicule v : utilisateur.getListeVehicule()){
            for(Trajet t : v.getListeTrajet()){
                if(t.getStatut().equals("fini")){
                    HistoriqueDTO hist = new HistoriqueDTO(t.getIdTrajet(), v.getNom(), 0,
                            "conducteur", t.getVilleDepart().getNomVille(),
                            t.getVilleArrivee().getNomVille(), t.getDate());
                    listeHisto.add(hist);
                }
            }
        }
        for(Reservation v : utilisateur.getListeReservation()){
            if(v.getTrajetReservation().getStatut().equals("fini") && v.getStatut().equals("accepte")){
                Trajet t = v.getTrajetReservation();
                HistoriqueDTO hist = new HistoriqueDTO(t.getIdTrajet(), "", v.getNbPlace(),
                        "passager", t.getVilleDepart().getNomVille(), t.getVilleArrivee().getNomVille(), t.getDate());
                listeHisto.add(hist);
            }
        }
        //A faire
        List<HistoriqueDTO>listeTest = new ArrayList<>();
        return listeHisto;
    }
}
