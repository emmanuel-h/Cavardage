package ejbs;

import dtos.*;
import entities.*;
import exceptions.*;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@SuppressWarnings("unchecked")
@DeclareRoles({"admin","utilisateur"})
@Stateless(name = "UtilisateurBean")
public class MaFacadeUtilisateurBean implements MaFacadeUtilisateur {

    @PersistenceContext(unitName = "monUnite")
    private EntityManager em;
    @EJB
    private RechercheBean recherche;
    @EJB
    private Automate automate;

    @RolesAllowed("utilisateur")
    @Override
    public Reservation reserverPlace(String login, int idTrajet, int nbPlaces, String idVilleArrivee) throws VilleNonTrouvee, AccesInterditException {
        Utilisateur utilisateur = em.find(Utilisateur.class, login);
        Trajet trajet = em.find(Trajet.class,idTrajet);
        if(!trajet.getStatut().equals("aVenir")){
            throw new AccesInterditException("Le trajet que vous essayez de réserver est déjà fini");
        }
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
                if (etape.getVilleEtape().getNomVille().equals(idVilleArrivee)) {
                    arrivee = etape;
                }
            }
            if(null == arrivee){
                throw new VilleNonTrouvee("La ville d'arrivée n'a pas été trouvée");
            }
            reservation.setDescendA(arrivee);
        } else {
            reservation.setDescendA(null);
        }
        em.persist(reservation);

        Vehicule vehicule = trajet.getVehiculeTrajet();
        Utilisateur conducteur = vehicule.getUtilisateur();
        Notification notification = new Notification();
        String messageNotification = "Une nouvelle réservation est arrivée pour le trajet "+trajet.getVilleDepart().getNomPropre()+" - "+trajet.getVilleArrivee().getNomPropre();
        notification.setMessage(messageNotification);
        conducteur.ajouterNotification(notification);
        em.persist(notification);
        em.persist(conducteur);
        return reservation;
    }

    @RolesAllowed("utilisateur")
    @Override
    public Appreciation donnerAppreciation(String login, int idTrajet, String commentaire, int note, String loginDestinataire) {
        Utilisateur donneNote = em.find(Utilisateur.class,login);
        Utilisateur destinataireNote = em.find(Utilisateur.class,loginDestinataire);
        Trajet trajet = em.find(Trajet.class,idTrajet);
        // Le faire sur la bonne table
        Appreciation appreciation = new Appreciation();
        appreciation.setCommentaire(commentaire);
        appreciation.setNote(note);
        appreciation.setDonneNote(donneNote);
        appreciation.setNoteTrajet(trajet);
        appreciation.setEstNote(destinataireNote);
        em.persist(appreciation);
        donneNote.ajouterNoteEnvoyee(appreciation);
        em.persist(donneNote);
        destinataireNote.ajouterNoteRecue(appreciation);
        em.persist(destinataireNote);

        creerNotification(loginDestinataire,"Vous avez reçu une appréciation concernant le trajet partant de "+trajet.getVilleDepart().getNomPropre()
                +" le "+trajet.getDate()+" de l'utilisateur " +donneNote.getNom());

        creerNotification(login,"Votre appréciation concernant le trajet partant de "+trajet.getVilleDepart().getNomPropre()
                +" le "+trajet.getDate()+" pour l'utilisateur " +destinataireNote.getNom()+ " a bien été envoyée");

        return appreciation;
    }

    @RolesAllowed("utilisateur")
    @Override
    public List<AppreciationDTO> avoirToutesAppreciations(String login) {
        Utilisateur utilisateur = em.find(Utilisateur.class,login);
        List<Appreciation> appreciationListe = utilisateur.getEstNote();
        List<AppreciationDTO> appreciationDTOList = new ArrayList<>();
        for (Appreciation appreciation : appreciationListe){
            appreciationDTOList.add(new AppreciationDTO(appreciation));
        }
        return appreciationDTOList;
    }

    @RolesAllowed("utilisateur")
    @Override
    public float moyenneNotes(String login) throws DivisionParZeroException{
        Utilisateur utilisateur = em.find(Utilisateur.class,login);
        List<Appreciation> appreciations = utilisateur.getEstNote();
        int notes=0;
        for (Appreciation appreciation:appreciations) {
            notes+=appreciation.getNote();
        }
        int moyenne;
        if(appreciations.size() == 0){
            throw new DivisionParZeroException("Il n'y a pas eu d'appreciations pour le moment");
        } else {
            moyenne = notes / appreciations.size();
            int moyenne_int = moyenne*10;
            return moyenne_int/10;
        }
    }

    @RolesAllowed("utilisateur")
    @Override
    public Vehicule ajouterVehicule(String login, String nomVehicule, String modele, String gabarit, int nbPlaces) throws VehiculeDejaExistantException, GabaritException {
        Utilisateur utilisateur = em.find(Utilisateur.class, login);
        for(Vehicule v : utilisateur.getListeVehicule()){
            if(v.getNom().equals(nomVehicule)){
                throw new VehiculeDejaExistantException("Vous avez déjà un véhicule portant ce nom");
            }
        }
        Query q = em.createQuery("SELECT g FROM Gabarit g where g.type=:gabarit");
        q.setParameter("gabarit", gabarit);
        try {
            Gabarit g = (Gabarit) q.getSingleResult();
            Vehicule vehicule = new Vehicule();
            vehicule.setGabarit(g);
            vehicule.setModele(modele);
            vehicule.setNom(nomVehicule);
            vehicule.setNombrePlaces(nbPlaces);
            vehicule.setUtilisateur(utilisateur);
            vehicule.setStatut("actif");
            boolean nomPris = utilisateur.ajouterVehicule(vehicule);
            if(!nomPris){
                throw new VehiculeDejaExistantException("Vous avez déjà un véhicule portant ce nom");
            }
            em.persist(vehicule);
            em.persist(utilisateur);

            creerNotification(login,"Le véhicule "+nomVehicule+" a bien été ajouté à votre liste de véhicules");

            return vehicule;
        }catch (Exception e){
            throw new GabaritException("gabarit non existant");
        }
    }

    @RolesAllowed("utilisateur")
    @Override
    public boolean supprimerVehicule(String login, int idVehicule) throws PasVehiculeUtilisateur {
        Query query = em.createQuery("SELECT v FROM Vehicule v WHERE v.idVehicule=:idVehicule and v.utilisateur.login=:login and v.statut='actif'");
        query.setParameter("login",login);
        query.setParameter("idVehicule",idVehicule);
        try{
            Vehicule v = (Vehicule) query.getSingleResult();
            v.setStatut("supprime");
            em.persist(v);
            query = em.createQuery("SELECT t FROM Trajet t WHERE t.vehiculeTrajet=:vehicule and t.statut='aVenir'");
            query.setParameter("vehicule",v);
            List<Trajet> listTrajets = query.getResultList();
            for (Trajet t : listTrajets){
                t.setStatut("annule");
                String login_conducteur = t.getVehiculeTrajet().getUtilisateur().getLogin();
                creerNotification(login_conducteur,"Le trajet de "+ t.getVilleDepart().getNomPropre() +" à "+t.getVilleArrivee().getNomPropre() + " le "+t.getDate()+ " a été annulé");
                for(Reservation r : t.getListeReservation()){
                    String login_reserv = r.getUtilisateurReservation().getLogin();
                    creerNotification(login_reserv,"Le trajet de "+ t.getVilleDepart().getNomPropre() +" à "+t.getVilleArrivee().getNomPropre() + " le "+t.getDate()+ " a été annulé");
                }
            }
            return true;
        }catch (Exception e){
            throw new PasVehiculeUtilisateur("pas votre vehicule");
        }
    }

    @RolesAllowed("utilisateur")
    @Override
    public List<VehiculeDTO> listeVehicules(String login){
        Utilisateur utilisateur = em.find(Utilisateur.class, login);
        List<VehiculeDTO> listeVehicules = new ArrayList<>();
        for(Vehicule v : utilisateur.getListeVehicule()){
            if(v.getStatut().equals("actif")) {
                VehiculeDTO vDTO = new VehiculeDTO(v.getIdVehicule(), v.getModele(), v.getNom(), v.getGabarit().getType(), v.getNombrePlaces());
                listeVehicules.add(vDTO);
            }
        }
        return listeVehicules;
    }

    @RolesAllowed("utilisateur")
    @Override
    public boolean annulerTrajet(String login, int idTrajet) throws PasConducteurException{
        Utilisateur utilisateur = em.find(Utilisateur.class,login);
        Trajet trajet = em.find(Trajet.class,idTrajet);

        // On vérifie que c'est bien l'utilisateur qui a créé le trajet
        verifierUtilisateurEstConducteur(utilisateur,trajet);

        trajet.setStatut("annule");
        Notification notification;
        String messageNotification = ("Le trajet "+trajet.getVilleDepart().getNomPropre()+" - "+trajet.getVilleArrivee().getNomPropre() + " du "+trajet.getDate()+" a été annulé");

        //Trouver tous les passagers et leur envoyer la notification
        Utilisateur passager;
        for(Reservation reservation : trajet.getListeReservation()){
            if(reservation.getStatut().equals("enAttente") || reservation.getStatut().equals("accepte")){
                passager = reservation.getUtilisateurReservation();
                notification = new Notification();
                notification.setMessage(messageNotification);
                if(passager.ajouterNotification(notification)) {
                    em.persist(notification);
                    em.persist(passager);
                }
            }
        }
        return true;
    }

    @RolesAllowed("utilisateur")
    @Override
    public List<ReservationDTO> avoirReservationsAcceptees(String login, int idTrajet) throws PasConducteurException {
        return avoirReservation(login,idTrajet,"accepte");
    }

    @RolesAllowed("utilisateur")
    @Override
    public List<ReservationDTO> avoirReservationsEnAttente(String login, int idTrajet) throws PasConducteurException {
        return avoirReservation(login,idTrajet,"enAttente");
    }

    /**
     * Avoir la liste des réservations concernant un trajet
     * @param login                     L'identifiant de l'utilisateur
     * @param idTrajet                  L'identifiant du trajet
     * @param message                   Le statut de la réservation
     * @return                          La liste des réservations pour le trajet
     * @throws PasConducteurException   Si l'utilisateur n'est pas le conducteur
     */
    @RolesAllowed("utilisateur")
    private List<ReservationDTO> avoirReservation(String login, int idTrajet, String message) throws PasConducteurException{
        Utilisateur utilisateur = em.find(Utilisateur.class,login);
        Trajet trajet = em.find(Trajet.class,idTrajet);
        verifierUtilisateurEstConducteur(utilisateur,trajet);

        List<ReservationDTO> reservationListe = new ArrayList<>();
        for(Reservation reservation : trajet.getListeReservation()){
            if(reservation.getStatut().equals(message)){
                reservationListe.add(new ReservationDTO(reservation));
            }
        }
        return reservationListe;
    }

    @RolesAllowed("utilisateur")
    @Override
    public boolean refuserReservation(String login, int idReservation) throws PasConducteurException {
        Reservation reservation = em.find(Reservation.class,idReservation);
        String messageNotification;
        if(null == reservation.getDescendA()){
            messageNotification = "Votre réservation pour le trajet "+reservation.getTrajetReservation().getVilleDepart().getNomPropre()+" - "
                    +reservation.getTrajetReservation().getVilleArrivee().getNomPropre()+" du "+reservation.getTrajetReservation().getDate()+" a été refusée";
        }else{
            messageNotification = "Votre réservation pour le trajet "+reservation.getTrajetReservation().getVilleDepart().getNomPropre()+" - "
                    +reservation.getDescendA().getVilleEtape().getNomPropre()+" du "+reservation.getTrajetReservation().getDate()+" a été refusée";
        }
        String statut="refuse";
        gererReservation(login,reservation,messageNotification,statut);
        return true;
    }

    @RolesAllowed("utilisateur")
    @Override
    public boolean accepterReservation(String login, int idReservation) throws PasConducteurException{
        Reservation reservation = em.find(Reservation.class,idReservation);
        String messageNotification;
        if(null == reservation.getDescendA()){
            messageNotification = "Votre réservation pour le trajet "+reservation.getTrajetReservation().getVilleDepart().getNomPropre()+" - "
                    +reservation.getTrajetReservation().getVilleArrivee().getNomPropre()+" du "+reservation.getTrajetReservation().getDate()+" a été acceptée";
        }else{
            messageNotification = "Votre réservation pour le trajet "+reservation.getTrajetReservation().getVilleDepart().getNomPropre()+" - "
                    +reservation.getDescendA().getVilleEtape().getNomPropre()+" du "+reservation.getTrajetReservation().getDate()+" a été acceptée";
        }
        String statut="accepte";
        gererReservation(login,reservation,messageNotification,statut);
        return true;
    }

    @RolesAllowed("utilisateur")
    @Override
    public boolean annulerReservation(String login, int idReservation) {
        Query query = em.createQuery("SELECT r FROM Reservation r WHERE r.idReservation =:idReservation and r.utilisateurReservation.login =:login");
        query.setParameter("idReservation",idReservation);
        query.setParameter("login",login);
        try {
            Reservation res = (Reservation) query.getSingleResult();
            res.setStatut("annule");
            em.persist(res);
        }catch(Exception e){
            creerNotification(login,"Vous avez essayé de supprimer une réservation qui ne vous appartient pas");
        }
        return true;
    }

    @RolesAllowed("utilisateur")
    @Override
    public boolean supprimerNotification(String login, int idNotification) {
        Utilisateur utilisateur = em.find(Utilisateur.class,login);
        Notification notification = em.find(Notification.class,idNotification);
        em.remove(notification);
        return utilisateur.supprimerNotification(notification);
    }

    @RolesAllowed("utilisateur")
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
        return listeHisto;
    }

    @RolesAllowed("utilisateur")
    @Override
    public HistoriqueDTO uniqueHistoriqueUtilisateur(String login, int id){
        List<HistoriqueDTO> liste = historiqueUtilisateur(login);
        for(HistoriqueDTO h : liste){
            if(h.getIdTrajet() == id){
                return h;
            }
        }
        return null;
    }

    @RolesAllowed("utilisateur")
    @Override
    public List<Gabarit> listeGabarits(){
        Query q = em.createQuery("SELECT g FROM Gabarit g");
        return q.getResultList();
    }

    @RolesAllowed("utilisateur")
    @Override
    public boolean changerMotDePasse(String login, String motDePasse) {
        Utilisateur utilisateur = em.find(Utilisateur.class,login);
        String motDePasseHash = automate.recupererHash(motDePasse);
        if(utilisateur.getMotDePasse().equals(motDePasseHash)){
            return false;
        } else {
            utilisateur.setMotDePasse(motDePasseHash);
            return true;
        }
    }

    @RolesAllowed("utilisateur")
    @Override
    public boolean verifierMotDePasse(String login, String motDePasse) {
        Utilisateur utilisateur = em.find(Utilisateur.class,login);
        return utilisateur.getMotDePasse().equals(automate.recupererHash(motDePasse));
    }

    @RolesAllowed("utilisateur")
    @Override
    public boolean supprimerUtilisateur(String login) throws PasConducteurException,PasVehiculeUtilisateur {
        Utilisateur utilisateur = em.find(Utilisateur.class,login);
        if(null != utilisateur) {

            //Pour passer tous les véhicules en inactifs
            for(Vehicule vehicule : utilisateur.getListeVehicule()){
                supprimerVehicule(login,vehicule.getIdVehicule());
            }
            System.out.println("liste vehicule");
            //Pour supprimer les vehicules inactifs
            Query query = em.createQuery("SELECT v FROM Vehicule v WHERE v.utilisateur=:utilisateur");
            query.setParameter("utilisateur",utilisateur);
            List<Vehicule> vehicules = query.getResultList();
            for(Vehicule vehicule : vehicules) {
                for(Trajet t : vehicule.getListeTrajet()){
                    System.out.println(t);
                    supprimerTrajet(t);
                    System.out.println("4");
                    vehicule.supprimerTrajet(t);
                    em.persist(vehicule);
                    System.out.println("7");
                    em.remove(t);
                    System.out.println("8");
                }
                em.remove(vehicule);
                System.out.println("9");
            }
            System.out.println("supprimer vehicule");

            for(Appreciation appreciation : utilisateur.getNote()){
                em.remove(appreciation);
            }
            System.out.println("supprimer appreciation envoyée");
            for(Appreciation appreciation : utilisateur.getEstNote()){
                em.remove(appreciation);
            }
            System.out.println("supprimer appreciation reçue");
            for(Reservation reservation : utilisateur.getListeReservation()){
                System.out.println("reservation "+ reservation) ;
                Trajet t = reservation.getTrajetReservation();
                System.out.println("trajet "+ t);
                annulerReservation(login,reservation.getIdReservation());
                System.out.println("annuler Reservation");
                if(t.supprimerReservation(reservation)) {
                    System.out.println("pre");
                    em.remove(reservation);
                    System.out.println("post");
                }
            }
            System.out.println("supprimer reservation");
            for(Notification notification : utilisateur.getNotifications()){
                em.remove(notification);
            }
            System.out.println("supprimer notif");
            em.remove(utilisateur);
            System.out.println("supprimer utilisateur");
            return true;
        } else {
            return false;
        }
    }

    /**
     * Supprime de la base un trajet
     * @param t Le trajet
     */
    private void supprimerTrajet(Trajet t) {
        System.out.println("1");
        for(Reservation reservation : t.getListeReservation()){
            creerNotification(reservation.getUtilisateurReservation().getLogin()," Le trajet "+t.getVilleDepart().getNomPropre()+"-"+
            t.getVilleArrivee().getNomPropre()+" le "+t.getDate()+":"+t.getHeure()+" a été supprimé par son conducteur");
            t.supprimerReservation(reservation);
            em.remove(reservation);
        }
        System.out.println("2");
        Query q = em.createQuery("SELECT a FROM Appreciation a WHERE a.noteTrajet=:t");
        q.setParameter("t",t);
        List<Appreciation>appreciations = q.getResultList();
        for(Appreciation appreciation : appreciations){
            appreciation.getEstNote().supprimerAppreciationRecue(appreciation);
            appreciation.getDonneNote().supprimerAppreciationEnvoyee(appreciation);
            em.remove(appreciation);
        }
        System.out.println("3");
        for(Etape etape: t.getListeEtape()){
            if(t.supprimerEtape(etape)){
                em.remove(etape);
            }
        }
    }

    /**
     * Gère une réservation et envoie des notifications aux utilisateurs concernés
     * @param login                     L'identifiant de l'utilisateur
     * @param reservation               La réservation à gérer
     * @param messageNotification       Le message à afficher dans la notification
     * @param statut                    Le statut à mettre dans la réservation
     * @throws PasConducteurException   Si l'utilisateur n'est pas le conducteur du trajet
     */
    @RolesAllowed("utilisateur")
    private void gererReservation(String login, Reservation reservation, String messageNotification, String statut) throws PasConducteurException {
        Utilisateur utilisateur = em.find(Utilisateur.class, login);
        verifierUtilisateurEstConducteur(utilisateur, reservation.getTrajetReservation());
        reservation.setStatut(statut);
        Utilisateur passager = reservation.getUtilisateurReservation();
        Notification notification = new Notification();
        notification.setMessage(messageNotification);
        if(passager.ajouterNotification(notification)) {
            em.persist(notification);
            em.persist(passager);
        }
    }

    /**
     * Vérifie si l'utilisateur est le conducteur du trajet
     * @param utilisateur               L'utilisateur à tester
     * @param trajet                    Le trajet à tester
     * @throws PasConducteurException   Si l'utilisateur n'est pas le conducteur du trajet
     */
    @RolesAllowed("utilisateur")
    private void verifierUtilisateurEstConducteur(Utilisateur utilisateur, Trajet trajet) throws PasConducteurException{
        if(!trajet.getVehiculeTrajet().getUtilisateur().getLogin().equals(utilisateur.getLogin())) {
            throw new PasConducteurException("Vous n'êtes pas le conducteur de ce trajet");
        }
    }

    @RolesAllowed("utilisateur")
    @Override
    public List<VilleDTO> getListeVilleDTO(){
        return recherche.getListeVillesDTO();
    }

    @RolesAllowed("utilisateur")
    @Override
    public List<TrajetDTO> rechercheTrajet(String villeDepart, String departementDepart, String villeArrive, String departementArrive, String date, String prix) throws DateAnterieureException, ParseException {
        return recherche.rechercheTrajet(villeDepart,departementDepart,villeArrive,departementArrive,date,prix);
    }

    @RolesAllowed("utilisateur")
    @Override
    public void ajouterTrajet(String login, String villeDepart, String villeArrivee, String nomVehicule, String[] etapes, String date, String heure, String minute, String prix) throws PrixInferieurException, EtapeException, VehiculeException {
        Utilisateur user = em.find(Utilisateur.class, login);
        List<Vehicule> vListe = user.getListeVehicule();

        // On récupère l'id du véhicule utilisé pour le trajet
        int idVehicule = 0;
        for(Vehicule v : vListe){
            if(v.getNom().equals(nomVehicule) && v.getStatut().equals("actif")){
                idVehicule = v.getIdVehicule();
                break;
            }
        }
        Vehicule vehicule = em.find(Vehicule.class, idVehicule);
        if(null == vehicule){
            throw new VehiculeException("vehicule non existant");
        }

        Trajet trajet = new Trajet(date, heure+":"+minute);
        // On crée une map associant les villes-étapes à leur prix.
        StringTokenizer st;
        st = new StringTokenizer(villeDepart, "()");
        String idVilleDepart = st.nextToken() + "_" + st.nextToken();
        st = new StringTokenizer(villeArrivee, "()");
        String idVilleArrivee = st.nextToken() + "_" + st.nextToken();
        Ville ville1 = em.find(Ville.class, idVilleDepart);
        Ville ville2 = em.find(Ville.class, idVilleArrivee);

        Map<String, Integer> mapPrix = new TreeMap<>();
        int prixTrajet = Integer.parseInt(prix);
        try {
            if (null != etapes) {
                for (String etape : etapes) {
                    st = new StringTokenizer(etape, "()/€");
                    String nomVille = st.nextToken() + "_" + st.nextToken();
                    int prixEtape = Integer.parseInt(st.nextToken());
                    if (prixEtape > prixTrajet) {
                        throw new PrixInferieurException("Une étape a un prix supérieur à celui du trajet");
                    }
                    if(idVilleDepart.equals(nomVille) || idVilleArrivee.equals(nomVille)){
                        throw new EtapeException("etape egale la ville d'arrivée ou de départ");
                    }
                    mapPrix.put(nomVille, prixEtape);
                }
            }
        }catch (Exception e){
            throw new EtapeException("erreur de saisie de la ville ou du prix");
        }

        // Si on a des étapes, on les persiste
        if(!mapPrix.isEmpty()){
            List<Etape> listeEtapes = new ArrayList<>();
            mapPrix.forEach((etape, prixEtape) -> {
                Etape etapeTemp = new Etape();
                etapeTemp.setTrajet(trajet);
                etapeTemp.setPrix(prixEtape);
                Ville villeTemp = em.find(Ville.class, etape);
                etapeTemp.setVilleEtape(villeTemp);
                em.persist(etapeTemp);
                listeEtapes.add(etapeTemp);
            });
            trajet.setListeEtape(listeEtapes);
        }

        // On crée le trajet à partir des données

        trajet.setVehiculeTrajet(vehicule);
        trajet.setVilleDepart(ville1);
        trajet.setVilleArrivee(ville2);
        trajet.setPrix(Integer.parseInt(prix));
        trajet.setStatut("aVenir");

        em.persist(trajet);

        creerNotification(login,"Le trajet "+villeDepart+" - "+villeArrivee + " pour le "+ trajet.getDate() +" a été créé");
    }

    @RolesAllowed("utilisateur")
    @Override
    public TrajetDTO avoirTrajet(String login, int idTrajet, String type) throws AccesInterditException {
        Query query;
        switch (type) {
            case "conducteur":
                query=em.createQuery("SELECT t FROM Trajet t, Reservation r WHERE t.vehiculeTrajet.utilisateur.login=:login and " +
                        "t.idTrajet=:idTrajet");
                query.setParameter("login",login);
                query.setParameter("idTrajet",idTrajet);
                break;
            case "passager":
                query = em.createQuery("SELECT t FROM Trajet t, Reservation r WHERE " +
                        "r.trajetReservation=t and r.utilisateurReservation.login=:login and " +
                        "t.idTrajet=:idTrajet");
                query.setParameter("login",login);
                query.setParameter("idTrajet",idTrajet);
                break;
            case "tous":
                query = em.createQuery("SELECT t FROM Trajet t, Reservation r WHERE ((t.vehiculeTrajet.utilisateur.login=:login) or" +
                        "(r.trajetReservation=t and r.utilisateurReservation.login=:login)) and " +
                        "t.idTrajet=:idTrajet");
                query.setParameter("login",login);
                query.setParameter("idTrajet",idTrajet);
                break;
            case "recherche":
                query = em.createQuery("SELECT t from Trajet t WHERE t.idTrajet=:idTrajet");
                query.setParameter("idTrajet",idTrajet);
                break;
            default:
                throw new AccesInterditException("pas les droits");
        }
        try{
            List<Trajet> trajets =  query.getResultList();
            return new TrajetDTO(trajets.get(0));
        }catch (Exception e){
            throw new AccesInterditException("vous n'avez pas les droits");
        }
    }

    @RolesAllowed("utilisateur")
    @Override
    public Notification creerNotification(String login, String message) {
        return automate.creerNotification(login,message);
    }

    @RolesAllowed("utilisateur")
    @Override
    public List<Notification> avoirListeNotification(String login){
        Query q = em.createQuery("SELECT u.notifications FROM Utilisateur u WHERE u.login=:login");
        q.setParameter("login", login);
        return q.getResultList();
    }

    @RolesAllowed("utilisateur")
    @Override
    public int avoirNbPlacesRestantes(int idTrajet) {
        Trajet trajet = em.find(Trajet.class,idTrajet);
        int nbPlacesRestantes = trajet.getVehiculeTrajet().getNombrePlaces();
        for ( Reservation reservation : trajet.getListeReservation()){
            if(reservation.getStatut().equals("accepte")){
                nbPlacesRestantes -= reservation.getNbPlace();
            }
        }
        return  nbPlacesRestantes;
    }

    @RolesAllowed("utilisateur")
    @Override
    public Map<String, Object> avoirListeTrajetAVenir(String login){
        // On recherche tous les trajets faits en tant que conducteur
        Query q = em.createQuery("SELECT DISTINCT t FROM Trajet t WHERE t.statut=:statutTrajet " +
                "AND t.vehiculeTrajet.utilisateur.login=:loginUtilisateur");
        q.setParameter("statutTrajet", "aVenir");
        q.setParameter("loginUtilisateur", login);
        Map<String,Object> map = new TreeMap<>();
        List<Trajet> listeTrajets = q.getResultList();
        List<TrajetDTO> listeTrajetsDTO = new ArrayList<>();

        Map<Integer,Integer> reservationEnAttente = new HashMap<>();

        int enAttente = 0;
        for(Trajet t : listeTrajets){
            listeTrajetsDTO.add(new TrajetDTO(t));
            for(Reservation reservation : t.getListeReservation()){
                if(reservation.getStatut().equals("enAttente")){
                    enAttente++;
                }
            }
            reservationEnAttente.put(t.getIdTrajet(),enAttente);
            enAttente = 0;
        }
        Collections.sort(listeTrajetsDTO);
        map.put("conducteur",listeTrajetsDTO);
        map.put("reservationEnAttente",reservationEnAttente);


        // On recherche tous les trajets faits en tant que passager
        q = em.createQuery("SELECT DISTINCT r FROM Reservation r WHERE (r.statut='accepte' or r.statut='enAttente')" +
                "AND r.trajetReservation.statut=:statutTrajet and r.utilisateurReservation.login=:login");
        q.setParameter("statutTrajet", "aVenir");
        q.setParameter("login", login);
        List<Reservation> reservations = q.getResultList();
        List<ReservationDTO> listeReservationDTO = new ArrayList<>();
        for(Reservation reservation : reservations){
            listeReservationDTO.add(new ReservationDTO(reservation));
        }
        Collections.sort(listeReservationDTO);
        map.put("passager",listeReservationDTO);

        return  map;
    }

    @RolesAllowed("utilisateur")
    @Override
    public ReservationDTO avoirReservationDTO(String login,int idReservation) throws AccesInterditException {
        Query query = em.createQuery("SELECT r FROM Reservation r WHERE r.idReservation=:idReservation and " +
                "r.utilisateurReservation.login=:login");
        query.setParameter("login",login);
        query.setParameter("idReservation",idReservation);
        try {
            Reservation res = (Reservation) query.getSingleResult();
            return new ReservationDTO(res);
        }catch (Exception e){
            throw new AccesInterditException("pas les droits");
        }
    }

    @RolesAllowed("utilisateur")
    @Override
    public List<TrajetDTO> avoirListeTrajet(String login) {
        Utilisateur utilisateur = em.find(Utilisateur.class,login);

        // On récupère tous les trajets finis ou à venir, où l'on était passager ou conducteur
        Query q = em.createQuery("SELECT DISTINCT t FROM Trajet t, Reservation r, Appreciation a WHERE" +
                "((t.statut='fini' or t.statut='aVenir') and ((t.vehiculeTrajet.utilisateur=:utilisateur)" +
                " or (r.trajetReservation=t and r.statut='accepte' and " +
                "r.utilisateurReservation=:utilisateur)))");
        q.setParameter("utilisateur",utilisateur);
        List<TrajetDTO> trajetDTOList = new ArrayList<>();
        List<Trajet> trajets =  q.getResultList();

        for(Trajet t : trajets) {
            String date_trajet = t.getDate()+" "+t.getHeure();
            // Si le statut est aVenir alors qu'il est passé, on le change en fini
            if((t.getStatut().equals("aVenir") && compareDate(date_trajet)<=0)){
                t.setStatut("fini");
                em.persist(t);
            }
            // Si le trajet est fini et qu'il y a moins d'appreciations que de réservations, on l'ajouter à la liste car
            // il reste des appreciations à faire dessus
            if(t.getStatut().equals("fini") ){
                q = em.createQuery("SELECT count(a) FROM Appreciation a WHERE a.noteTrajet=:trajet AND a.donneNote=:utilisateur");
                q.setParameter("trajet", t);
                q.setParameter("utilisateur", utilisateur);
                long nbApp = (long) q.getResultList().get(0);
                q = em.createQuery("SELECT count(r.idReservation) FROM Reservation r WHERE r.trajetReservation=:trajet");
                q.setParameter("trajet", t);
                long nbReserv = (long) q.getResultList().get(0);
                if (nbReserv > nbApp) {
                    trajetDTOList.add(new TrajetDTO(t));
                }
            }

        }
        Collections.sort(trajetDTOList);
        return trajetDTOList;
    }

    @RolesAllowed("utilisateur")
    @Override
    public List<UtilisateurDTO> avoirPersonnesTrajet(String login, int idTrajet) {
        Utilisateur utilisateur = em.find(Utilisateur.class,login);

        // On récupère tous les utilisateurs du trajet
        Query queryUtilisateurs = em.createQuery("SELECT DISTINCT u FROM Trajet t, Reservation r, Utilisateur u WHERE " +
                "t.idTrajet=:idTrajet and ((r.trajetReservation=t and r.utilisateurReservation=u and r.statut='accepte') " +
                "or (t.vehiculeTrajet.utilisateur = u))");
        queryUtilisateurs.setParameter("idTrajet",idTrajet);
        List<Utilisateur> utilisateurs = queryUtilisateurs.getResultList();

        // On récupère toutes les appréciations du trajet
        Query queryAppreciations = em.createQuery("SELECT DISTINCT a FROM Appreciation a WHERE " +
                "a.noteTrajet.idTrajet= :idTrajet");
        queryAppreciations.setParameter("idTrajet",idTrajet);
        List<Appreciation> appreciations = queryAppreciations.getResultList();
        // Si on a déjà donné une appreciation à un utilisateur pour ce trajet,
        // on enlève cet utilisateur de la liste des utilisateurs à noter
        for(Utilisateur utilisateur1 : utilisateurs){
            System.out.println(utilisateur1.toString());
        }
        for (Appreciation appreciation : appreciations){
            System.out.println(appreciation.toString());
            if(appreciation.getDonneNote().equals(utilisateur)){
                utilisateurs.remove(appreciation.getEstNote());
            }
        }

        // On transforme la liste des utilisateurs en utilisateurDTO
        List<UtilisateurDTO> utilisateurDTOS = new ArrayList<>();
        for(Utilisateur u : utilisateurs){
            utilisateurDTOS.add(new UtilisateurDTO(u));
        }
        return utilisateurDTOS;
    }

    @Override
    public void supprimerToutesReservationsTrajet(String login, int idTrajet) throws PasConducteurException{
        Utilisateur utilisateur = em.find(Utilisateur.class,login);
        Trajet trajet = em.find(Trajet.class,idTrajet);
        verifierUtilisateurEstConducteur(utilisateur,trajet);
        for(Reservation reservation : trajet.getListeReservation()){
            refuserReservation(login,reservation.getIdReservation());
        }
    }


    /**
     * Compare une date avec aujourd'hui
     * @param string_date   La date à tester
     * @return              Le résultat de la comparaison des dates
     */
    @RolesAllowed("utilisateur")
    private int compareDate(String string_date) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        int result=0;
        try {
            Date current_date= new Date();
            format.format(current_date);
            Date date = format.parse((string_date));
            if (date.compareTo(current_date) > 0) {
                result=1;
            } else if (date.compareTo(current_date) < 0) {
                result=-1;
            } else if (date.compareTo(current_date) == 0) {
                result=0;
            }
        }catch(ParseException e){
            Logger.getAnonymousLogger().log(Level.INFO,"La date n'a pas pu être parsée");
        }
        return result;
    }

    @RolesAllowed("utilisateur")
    @Override
    public float avoirPrixMoyen(String villeDepart, String villeArrivee) throws VilleNonTrouvee{
        try {
            StringTokenizer st = new StringTokenizer(villeDepart, "()");
            String villeDepart2 = st.nextToken() + "_" + st.nextToken();
            st = new StringTokenizer(villeArrivee, "()");
            String villeArrivee2 = st.nextToken() + "_" + st.nextToken();
            return automate.prixMoyen(villeDepart2, villeArrivee2);
        }catch (Exception e){
            throw new VilleNonTrouvee("ville non trouvee");
        }
    }

    @RolesAllowed("utilisateur")
    @Override
    public boolean datePosterieure(String dateTest)  throws ParseException {
        return automate.datePosterieure(dateTest);
    }
}
