package ejbs;

import dtos.HistoriqueDTO;
import dtos.VehiculeDTO;
import dtos.VilleDTO;
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
        if(!trajet.getVilleArrivee().getNomVille().equals(idVilleArrivee)) {
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
    public void proposerTrajet(String idVilleDepart, String idVilleArrivee, Map<String,Integer> villesPrix, String date, String heure, int idVehicule, int prix) {
        Vehicule vehicule = em.find(Vehicule.class,idVehicule);
        Ville depart = em.find(Ville.class,idVilleDepart);
        Ville arrivee = em.find(Ville.class,idVilleArrivee);
        Trajet trajet = new Trajet(date,heure);

        // Liste des étapes
        if(!villesPrix.isEmpty()) {
            System.out.println("Il y a des étapes");
            List<Etape> etapeListe = new ArrayList<>();
            Etape etape;
            // On récupère tous les id des villes
            Set etapes = villesPrix.keySet();
            String etapeId;
            Iterator<String> it = etapes.iterator();
            // On itère sur tous les id de villes-étapes
            while (it.hasNext()) {
                etapeId = it.next();
                //On crée chaque nouvelle étape
                etape = new Etape();
                etape.setTrajet(trajet);
                System.out.println(villesPrix.get(etapeId));
                etape.setPrix(villesPrix.get(etapeId));
                Ville ville = em.find(Ville.class, etapeId);
                etape.setVilleEtape(ville);
                em.persist(etape);
                etapeListe.add(etape);
            }
            trajet.setListeEtape(etapeListe);
        }

        // On ajoute tous les élement du trajet
        trajet.setDate(date);
        trajet.setHeure(heure);
        trajet.setVehiculeTrajet(vehicule);
        trajet.setVilleDepart(depart);
        trajet.setVilleArrivee(arrivee);
        trajet.setPrix(prix);
        trajet.setStatut("aVenir");
        em.persist(trajet);
        //return trajet;
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
    public boolean supprimerVehicule(int idVehicule){
        Vehicule v = em.find(Vehicule.class, idVehicule);
        em.remove(v);
        return true;
    }

    @Override
    public List<VehiculeDTO> listeVehicules(String login){
        Utilisateur utilisateur = em.find(Utilisateur.class, login);
        List<VehiculeDTO> listeVehicules = new ArrayList<>();
        for(Vehicule v : utilisateur.getListeVehicule()){
            VehiculeDTO vDTO = new VehiculeDTO(v.getIdVehicule(), v.getModele(), v.getNom(), v.getGabarit().getType(), v.getNombrePlaces());
            listeVehicules.add(vDTO);
        }
        return listeVehicules;
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

    @Override
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

        HistoriqueDTO hist1 = new HistoriqueDTO(1, "clio", 0, "conducteur", "Orleans", "Tours", "26/10/2017");
        HistoriqueDTO hist2 = new HistoriqueDTO(2, "", 2, "passager", "Paris", "Marseille", "15/10/2017");

        listeHisto.add(hist1);
        listeHisto.add(hist2);

        return listeHisto;
    }

    @Override
    public List<Gabarit> listeGabarits(){
        Query q = em.createQuery("FROM Gabarit g");
        List<Gabarit> gabaritListe = q.getResultList();
        return gabaritListe;
    }

    @Override
    public boolean changerMotDePasse(String login, String motDePasse) {
        Utilisateur utilisateur = em.find(Utilisateur.class,login);
        if(utilisateur.getMotDePasse().equals(motDePasse)){
            return false;
        } else {
            utilisateur.setMotDePasse(motDePasse);
            return true;
        }
    }

    @Override
    public boolean verifierMotDePasse(String login, String motDePasse) {
        Utilisateur utilisateur = em.find(Utilisateur.class,login);
        return utilisateur.getMotDePasse().equals(motDePasse);
    }

    @Override
    public boolean supprimerUtilisateur(String login) {
        Utilisateur utilisateur = em.find(Utilisateur.class,login);
        if(null != utilisateur) {
            em.remove(utilisateur);
            return true;
        } else {
            return false;
        }
    }

    private void gererReservation(String login, Reservation reservation, String messageNotification, String statut) throws PasConducteurException {
        Utilisateur utilisateur = em.find(Utilisateur.class, login);
        verifierUtilisateurEstConducteur(utilisateur, reservation.getTrajetReservation());
        reservation.setStatut(statut);
        Utilisateur passager = reservation.getUtilisateurReservation();
        Notification notification = new Notification();
        notification.setMessage(messageNotification);
        passager.ajouterNotification(notification);
        em.persist(notification);
        em.persist(passager);
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

    public List<Ville> getListeVilles(){
        Query q = em.createQuery("From Ville v");
        List<Ville> listeTemp = q.getResultList();
        if(!listeTemp.isEmpty()){
            return listeTemp;
        }
        return new ArrayList<>();
    }

    public List<VilleDTO> getListeVilleDTO(){
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

    @Override
    public void preAjoutVille(String login, String villeDepart, String villeArrivee, String nomVehicule, String[] etapes, String date, String heure, String prix) {
        Utilisateur user = em.find(Utilisateur.class, login);
        List<Vehicule> vListe = user.getListeVehicule();
        int idVehicule = 0;
        for(Vehicule v : vListe){
            if(v.getNom().equals(nomVehicule)){
                idVehicule = v.getIdVehicule();
                break;
            }
        }

        StringTokenizer st;
        Map<String, Integer> mapPrix = new TreeMap<>();
        String nomVille;
        if(null != etapes) {
            for (int i = 0; i < etapes.length; i++) {
                st = new StringTokenizer(etapes[i], " -");
                nomVille = st.nextToken() + "_" + st.nextToken();
                prix = st.nextToken();
                mapPrix.put(nomVille, Integer.parseInt(prix));
            }
        }
        st = new StringTokenizer(villeDepart, " -");
        String idVilleDepart = st.nextToken() + "_" + st.nextToken();
        st = new StringTokenizer(villeArrivee, " -");
        String idVilleArrivee = st.nextToken() + "_" + st.nextToken();
        proposerTrajet(idVilleDepart, idVilleArrivee, mapPrix, date, heure, idVehicule, Integer.parseInt(prix));
    }
}
