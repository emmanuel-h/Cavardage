package servlets;

import dtos.*;
import ejbs.MaFacadeUtilisateur;
import entities.Gabarit;
import entities.Notification;
import exceptions.*;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * La servlet qui gère toutes les actions d'un utilisateur authentifié
 */
@DeclareRoles({"admin","utilisateur"})
@WebServlet("ControleurUtilisateur")
public class ControleurUtilisateur extends HttpServlet {

    /**
     * L'ejb pour parler avec le métier
     */
    @EJB
    private MaFacadeUtilisateur maFacade;

    /**
     * Si on reçoit quelque chose e nget, on le traite de la même façon que le post
     * @param request la requête
     * @param response la réponse
     * @throws ServletException servlet exception
     * @throws IOException entrée/sortie exception
     */
    @RolesAllowed("utilisateur")
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * Redirige vers la méthode correspondant à l'action de l'utilisateur
     * @param request la requête
     * @param response la réponse
     * @throws ServletException servlet exception
     * @throws IOException entrée/sortie exception
     */
    @RolesAllowed("utilisateur")
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String aFaire = request.getParameter("afaire");
        afficherNotification(request);
        if (null == aFaire) {
            if (null != request.getUserPrincipal().getName()) {
                afficherNotification(request);
                request.setAttribute("aAfficher", "accueil");
                request.getRequestDispatcher("/WEB-INF/homePage/homePage.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("/WEB-INF/accueil.jsp");
            }
        } else {
            switch (aFaire) {
                case "accueil":
                    voirAccueil(request, response);
                    break;
                case "supprimerNotifs":
                    supprimerToutesLesNotifications(request,response);
                    break;
                case "trajetsEnCours":
                    voirTrajetsEnCours(request, response);
                    break;
                case "creerTrajet":
                    voirCreerTrajetTemp(request, response);
                    break;
                case "voirVehicules":
                    voirVehicules(request, response);
                    break;
                case "voirHistorique":
                    voirHistorique(request, response);
                    break;
                case "detailHistorique":
                    detailsHistorique(request, response);
                    break;
                case "enregistrerVehicule":
                    enregistrerVehicule(request, response);
                    break;
                case "voirAppreciations":
                    voirAppreciations(request, response);
                    break;
                case "parametres":
                    parametres(request, response);
                    break;
                case "enregistrerTrajetTemp":
                    voirCreerTrajet(request, response);
                    break;
                case "enregistrerTrajet":
                    enregistrerTrajet(request, response);
                    break;
                case "changerMotDePasse":
                    changerMotDepasse(request, response);
                    break;
                case "supprimerCompte":
                    supprimerCompte(request, response);
                    break;
                case "rechercherTrajet":
                    rechercherTrajet(request, response);
                    break;
                case "afficherRechercheTrajet":
                    afficherRechercheTrajet(request, response);
                    break;
                case "deconnexion":
                    deconnexion(request,response);
                    break;
                case "gererTrajet":
                    gererTrajet(request,response);
                    break;
                case "detailsTrajet":
                    detailsTrajet(request, response);
                    break;
                case "reserverTrajet":
                    reserverTrajet(request, response);
                    break;
                case "supprimerTrajet":
                    supprimerTrajet(request, response);
                    break;
                case "detailsReservation":
                    detailsReservation(request, response);
                    break;
                case "accepterReservation":
                    accepterReservation(request, response);
                    break;
                case "refuserReservation":
                    refuserReservation(request, response);
                    break;
                case "supprimerReservation":
                    supprimerReservation(request, response);
                    break;
                case "apprecierTrajet":
                    apprecierTrajet(request,response);
                    break;
                case "noter":
                    noter(request,response);
                    break;
                case "supprimerNotif":
                    supprimerNotification(request, response);
                    break;
                case "supprimerVehicule":
                    supprimerVehicule(request, response);
                    break;
                case "toutSupprimer":
                    toutSupprimer(request, response);
                    break;
                default:
                    afficherNotification(request);
                    request.setAttribute("aAfficher", "accueil");
                    request.getRequestDispatcher("/WEB-INF/homePage/homePage.jsp").forward(request, response);
            }
        }
    }

    /**
     * Supprime toutes les notifications de l'utilisateur
     * @param request la requête
     * @param response la réponse
     */
    @RolesAllowed("utilisateur")
    private void supprimerToutesLesNotifications(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getUserPrincipal().getName();
        try{
            maFacade.supprimerToutesLesNotifications(login);
            voirAccueil(request,response);
        }catch (AccesInterditException e){
            deconnexion(request,response);
        }
    }

    /**
     * Retourne sur la page d'accueil
     * @param request la requête
     * @param response la réponse
     * @throws ServletException servlet exception
     * @throws IOException entrée/sortie exception
     */
    @RolesAllowed("utilisateur")
    private void voirAccueil(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        afficherNotification(request);
        request.setAttribute("aAfficher", "accueil");
        request.getRequestDispatcher("/WEB-INF/homePage/homePage.jsp").forward(request, response);
    }

    /**
     * Valide la création d'un trajet
     * @param request la requête
     * @param response la réponse
     * @throws ServletException servlet exception
     * @throws IOException entrée/sortie exception
     */
    @RolesAllowed("utilisateur")
    private void enregistrerTrajet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getUserPrincipal().getName();
        String villeDepart = request.getParameter("villeDepart");
        String villeArrivee = request.getParameter("villeArrivee");
        String date = request.getParameter("date");
        String heure = request.getParameter("heure");
        String minute = request.getParameter("minute");
        String nomVehicule = request.getParameter("vehicule");
        String prixVoyage = request.getParameter("prixVoyage");
        String[] etapes = request.getParameterValues("etape");
        String message = "";
        String messageErreur = null;
        try {
            if (maFacade.datePosterieure(date + " " + heure + ":" + minute)) {
                try {
                    maFacade.ajouterTrajet(login, villeDepart, villeArrivee, nomVehicule, etapes, date, heure, minute, prixVoyage);
                    message = "Trajet créé";
                } catch (PrixInferieurException | VehiculeException | EtapeException e) {
                    messageErreur = e.getMessage();
                }
                request.setAttribute("message", message);
            } else {
                messageErreur = "Vous ne pouvez pas proposer un trajet qui commence dans moins d'une heure";
            }
        }catch (ParseException e){
            messageErreur = "La date rentrée n'est pas valide";
        }
        request.setAttribute("messageErreur",messageErreur);
        voirCreerTrajet(request, response);
    }

    /**
     * Affiche tous les trajets auxquels on va participer
     * @param request la requête
     * @param response la réponse
     * @throws ServletException servlet exception
     * @throws IOException entrée/sortie exception
     */
    @RolesAllowed("utilisateur")
    private void voirTrajetsEnCours(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getUserPrincipal().getName();
        Map<String,Object> listeTrajet = maFacade.avoirListeTrajetAVenir(login);
        request.setAttribute("listeTrajetsConducteur", listeTrajet.get("conducteur"));
        request.setAttribute("listeTrajetsPassager", listeTrajet.get("passager"));
        request.setAttribute("reservationEnAttente",listeTrajet.get("reservationEnAttente"));
        request.setAttribute("aAfficher", "trajetsEnCours");
        request.getRequestDispatcher("/WEB-INF/homePage/homePage.jsp").forward(request, response);
    }

    /**
     * Demande à l'utilisateur sa ville de départ et d'arrivée pour pouvoir lui calculer le prix moyen de ce trajet
     * @param request la requête
     * @param response la réponse
     * @throws ServletException servlet exception
     * @throws IOException entrée/sortie exception
     */
    @RolesAllowed("utilisateur")
    private void voirCreerTrajetTemp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<VilleDTO> listeVilles = maFacade.getListeVilleDTO();
        request.setAttribute("listeVilles", listeVilles);
        request.setAttribute("aAfficher", "creerTrajetTemp");
        request.getRequestDispatcher("/WEB-INF/homePage/homePage.jsp").forward(request, response);
    }

    /**
     * Affiche la page de création d'un trajet
     * @param request la requête
     * @param response la réponse
     * @throws ServletException servlet exception
     * @throws IOException entrée/sortie exception
     */
    @RolesAllowed("utilisateur")
    private void voirCreerTrajet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getUserPrincipal().getName();
        String villeDepart = request.getParameter("villeDepart");
        String villeArrivee = request.getParameter("villeArrivee");
        List<VehiculeDTO> vehiculeDTOS = maFacade.listeVehicules(login);
        List<VilleDTO> listeVilles = maFacade.getListeVilleDTO();
        try{
            float prixMoyen = maFacade.avoirPrixMoyen(villeDepart, villeArrivee);
            request.setAttribute("villeDepart", villeDepart);
            request.setAttribute("villeArrivee", villeArrivee);
            request.setAttribute("listeVehicules", vehiculeDTOS);
            request.setAttribute("listeVilles", listeVilles);
            request.setAttribute("prixMoyen", prixMoyen);
            request.setAttribute("aAfficher", "creerTrajet");
            request.getRequestDispatcher("/WEB-INF/homePage/homePage.jsp").forward(request, response);
        }catch(VilleNonTrouvee e){
            request.setAttribute("messageErreur","Veuillez choisir des villes appartenant à la liste");
            voirCreerTrajetTemp(request,response);
        }
    }

    /**
     * Affiche la page de gestion des véhicules
     * @param request la requête
     * @param response la réponse
     * @throws ServletException servlet exception
     * @throws IOException entrée/sortie exception
     */
    @RolesAllowed("utilisateur")
    private void voirVehicules(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getUserPrincipal().getName();
        List<VehiculeDTO> vehiculesDTO = maFacade.listeVehicules(login);
        request.setAttribute("listeVehicules", vehiculesDTO);
        List<Gabarit> gabarits = maFacade.listeGabarits();
        request.setAttribute("listeGabarits", gabarits);
        request.setAttribute("aAfficher", "vehicules");
        request.getRequestDispatcher("/WEB-INF/homePage/homePage.jsp").forward(request, response);
    }

    /**
     * Affiche la page affichant l'historique des trajets effectués sur le site pour l'utilisateur actuel
     * @param request la requête
     * @param response la réponse
     * @throws ServletException servlet exception
     * @throws IOException entrée/sortie exception
     */
    @RolesAllowed("utilisateur")
    private void voirHistorique(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getUserPrincipal().getName();
        List<HistoriqueDTO> listeHistorique = maFacade.historiqueUtilisateur(login);
        request.setAttribute("listeHistorique", listeHistorique);
        request.setAttribute("aAfficher", "historique");
        request.getRequestDispatcher("/WEB-INF/homePage/homePage.jsp").forward(request, response);
    }

    /**
     * Affiche les détails d'un trajet effectué
     * @param request la requête
     * @param response la réponse
     * @throws ServletException servlet exception
     * @throws IOException entrée/sortie exception
     */
    @RolesAllowed("utilisateur")
    private void detailsHistorique(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getUserPrincipal().getName();
        int idTrajet = Integer.parseInt(request.getParameter("idTrajet"));
        HistoriqueDTO historique = maFacade.uniqueHistoriqueUtilisateur(login, idTrajet);
        try{
            TrajetDTO trajet = maFacade.avoirTrajet(login,idTrajet,"tous");
            request.setAttribute("histo", historique);
            request.setAttribute("trajet", trajet);
            request.setAttribute("aAfficher", "detailsHistorique");
            request.getRequestDispatcher("/WEB-INF/homePage/homePage.jsp").forward(request, response);
        }catch (AccesInterditException e){
            maFacade.creerNotification(login,"Vous avez essayé de voir un trajet dont vous n'avez pas les droits");
            voirHistorique(request,response);
        }
    }

    /**
     * Affiche la page permettant de faire ou de voir ses appréciations
     * @param request la requête
     * @param response la réponse
     * @throws ServletException servlet exception
     * @throws IOException entrée/sortie exception
     */
    @RolesAllowed("utilisateur")
    private void voirAppreciations(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getUserPrincipal().getName();
        String noteMoyenne;
        try {
            float noteMoyenneFloat = maFacade.moyenneNotes(login);
            noteMoyenne = Float.toString(noteMoyenneFloat);
        } catch (DivisionParZeroException e) {
            noteMoyenne = "Pas encore de notes reçues.";
        }
        List<AppreciationDTO> appreciationDTOList = maFacade.avoirToutesAppreciations(login);
        List<TrajetDTO> trajetDTOS = maFacade.avoirListeTrajet(login);
        request.setAttribute("listeTrajetEffectues",trajetDTOS);
        request.setAttribute("appreciations",appreciationDTOList);
        request.setAttribute("noteMoyenne",noteMoyenne);
        request.setAttribute("aAfficher", "appreciations");
        request.getRequestDispatcher("/WEB-INF/homePage/homePage.jsp").forward(request, response);
    }

    /**
     * Affiche la page de gestion des paramètres utilisateurs
     * @param request la requête
     * @param response la réponse
     * @throws ServletException servlet exception
     * @throws IOException entrée/sortie exception
     */
    @RolesAllowed("utilisateur")
    private void parametres(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("aAfficher", "parametres");
        request.getRequestDispatcher("/WEB-INF/homePage/homePage.jsp").forward(request, response);
    }

    /**
     * Enregistre un nouveau véhicule pour l'utilisateur courant
     * @param request la requête
     * @param response la réponse
     * @throws ServletException servlet exception
     * @throws IOException entrée/sortie exception
     */
    @RolesAllowed("utilisateur")
    private void enregistrerVehicule(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getUserPrincipal().getName();
        String nomVehicule = request.getParameter("nomVehicule");
        String modeleVehicule = request.getParameter("modeleVehicule");
        String gabaritVehicule = request.getParameter("gabaritVehicule");
        int nbPlaces = Integer.parseInt(request.getParameter("nbPlaces"));
        try{
            maFacade.ajouterVehicule(login, nomVehicule, modeleVehicule, gabaritVehicule, nbPlaces);
        }catch(VehiculeDejaExistantException e){
            request.setAttribute("message", e.getMessage());
        }
        catch (GabaritException e){
            request.setAttribute("message","Gabarit non trouvé");
        }
        voirVehicules(request, response);
    }

    /**
     * Valide le changement de mot de passe de l'utilisateur courant
     * @param request la requête
     * @param response la réponse
     * @throws ServletException servlet exception
     * @throws IOException entrée/sortie exception
     */
    @RolesAllowed("utilisateur")
    private void changerMotDepasse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getUserPrincipal().getName();
        String motDePasse1 = request.getParameter("nouveauMdP1");
        String motDePasse2 = request.getParameter("nouveauMdP2");
        String message;
        if (motDePasse1.equals("") || motDePasse2.equals("")) {
            message = "Un champs n'est pas rempli";
        } else if (!motDePasse1.equals(motDePasse2)) {
            message = "Les mots de passe ne sont pas identiques";
        } else {
            boolean b = maFacade.changerMotDePasse(login, motDePasse1);
            if (!b) {
                message = "Le nouveau mot de passe est identique à l'actuel";
            } else {
                message = "Votre mot de passe a bien été changé";
            }
        }
        request.setAttribute("message", message);
        request.setAttribute("aAfficher", "parametres");
        request.getRequestDispatcher("/WEB-INF/homePage/homePage.jsp").forward(request, response);
    }

    /**
     * Supprime le compte de l'utilisateur courant
     * @param request la requête
     * @param response la réponse
     * @throws ServletException servlet exception
     * @throws IOException entrée/sortie exception
     */
    @RolesAllowed("utilisateur")
    private void supprimerCompte(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String confirmation = request.getParameter("confirmation");
        if (null == confirmation) {
            request.setAttribute("aAfficher", "suppressionCompte");
            request.getRequestDispatcher("/WEB-INF/homePage/homePage.jsp").forward(request, response);
        } else {
            if (confirmation.equals("ok")) {
                String login = request.getUserPrincipal().getName();
                String motDePasse = request.getParameter("motDePasse");
                if (maFacade.verifierMotDePasse(login, motDePasse)) {
                    try {
                        maFacade.supprimerUtilisateur(login);
                    } catch (PasConducteurException e) {
                        maFacade.creerNotification(login,"Vous avez essayé de supprimer un compte qui ne vous appartient pas");
                    } catch (PasVehiculeUtilisateur pasVehiculeUtilisateur) {
                        maFacade.creerNotification(login,"Vous avez essayé de supprimer un compte qui ne vous appartient pas");
                    }
                    deconnexion(request,response);
                } else {
                    request.setAttribute("message", "Mot de passe incorrect");
                    request.setAttribute("aAfficher", "suppressionCompte");
                    request.getRequestDispatcher("/WEB-INF/homePage/homePage.jsp").forward(request, response);
                }
            }
        }
    }

    /**
     * Affiche la page de recherche d'un trajet
     * @param request la requête
     * @param response la réponse
     * @throws ServletException servlet exception
     * @throws IOException entrée/sortie exception
     */
    @RolesAllowed("utilisateur")
    private void rechercherTrajet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<VilleDTO> listeVilles = maFacade.getListeVilleDTO();
        request.setAttribute("listeVilles", listeVilles);
        request.setAttribute("aAfficher", "rechercherTrajet");
        request.getRequestDispatcher("/WEB-INF/homePage/homePage.jsp").forward(request, response);
    }

    /**
     * Affiche les résultats de recherche d'un trajet
     * @param request la requête
     * @param response la réponse
     * @throws ServletException servlet exception
     * @throws IOException entrée/sortie exception
     */
    @RolesAllowed("utilisateur")
    private void afficherRechercheTrajet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String villeDepart = request.getParameter("nomVilleDepart");
        String nomVilleDepart = villeDepart.substring(0, villeDepart.length() - 4);
        String departementVilleDepart = villeDepart.substring(villeDepart.length() - 3, villeDepart.length() - 1);
        String villeArrive = request.getParameter("nomVilleArrivee");
        String nomVilleArrivee = villeArrive.substring(0, villeArrive.length() - 4);
        String departementVilleArrivee = villeArrive.substring(villeArrive.length() - 3, villeArrive.length() - 1);
        String date = request.getParameter("date");
        String prix = request.getParameter("prix");
        List<TrajetDTO> listeTrajetRecherche;
        try {
            listeTrajetRecherche = maFacade.rechercheTrajet(nomVilleDepart, departementVilleDepart, nomVilleArrivee, departementVilleArrivee, date, prix);
            request.setAttribute("listeTrajetRecherche", listeTrajetRecherche);
            List<VilleDTO> listeVilles = maFacade.getListeVilleDTO();
            request.setAttribute("listeVilles", listeVilles);
            request.setAttribute("villeDepart", villeDepart);
            request.setAttribute("villeArrivee", villeArrive);
            request.setAttribute("date", date);
            if (!prix.equals("")) {
                request.setAttribute("prix", prix);
            }
            request.setAttribute("aAfficher", "rechercherTrajet");
            request.setAttribute("resultatsRecherche", "afficher");
            request.getRequestDispatcher("/WEB-INF/homePage/homePage.jsp").forward(request, response);
        } catch (DateAnterieureException | VilleNonTrouvee e) {
            request.setAttribute("messageErreur", e.getMessage());
            rechercherTrajet(request, response);
        } catch (ParseException e) {
            request.setAttribute("messageErreur", "Merci de rentrer une date valide (jj/mm/aaaa)");
            rechercherTrajet(request, response);
        }
    }

    /**
     * Affiche les détails d'un trajet consulté
     * @param request la requête
     * @param response la réponse
     * @throws ServletException servlet exception
     * @throws IOException entrée/sortie exception
     */
    @RolesAllowed("utilisateur")
    private void detailsTrajet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idTrajet = Integer.parseInt(request.getParameter("idTrajet"));
        String login = request.getUserPrincipal().getName();
        try{
            TrajetDTO trajetDTO = maFacade.avoirTrajet(login,idTrajet,"recherche");
            request.setAttribute("trajet", trajetDTO);
            request.setAttribute("aAfficher", "detailsTrajet");
            request.getRequestDispatcher("/WEB-INF/homePage/homePage.jsp").forward(request, response);
        }catch (AccesInterditException e){
            maFacade.creerNotification(login,"Vous avez essayé de voir un trajet dont vous n'avez pas les droits");
            rechercherTrajet(request,response);
        }
    }

    /**
     * Lance une demande de réservation pour un trajet
     * @param request la requête
     * @param response la réponse
     * @throws ServletException servlet exception
     * @throws IOException entrée/sortie exception
     */
    @RolesAllowed("utilisateur")
    private void reserverTrajet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idVilleArrivee;
        if(request.getParameter("etapeArrivee").equals("")){
           idVilleArrivee = request.getParameter("villeArrivee");
        } else {
            idVilleArrivee = request.getParameter("etapeArrivee");
        }
        int nbPlaces = Integer.parseInt(request.getParameter("nbPlacesReservees"));
        int idTrajet = Integer.parseInt(request.getParameter("idTrajet"));
        String login = request.getUserPrincipal().getName();
        try {
            maFacade.reserverPlace(login, idTrajet, nbPlaces, idVilleArrivee);
        } catch (VilleNonTrouvee villeNonTrouvee) {
            maFacade.creerNotification(login, "La ville d'arrivée n'a pas été trouvée.");
        } catch (AccesInterditException e) {
            maFacade.creerNotification(login,e.getMessage());
        }
        afficherNotification(request);
        request.setAttribute("aAfficher", "accueil");
        request.getRequestDispatcher("/WEB-INF/homePage/homePage.jsp").forward(request, response);
    }

    /**
     * Permet de gérer un trajet dont on est le conducteur
     * @param request la requête
     * @param response la réponse
     * @throws ServletException servlet exception
     * @throws IOException entrée/sortie exception
     */
    @RolesAllowed("utilisateur")
    private void gererTrajet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idTrajet = Integer.parseInt(request.getParameter("idTrajet"));
        String login = request.getUserPrincipal().getName();
        try{
            TrajetDTO trajetDTO = maFacade.avoirTrajet(login, idTrajet,"conducteur");
            int nbPlacesRestantes = maFacade.avoirNbPlacesRestantes(idTrajet);
            List<ReservationDTO> reservationsAttente = new ArrayList<>();
            List<ReservationDTO> reservationsAcceptees = new ArrayList<>();
            try {
                reservationsAttente = maFacade.avoirReservationsEnAttente(login, idTrajet);
                reservationsAcceptees = maFacade.avoirReservationsAcceptees(login, idTrajet);
            } catch (PasConducteurException e) {
                maFacade.creerNotification(login, "Vous avez essayé de modifier un trajet dont vous n'êtes pas le conducteur.");
            }
            request.setAttribute("reservationsAttente", reservationsAttente);
            request.setAttribute("reservationsAcceptees", reservationsAcceptees);
            request.setAttribute("trajet", trajetDTO);
            request.setAttribute("nbPlacesRestantes", nbPlacesRestantes);
            request.setAttribute("aAfficher", "gestionTrajet");
            request.getRequestDispatcher("/WEB-INF/homePage/homePage.jsp").forward(request, response);
        }catch (AccesInterditException e){
            maFacade.creerNotification(login,"Vous avez essayé de voir un trajet dont vous n'avez pas les droits");
            voirTrajetsEnCours(request,response);
        }
    }

    /**
     * Permet de supprimer un trajet dont on est le conducteur
     * @param request la requête
     * @param response la réponse
     * @throws ServletException servlet exception
     * @throws IOException entrée/sortie exception
     */
    @RolesAllowed("utilisateur")
    private void supprimerTrajet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getUserPrincipal().getName();
        int idTrajet = Integer.parseInt(request.getParameter("idTrajet"));
        try{
            maFacade.annulerTrajet(login, idTrajet);
        }catch(PasConducteurException e){
            maFacade.creerNotification(login, "Vous avez essayé d'annuler un trajet dont vous n'êtes pas le conducteur");
        }
        voirTrajetsEnCours(request, response);
    }

    /**
     * Permet d'accepter une réservation sur un trajet
     * @param request la requête
     * @param response la réponse
     * @throws ServletException servlet exception
     * @throws IOException entrée/sortie exception
     */
    @RolesAllowed("utilisateur")
    private void accepterReservation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getUserPrincipal().getName();
        int idReservation = Integer.parseInt(request.getParameter("idReservation"));
        try {
            maFacade.accepterReservation(login, idReservation);
        } catch (PasConducteurException e) {
            maFacade.creerNotification(login,"Vous avez essayé d'accepter la réservation d'un trajet donc vous n'êtes pas le conducteur");
        }
        int idTrajet = Integer.parseInt(request.getParameter("idTrajet"));
        request.setAttribute("idTrajet", idTrajet);
        gererTrajet(request, response);
    }

    /**
     * Permet de refuser une réservation sur un trajet
     * @param request la requête
     * @param response la réponse
     * @throws ServletException servlet exception
     * @throws IOException entrée/sortie exception
     */
    @RolesAllowed("utilisateur")
    private void refuserReservation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getUserPrincipal().getName();
        int idReservation = Integer.parseInt(request.getParameter("idReservation"));
        try {
            maFacade.refuserReservation(login, idReservation);
        } catch (PasConducteurException e) {
            maFacade.creerNotification(login,"Vous avez essayé d'accepter la réservation d'un trajet donc vous n'êtes pas le conducteur");
        }
        int idTrajet = Integer.parseInt(request.getParameter("idTrajet"));
        request.setAttribute("idTrajet", idTrajet);
        gererTrajet(request, response);
    }

    /**
     * Permet de supprimer une réservation
     * @param request la requête
     * @param response la réponse
     * @throws ServletException servlet exception
     * @throws IOException entrée/sortie exception
     */
    @RolesAllowed("utilisateur")
    private void supprimerReservation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idReservation = Integer.parseInt(request.getParameter("idReservation"));
        String login = request.getUserPrincipal().getName();
        maFacade.annulerReservation(login,idReservation);
        voirTrajetsEnCours(request, response);
    }

    /**
     * Déconnecte l'utilisateur courant
     * @param request la requête
     * @param response la réponse
     * @throws ServletException servlet exception
     * @throws IOException entrée/sortie exception
     */
    @RolesAllowed("utilisateur")
    private void deconnexion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.logout();
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath());
    }


    /**
     * Ouvre un écran permettant de faire une appréciation sur un tajet et un utilisateur donné
     * @param request la requête
     * @param response la réponse
     * @throws ServletException servlet exception
     * @throws IOException entrée/sortie exception
     */
    @RolesAllowed("utilisateur")
    private void apprecierTrajet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idTrajet = Integer.parseInt(request.getParameter("idTrajet"));
        String login = request.getUserPrincipal().getName();
        try {
            TrajetDTO trajet = maFacade.avoirTrajet(login, idTrajet,"tous");
            List<UtilisateurDTO> listePersonnes = maFacade.avoirPersonnesTrajet(login, idTrajet);
            request.setAttribute("listePersonnes", listePersonnes);
            request.setAttribute("trajet", trajet);
            request.setAttribute("aAfficher", "detailsAppreciation");
            request.getRequestDispatcher("/WEB-INF/homePage/homePage.jsp").forward(request, response);
        }catch (AccesInterditException e){
            maFacade.creerNotification(login,"Vous avez essayé de voir un trajet dont vous n'avez pas les droits");
            voirAppreciations(request,response);
        }
    }

    /**
     * Valide une appréciation sur un trajet et une personne donnés
     * @param request la requête
     * @param response la réponse
     * @throws ServletException servlet exception
     * @throws IOException entrée/sortie exception
     */
    @RolesAllowed("utilisateur")
    private void noter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getUserPrincipal().getName();
        String loginDestinataire = request.getParameter("loginPersonneAppreciation");
        int note = Integer.parseInt(request.getParameter("note"));
        String commentaire = request.getParameter("commentaire");
        int idTrajet = Integer.parseInt(request.getParameter("idTrajet"));
        maFacade.donnerAppreciation(login,idTrajet,commentaire,note,loginDestinataire);
        apprecierTrajet(request,response);
    }

    /**
     * Charge dans la requête la liste des notification s de l'utilisateur courant
     * @param request la requête
     */
    @RolesAllowed("utilisateur")
    private void afficherNotification(HttpServletRequest request){
        String login = request.getUserPrincipal().getName();
        List<Notification> listeNotif = maFacade.avoirListeNotification(login);
        request.setAttribute("listeNotif", listeNotif);
    }

    /**
     * Supprime la notification que l'utilisateur ne veut plus voir affichée
     * @param request la requête
     * @param response la réponse
     * @throws ServletException servlet exception
     * @throws IOException entrée/sortie exception
     */
    @RolesAllowed("utilisateur")
    private void supprimerNotification(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getUserPrincipal().getName();
        int idNotif = Integer.parseInt(request.getParameter("idNotif"));
        maFacade.supprimerNotification(login, idNotif);
        voirAccueil(request, response);
    }

    /**
     * Affiche les détails d'une réservation
     * @param request la requête
     * @param response la réponse
     * @throws ServletException servlet exception
     * @throws IOException entrée/sortie exception
     */
    @RolesAllowed("utilisateur")
    private void detailsReservation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idTrajet = Integer.parseInt(request.getParameter("idTrajet"));
        int idRes = Integer.parseInt(request.getParameter("idReservation"));
        String login = request.getUserPrincipal().getName();
        try {
            TrajetDTO trajetDTO = maFacade.avoirTrajet(login, idTrajet,"passager");
            ReservationDTO reservationDTO = maFacade.avoirReservationDTO(login,idRes);
            request.setAttribute("trajet", trajetDTO);
            request.setAttribute("reservation", reservationDTO);
            request.setAttribute("aAfficher", "detailsTrajet");
            request.getRequestDispatcher("/WEB-INF/homePage/homePage.jsp").forward(request, response);
        }catch (AccesInterditException e){
            maFacade.creerNotification(login,"Vous avez essayé de voir un trajet dont vous n'avez pas les droits");
            voirTrajetsEnCours(request,response);
        }
    }

    /**
     * Valide la suppression d'un véhicule
     * @param request la requête
     * @param response la réponse
     * @throws ServletException servlet exception
     * @throws IOException entrée/sortie exception
     */
    @RolesAllowed("utilisateur")
    private void supprimerVehicule(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idVehicule = Integer.parseInt(request.getParameter("idVehicule"));
        String login = request.getUserPrincipal().getName();
        try{
            maFacade.supprimerVehicule(login,idVehicule);
            request.setAttribute("message","Vehicule supprimé");
            voirVehicules(request,response);
        }catch(PasVehiculeUtilisateur e){
            maFacade.creerNotification(login,"Vous avez essayé de supprimer un vehicule qui ne vous appartient pas");
            voirVehicules(request,response);
        }
    }

    /**
     * Supprime toutes les reservations en attente
     * @param request la requête
     * @param response la réponse
     * @throws ServletException servlet exception
     * @throws IOException entrée/sortie exception
     */
    @RolesAllowed("utilisateur")
    private void toutSupprimer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getUserPrincipal().getName();
        int idTrajet = Integer.parseInt(request.getParameter("idTrajet"));
        try{
            maFacade.supprimerToutesReservationsTrajet(login, idTrajet);
        }catch(PasConducteurException e){
            maFacade.creerNotification(login, "Vous avez essayé de supprimer des réservations d'un trajet dont vous n'êtes pas le conducteur");
        }
        gererTrajet(request,response);
    }

}
