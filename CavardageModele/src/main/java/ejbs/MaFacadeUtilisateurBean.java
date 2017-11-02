package ejbs;

import dtos.*;
import entities.*;
import exceptions.*;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.text.SimpleDateFormat;
import java.util.*;

@Stateless(name = "UtilisateurBean")
public class MaFacadeUtilisateurBean implements MaFacadeUtilisateur {

    @PersistenceContext(unitName = "monUnite")
    EntityManager em;
    @EJB
    RechercheBean recherche;

    public MaFacadeUtilisateurBean() {
    }

    @Override
    public Reservation reserverPlace(String login, int idTrajet, int nbPlaces, String idVilleArrivee) throws VilleNonTrouvee {
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
                System.out.println(etape.getVilleEtape().getNomVille()+" "+idVilleArrivee);
                if (etape.getVilleEtape().getNomVille().equals(idVilleArrivee)) {
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
        Utilisateur conducteur = vehicule.getUtilisateur();
        Notification notification = new Notification();
        String messageNotification = "Une nouvelle réservation est arrivée pour le trajet "+trajet.getVilleDepart().getNomVille()+" - "+trajet.getVilleArrivee().getNomVille();
        notification.setMessage(messageNotification);
        conducteur.ajouterNotification(notification);
        em.persist(notification);
        em.persist(conducteur);
        return reservation;
    }

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
        return appreciation;
       // AppreciationDTO appreciationDTO = new AppreciationDTO(appreciation);
        //return appreciationDTO;
    }

    @Override
    public List<Appreciation> avoirNotesTrajet(int idTrajet) {
        Query q = em.createQuery("FROM Appreciation a WHERE a.noteTrajet:=trajet");
        q.setParameter("trajet",idTrajet);
        List<Appreciation> appreciationListe = q.getResultList();
        return appreciationListe;
    }

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
            throw new DivisionParZeroException();
        } else {
            moyenne = notes / appreciations.size();
            int moyenne_int = moyenne*10;
            return moyenne_int/10;
        }
    }

    @Override
    public Vehicule ajouterVehicule(String login, String nomVehicule, String modele, String gabarit, int nbPlaces) throws VehiculeDejaExistantException {
        Utilisateur utilisateur = em.find(Utilisateur.class, login);
        for(Vehicule v : utilisateur.getListeVehicule()){
            if(v.getNom().equals(nomVehicule)){
                throw new VehiculeDejaExistantException("Vous avez déjà un véhicule portant ce nom");
            }
        }
        Query q = em.createQuery("From Gabarit g where g.type=:gabarit");
        q.setParameter("gabarit", gabarit);
        Gabarit g = (Gabarit) q.getSingleResult();
        Vehicule vehicule = new Vehicule();
        vehicule.setGabarit(g);
        vehicule.setModele(modele);
        vehicule.setNom(nomVehicule);
        vehicule.setNombrePlaces(nbPlaces);
        vehicule.setUtilisateur(utilisateur);
        utilisateur.ajouterVehicule(vehicule);
        em.persist(vehicule);
        em.persist(utilisateur);
        return vehicule;
    }

    @Override
    public boolean supprimerVehicule(String login, int idVehicule){
        Utilisateur utilisateur = em.find(Utilisateur.class, login);
        Vehicule v = em.find(Vehicule.class, idVehicule);
        utilisateur.supprimerVehicule(v);
        em.remove(v);
        em.persist(utilisateur);
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
    public List<ReservationDTO> avoirReservationsAcceptees(String login, int idTrajet) throws PasConducteurException {
        return avoirReservation(login,idTrajet,"accepte");
    }

    @Override
    public List<ReservationDTO> avoirReservationsEnAttente(String login, int idTrajet) throws PasConducteurException {
        return avoirReservation(login,idTrajet,"enAttente");
    }

    private List<ReservationDTO> avoirReservation(String login, int idTrajet, String message) throws PasConducteurException{
        System.out.println(idTrajet);
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

    @Override
    public boolean refuserReservation(String login, int idReservation) throws PasConducteurException {
        Reservation reservation = em.find(Reservation.class,idReservation);
        String messageNotification = "";
        if(null == reservation.getDescendA() || reservation.getDescendA().equals("")){
            messageNotification = "Votre réservation pour le trajet "+reservation.getTrajetReservation().getVilleDepart().getNomVille()+" - "+reservation.getTrajetReservation().getVilleArrivee().getNomVille()+" a été refusée";
        }else{
            messageNotification = "Votre réservation pour le trajet "+reservation.getTrajetReservation().getVilleDepart().getNomVille()+" - "+reservation.getDescendA().getVilleEtape().getNomVille()+" a été refusée";
        }
        String statut="refuse";
        gererReservation(login,reservation,messageNotification,statut);
        return true;
    }

    @Override
    public boolean accepterReservation(String login, int idReservation) throws PasConducteurException{
        Reservation reservation = em.find(Reservation.class,idReservation);
        String messageNotification = "";
        if(null == reservation.getDescendA() || reservation.getDescendA().equals("")){
            messageNotification = "Votre réservation pour le trajet "+reservation.getTrajetReservation().getVilleDepart().getNomVille()+" - "+reservation.getTrajetReservation().getVilleArrivee().getNomVille()+" a été acceptée";
        }else{
            messageNotification = "Votre réservation pour le trajet "+reservation.getTrajetReservation().getVilleDepart().getNomVille()+" - "+reservation.getDescendA().getVilleEtape().getNomVille()+" a été acceptée";
        }
        String statut="accepte";
        gererReservation(login,reservation,messageNotification,statut);
        return true;
    }

    @Override
    public boolean supprimerNotification(String login, int idNotification) {
        Utilisateur utilisateur = em.find(Utilisateur.class,login);
        Notification notification = em.find(Notification.class,idNotification);
        em.remove(notification);
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

        //HistoriqueDTO hist1 = new HistoriqueDTO(1, "clio", 0, "conducteur", "Orleans", "Tours", "26/10/2017");
        //HistoriqueDTO hist2 = new HistoriqueDTO(2, "", 2, "passager", "Paris", "Marseille", "15/10/2017");

        //listeHisto.add(hist1);
        //listeHisto.add(hist2);

        return listeHisto;
    }

    @Override
    public HistoriqueDTO uniqueHistoriqueUtilisateur(String login, int id){
        System.out.println("trajet modele : " + id);
        List<HistoriqueDTO> liste = historiqueUtilisateur(login);
        System.out.println("size : " + liste.size());
        for(HistoriqueDTO h : liste){
            if(h.getIdTrajet() == id){
                System.out.println("id : " + h.getIdTrajet());
                return h;
            }
        }
        return null;
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
        System.out.println("la 3.5 login : " + login);
        Utilisateur utilisateur = em.find(Utilisateur.class, login);
        System.out.println("la 4");
        verifierUtilisateurEstConducteur(utilisateur, reservation.getTrajetReservation());
        System.out.println("la 5");
        reservation.setStatut(statut);
        Utilisateur passager = reservation.getUtilisateurReservation();
        Notification notification = new Notification();
        notification.setMessage(messageNotification);
        passager.ajouterNotification(notification);
        em.persist(notification);
        em.persist(passager);
        System.out.println("la 6");
    }

    private boolean verifierUtilisateurEstConducteur(Utilisateur utilisateur, Trajet trajet) throws PasConducteurException{
        if(!trajet.getVehiculeTrajet().getUtilisateur().getLogin().equals(utilisateur.getLogin())) {
            throw new PasConducteurException();
        } else {
            return true;
        }
    }

    public List<Ville> getListeVilles(){
        return recherche.getListeVilles();
    }

    public List<VilleDTO> getListeVilleDTO(){
        return recherche.getListeVillesDTO();
    }

    @Override
    public List<TrajetDTO> rechercheTrajet(String villeDepart, String departementDepart, String villeArrive, String departementArrive, String date, String prix) {
        return recherche.rechercheTrajet(villeDepart,departementDepart,villeArrive,departementArrive,date,prix);
    }

    @Override
    public void ajouterTrajet(String login, String villeDepart, String villeArrivee, String nomVehicule, String[] etapes, String date, String heure, String minute, String prix) throws PrixInferieurException{
        Utilisateur user = em.find(Utilisateur.class, login);
        List<Vehicule> vListe = user.getListeVehicule();
        int idVehicule = 0;
        for(Vehicule v : vListe){
            if(v.getNom().equals(nomVehicule)){
                idVehicule = v.getIdVehicule();
                break;
            }
        }

        Trajet trajet = new Trajet(date, heure+":"+minute);

        StringTokenizer st;
        Map<String, Integer> mapPrix = new TreeMap<>();
        int sumpPrix = 0;
        if(null != etapes){
            for(String etape : etapes){
                st = new StringTokenizer(etape, "()/€");
                String nomVille = st.nextToken() + "_" + st.nextToken();
                int prixEtape = Integer.parseInt(st.nextToken());
                sumpPrix += prixEtape;
                mapPrix.put(nomVille, prixEtape);
            }
        }

        if(Integer.parseInt(prix) < sumpPrix){
            throw new PrixInferieurException("Le prix des étapes est inférieur au prix du trajet");
        }

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

        st = new StringTokenizer(villeDepart, "()");
        String idVilleDepart = st.nextToken() + "_" + st.nextToken();
        st = new StringTokenizer(villeArrivee, "()");
        String idVilleArrivee = st.nextToken() + "_" + st.nextToken();

        Ville ville1 = em.find(Ville.class, idVilleDepart);
        Ville ville2 = em.find(Ville.class, idVilleArrivee);
        Vehicule vehicule = em.find(Vehicule.class, idVehicule);

        trajet.setVehiculeTrajet(vehicule);
        trajet.setVilleDepart(ville1);
        trajet.setVilleArrivee(ville2);
        trajet.setPrix(Integer.parseInt(prix));
        trajet.setStatut("aVenir");

        em.persist(trajet);
    }

    @Override
    public TrajetDTO avoirTrajet(int idTrajet) {
        Trajet trajet = em.find(Trajet.class,idTrajet);
        return new TrajetDTO(trajet);
    }

    @Override
    public Notification creerNotification(String login, String message) {
        Utilisateur utilisateur = em.find(Utilisateur.class,login);
        Notification notification = new Notification();
        notification.setMessage(message);
        em.persist(notification);
        utilisateur.ajouterNotification(notification);
        em.persist(utilisateur);
        return notification;
    }

    @Override
    public List<Notification> avoirListeNotification(String login){
        Query q = em.createQuery("SELECT u.notifications FROM Utilisateur u WHERE u.login=:login");
        q.setParameter("login", login);
        List<Notification> listeNotif = q.getResultList();
        return listeNotif;
    }

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

    @Override
    public List<TrajetDTO> avoirListeTrajet(String login) {
        Utilisateur utilisateur = em.find(Utilisateur.class,login);
        Query q = em.createQuery("SELECT DISTINCT t FROM Trajet t, Reservation r, Appreciation a WHERE" +
                "((t.statut='fini' or t.statut='aVenir') and ((t.vehiculeTrajet.utilisateur.login=:login)" +
                " or (r.trajetReservation=t and r.statut='accepte' and " +
                "r.utilisateurReservation.login=:login)))");
        q.setParameter("login",login);
        List<TrajetDTO> trajetDTOList = new ArrayList<>();
        List<Trajet> trajets =  q.getResultList();
        for(Trajet t : trajets) {
            String date_trajet = t.getDate()+" "+t.getHeure();
            if((t.getStatut().equals("aVenir") && compareDate(date_trajet)<=0)){
                t.setStatut("fini");
                em.persist(t);
            }
            if(t.getStatut().equals("fini") ){
               // System.out.println(t);
                q = em.createQuery("SELECT count(a) FROM Appreciation a WHERE a.noteTrajet=:trajet");
                q.setParameter("trajet", t);
                long nbApp = (long) q.getResultList().get(0);
                //System.out.println("nbApp " + nbApp);
                q = em.createQuery("SELECT count(r.idReservation) FROM Reservation r WHERE r.trajetReservation=:trajet");
                q.setParameter("trajet", t);
                long nbReserv = (long) q.getResultList().get(0);
                //System.out.println("nbReserv " + nbReserv);
                if (nbReserv > nbApp) {
                    trajetDTOList.add(new TrajetDTO(t));
                }
            }

        }
        return trajetDTOList;
    }

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
        for (Appreciation appreciation : appreciations){
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

    /*
    -1 date1 < date2
    0 date1 = date2
    1 date1 > date2
     */
    public int compareDate(String string_date) {
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
        }catch(Exception e){
            e.printStackTrace();
            System.out.println(e.toString());
        }
        return result;
    }
}
