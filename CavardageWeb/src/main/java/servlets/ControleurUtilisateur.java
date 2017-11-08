package servlets;

import dtos.*;
import ejbs.MaFacadeUtilisateur;
import entities.Gabarit;
import entities.Notification;
import exceptions.DivisionParZeroException;
import exceptions.PasConducteurException;
import exceptions.PrixInferieurException;
import exceptions.VehiculeDejaExistantException;
import exceptions.VilleNonTrouvee;

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

@WebServlet("ControleurUtilisateur")
public class ControleurUtilisateur extends HttpServlet {

    @EJB
    MaFacadeUtilisateur maFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(request.getUserPrincipal().getName()+"Utilisateur");
        System.out.println(request.getParameter("afaire"));
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
                default:
                    //display homepage
            }
        }
    }


    private void voirAccueil(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        afficherNotification(request);
        request.setAttribute("aAfficher", "accueil");
        request.getRequestDispatcher("/WEB-INF/homePage/homePage.jsp").forward(request, response);
    }


    private void enregistrerTrajet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = (String) request.getUserPrincipal().getName();
        String villeDepart = request.getParameter("villeDepart");
        String villeArrivee = request.getParameter("villeArrivee");
        String date = request.getParameter("date");
        String heure = request.getParameter("heure");
        String minute = request.getParameter("minute");
        String nomVehicule = request.getParameter("vehicule");
        String prixVoyage = request.getParameter("prixVoyage");
        String[] etapes = request.getParameterValues("etape");
        String message;
        try {
            if (maFacade.datePosterieure(date + " " + heure + ":" + minute)) {
                try {
                    maFacade.ajouterTrajet(login, villeDepart, villeArrivee, nomVehicule, etapes, date, heure, minute, prixVoyage);
                    message = "Trajet créé";
                } catch (PrixInferieurException e) {
                    message = "Erreur : " + e.getMessage();
                }
                request.setAttribute("message", message);
                request.setAttribute("messageDate",null);
            } else {
                request.setAttribute("messageDate", "La date rentrée est antérieure à ajourd'hui");
            }
            voirCreerTrajet(request, response);
        }catch (ParseException e){
            request.setAttribute("messageDate","La date rentrée n'est pas valide");
            voirCreerTrajet(request, response);
        }
    }

    private void voirTrajetsEnCours(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = (String) request.getUserPrincipal().getName();
        Map<String,Object> listeTrajet = maFacade.avoirListeTrajetAVenir(login);
        request.setAttribute("listeTrajetsConducteur", listeTrajet.get("conducteur"));
        request.setAttribute("listeTrajetsPassager", listeTrajet.get("passager"));
        request.setAttribute("reservationEnAttente",listeTrajet.get("reservationEnAttente"));
        request.setAttribute("aAfficher", "trajetsEnCours");
        request.getRequestDispatcher("/WEB-INF/homePage/homePage.jsp").forward(request, response);
    }

    private void voirCreerTrajetTemp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<VilleDTO> listeVilles = maFacade.getListeVilleDTO();
        request.setAttribute("listeVilles", listeVilles);
        request.setAttribute("aAfficher", "creerTrajetTemp");
        request.getRequestDispatcher("/WEB-INF/homePage/homePage.jsp").forward(request, response);
    }

    private void voirCreerTrajet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = (String) request.getUserPrincipal().getName();
        String villeDepart = request.getParameter("villeDepart");
        String villeArrivee = request.getParameter("villeArrivee");
        List<VehiculeDTO> vehiculeDTOS = maFacade.listeVehicules(login);
        List<VilleDTO> listeVilles = maFacade.getListeVilleDTO();
        float prixMoyen = maFacade.avoirPrixMoyen(villeDepart, villeArrivee);
        request.setAttribute("villeDepart", villeDepart);
        request.setAttribute("villeArrivee", villeArrivee);
        request.setAttribute("listeVehicules", vehiculeDTOS);
        request.setAttribute("listeVilles", listeVilles);
        request.setAttribute("prixMoyen", prixMoyen);
        request.setAttribute("aAfficher", "creerTrajet");
        request.getRequestDispatcher("/WEB-INF/homePage/homePage.jsp").forward(request, response);
    }

    private void voirVehicules(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = (String) request.getUserPrincipal().getName();
        List<VehiculeDTO> vehiculesDTO = maFacade.listeVehicules(login);
        request.setAttribute("listeVehicules", vehiculesDTO);
        List<Gabarit> gabarits = maFacade.listeGabarits();
        request.setAttribute("listeGabarits", gabarits);
        request.setAttribute("aAfficher", "vehicules");
        request.getRequestDispatcher("/WEB-INF/homePage/homePage.jsp").forward(request, response);
    }

    private void voirHistorique(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = (String) request.getUserPrincipal().getName();
        List<HistoriqueDTO> listeHistorique = maFacade.historiqueUtilisateur(login);
        request.setAttribute("listeHistorique", listeHistorique);
        request.setAttribute("aAfficher", "historique");
        request.getRequestDispatcher("/WEB-INF/homePage/homePage.jsp").forward(request, response);
    }

    private void detailsHistorique(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = (String) request.getUserPrincipal().getName();
        int idTrajet = Integer.parseInt(request.getParameter("idTrajet"));
        HistoriqueDTO historique = maFacade.uniqueHistoriqueUtilisateur(login, idTrajet);
        TrajetDTO trajet = maFacade.avoirTrajet(idTrajet);
        request.setAttribute("histo", historique);
        request.setAttribute("trajet", trajet);
        request.setAttribute("aAfficher", "detailsHistorique");
        request.getRequestDispatcher("/WEB-INF/homePage/homePage.jsp").forward(request, response);
    }

    private void voirAppreciations(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = (String) request.getUserPrincipal().getName();
        String noteMoyenne = "";
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

    private void parametres(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("aAfficher", "parametres");
        request.getRequestDispatcher("/WEB-INF/homePage/homePage.jsp").forward(request, response);
    }

    private void enregistrerVehicule(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = (String) request.getUserPrincipal().getName();
        String nomVehicule = request.getParameter("nomVehicule");
        String modeleVehicule = request.getParameter("modeleVehicule");
        String gabaritVehicule = request.getParameter("gabaritVehicule");
        int nbPlaces = Integer.parseInt(request.getParameter("nbPlaces"));
        try{
            maFacade.ajouterVehicule(login, nomVehicule, modeleVehicule, gabaritVehicule, nbPlaces);
        }catch(VehiculeDejaExistantException e){
            request.setAttribute("message", e.getMessage());
        }
        voirVehicules(request, response);
    }

    private void changerMotDepasse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = (String) request.getUserPrincipal().getName();
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

    private void supprimerCompte(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String confirmation = request.getParameter("confirmation");
        if (null == confirmation) {
            request.setAttribute("aAfficher", "suppressionCompte");
            request.getRequestDispatcher("/WEB-INF/homePage/homePage.jsp").forward(request, response);
        } else {
            if (confirmation.equals("ok")) {
                String login = (String) request.getUserPrincipal().getName();
                String motDePasse = request.getParameter("motDePasse");
                if (maFacade.verifierMotDePasse(login, motDePasse)) {
                    maFacade.supprimerUtilisateur(login);
                    request.getSession().setAttribute("utilisateur", null);
                    response.sendRedirect("ControleurAnonyme");
                } else {
                    request.setAttribute("message", "Mot de passe incorrect");
                    request.setAttribute("aAfficher", "suppressionCompte");
                    request.getRequestDispatcher("/WEB-INF/homePage/homePage.jsp").forward(request, response);
                }
            }
        }
    }

    private void rechercherTrajet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<VilleDTO> listeVilles = maFacade.getListeVilleDTO();
        request.setAttribute("listeVilles", listeVilles);
        request.setAttribute("aAfficher", "rechercherTrajet");
        request.getRequestDispatcher("/WEB-INF/homePage/homePage.jsp").forward(request, response);
    }

    private void afficherRechercheTrajet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String villeDepart = request.getParameter("nomVilleDepart");
        String nomVilleDepart = villeDepart.substring(0, villeDepart.length() - 4);
        String departementVilleDepart = villeDepart.substring(villeDepart.length() - 3, villeDepart.length() - 1);
        String villeArrive = request.getParameter("nomVilleArrivee");
        String nomVilleArrivee = villeArrive.substring(0, villeArrive.length() - 4);
        String departementVilleArrivee = villeArrive.substring(villeArrive.length() - 3, villeArrive.length() - 1);
        String date = request.getParameter("date");
        String prix = request.getParameter("prix");
        List<TrajetDTO> listeTrajetRecherche = maFacade.rechercheTrajet(nomVilleDepart, departementVilleDepart, nomVilleArrivee, departementVilleArrivee, date, prix);
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
    }

    private void detailsTrajet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idTrajet = Integer.parseInt(request.getParameter("idTrajet"));
        TrajetDTO trajetDTO = maFacade.avoirTrajet(idTrajet);
        request.setAttribute("trajet", trajetDTO);
        request.setAttribute("aAfficher", "detailsTrajet");
        request.getRequestDispatcher("/WEB-INF/homePage/homePage.jsp").forward(request, response);
    }

    private void reserverTrajet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idVilleArrivee;
        if(request.getParameter("etapeArrivee").equals("")){
           idVilleArrivee = request.getParameter("villeArrivee");
        } else {
            idVilleArrivee = request.getParameter("etapeArrivee");
        }
        int nbPlaces = Integer.parseInt(request.getParameter("nbPlacesReservees"));
        int idTrajet = Integer.parseInt(request.getParameter("idTrajet"));
        String login = (String) request.getUserPrincipal().getName();
        try {
            maFacade.reserverPlace(login, idTrajet, nbPlaces, idVilleArrivee);
        } catch (VilleNonTrouvee villeNonTrouvee) {
            maFacade.creerNotification(login, "La ville d'arrivée n'a pas été trouvée.");
        }
        request.getRequestDispatcher("/WEB-INF/homePage/homePage.jsp").forward(request, response);
    }

    private void gererTrajet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idTrajet = Integer.parseInt(request.getParameter("idTrajet"));
        TrajetDTO trajetDTO = maFacade.avoirTrajet(idTrajet);
        int nbPlacesRestantes = maFacade.avoirNbPlacesRestantes(idTrajet);
        String login = (String) request.getUserPrincipal().getName();
        List<ReservationDTO> reservationsAttente = new ArrayList<>();
        List<ReservationDTO> reservationsAcceptees = new ArrayList<>();
        try {
            reservationsAttente = maFacade.avoirReservationsEnAttente(login, idTrajet);
            reservationsAcceptees = maFacade.avoirReservationsAcceptees(login, idTrajet);
         }catch (PasConducteurException e){
            maFacade.creerNotification(login,"Vous avez essayé de modifier un trajet dont vous n'êtes pas le conducteur.");
        }
        request.setAttribute("reservationsAttente",reservationsAttente);
        request.setAttribute("reservationsAcceptees",reservationsAcceptees);
        request.setAttribute("trajet", trajetDTO);
        request.setAttribute("nbPlacesRestantes", nbPlacesRestantes);
        request.setAttribute("aAfficher", "gestionTrajet");
        request.getRequestDispatcher("/WEB-INF/homePage/homePage.jsp").forward(request, response);
    }

    private void supprimerTrajet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = (String) request.getUserPrincipal().getName();
        int idTrajet = Integer.parseInt(request.getParameter("idTrajet"));
        try{
            maFacade.annulerTrajet(login, idTrajet);
        }catch(PasConducteurException e){
            maFacade.creerNotification(login, "Vous avez essayé d'annuler un trajet dont vous n'êtes pas le conducteur");
        }
        rechercherTrajet(request, response);
    }

    private void accepterReservation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = (String) request.getUserPrincipal().getName();
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

    private void refuserReservation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = (String) request.getUserPrincipal().getName();
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

    private void supprimerReservation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idReservation = Integer.parseInt(request.getParameter("idReservation"));
        maFacade.annulerReservation(idReservation);
        voirTrajetsEnCours(request, response);
    }

    private void deconnexion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("utilisateur");
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath());
    }

    private void apprecierTrajet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idTrajet = Integer.parseInt(request.getParameter("idTrajet"));
        String login = (String) request.getUserPrincipal().getName();
        TrajetDTO trajet = maFacade.avoirTrajet(idTrajet);
        List<UtilisateurDTO> listePersonnes = maFacade.avoirPersonnesTrajet(login,idTrajet);
        request.setAttribute("listePersonnes",listePersonnes);
        request.setAttribute("trajet",trajet);
        request.setAttribute("aAfficher", "detailsAppreciation");
        request.getRequestDispatcher("/WEB-INF/homePage/homePage.jsp").forward(request, response);
    }

    private void noter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = (String) request.getUserPrincipal().getName();
        String loginDestinataire = request.getParameter("loginPersonneAppreciation");
        int note = Integer.parseInt(request.getParameter("note"));
        String commentaire = request.getParameter("commentaire");
        int idTrajet = Integer.parseInt(request.getParameter("idTrajet"));
        maFacade.donnerAppreciation(login,idTrajet,commentaire,note,loginDestinataire);
        apprecierTrajet(request,response);
    }

    private void afficherNotification(HttpServletRequest request){
        String login = (String) request.getUserPrincipal().getName();
        List<Notification> listeNotif = maFacade.avoirListeNotification(login);
        request.setAttribute("listeNotif", listeNotif);
    }


    private void supprimerNotification(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = (String) request.getUserPrincipal().getName();
        int idNotif = Integer.parseInt(request.getParameter("idNotif"));
        maFacade.supprimerNotification(login, idNotif);
        voirAccueil(request, response);
    }

    private void detailsReservation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idTrajet = Integer.parseInt(request.getParameter("idTrajet"));
        int idRes = Integer.parseInt(request.getParameter("idReservation"));
        TrajetDTO trajetDTO = maFacade.avoirTrajet(idTrajet);
        ReservationDTO reservationDTO = maFacade.avoirReservationDTO(idRes);
        request.setAttribute("trajet", trajetDTO);
        request.setAttribute("reservation", reservationDTO);
        request.setAttribute("aAfficher", "detailsTrajet");
        request.getRequestDispatcher("/WEB-INF/homePage/homePage.jsp").forward(request, response);
    }
}
