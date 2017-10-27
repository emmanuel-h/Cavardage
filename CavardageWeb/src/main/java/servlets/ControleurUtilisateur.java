package servlets;

import dtos.HistoriqueDTO;
import dtos.VehiculeDTO;
import dtos.VilleDTO;
import ejbs.MaFacadeUtilisateur;
import entities.Gabarit;
import entities.Ville;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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
        String aFaire = request.getParameter("afaire");
        if(null == aFaire){
            if(null != request.getSession().getAttribute("utilisateur")){
                //display homepage
            } else {
                request.getRequestDispatcher("/WEB-INF/accueil.jsp");
            }
        } else {
            switch (aFaire) {
                case "trajetsEnCours":
                    voirTrajetsEnCours(request, response);
                    break;
                case "creerTrajet":
                    voirCreerTrajet(request,response);
                    break;
                case "voirVehicules":
                    voirVehicules(request, response);
                    break;
                case "voirHistorique":
                    voirHistorique(request,response);
                    break;
                case "enregistrerVehicule":
                    enregistrerVehicule(request,response);
                case "voirAppreciations":
                    voirAppreciations(request, response);
                    break;
                case "parametres":
                    parametres(request, response);
                    break;
                case "ajouterVehicule":
                    ajouterVehicule(request, response);
                    break;
                case "enregistrerTrajet":
                    enregistrerTrajet(request, response);
                    break;
                case "changerMotDePasse":
                    changerMotDepasse(request,response);
                    break;
                case "supprimerCompte":
                    supprimerCompte(request,response);
                    break;
                default :
                    //display homepage
            }
        }
    }

    private void enregistrerTrajet(HttpServletRequest request, HttpServletResponse response) {
        String login = (String) request.getSession().getAttribute("utilisateur");
        String villeDepart = request.getParameter("villeDepart");
        String villeArrivee = request.getParameter("villeArrivee");
        String date = request.getParameter("date");
        String heure = request.getParameter("heure")+"h"+request.getParameter("minute");
        String nomVehicule = request.getParameter("vehicule");
        String prixVoyage = request.getParameter("prixVoyage");
        String[] etapes = request.getParameterValues("etape");
        String[] prix = request.getParameterValues("prix");
        System.out.println(etapes);
        maFacade.preAjoutVille(login, villeDepart, villeArrivee, nomVehicule, etapes, prix, date, heure, prixVoyage);
    }

    private void voirTrajetsEnCours(HttpServletRequest request, HttpServletResponse response){

    }

    private void voirCreerTrajet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = (String) request.getSession().getAttribute("utilisateur");
        List<VehiculeDTO> vehiculeDTOS = maFacade.listeVehicules(login);
        List<VilleDTO> listeVilles = maFacade.getListeVilleDTO();
        request.setAttribute("listeVehicules", vehiculeDTOS);
        request.setAttribute("listeVilles", listeVilles);
        request.setAttribute("aAfficher", "creerTrajet");
        request.getRequestDispatcher("/WEB-INF/homePage/homePage.jsp").forward(request, response);
    }

    private void voirVehicules(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String login = (String) request.getSession().getAttribute("utilisateur");
        List<VehiculeDTO> vehiculesDTO = maFacade.listeVehicules(login);
        request.setAttribute("listeVehicules", vehiculesDTO);
        List<Gabarit> gabarits = maFacade.listeGabarits();
        request.setAttribute("listeGabarits", gabarits);
        request.setAttribute("aAfficher", "vehicules");
        request.getRequestDispatcher("/WEB-INF/homePage/homePage.jsp").forward(request, response);
    }

    private void voirHistorique(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login =(String) request.getSession().getAttribute("utilisateur");
        List<HistoriqueDTO> listeHistorique = maFacade.historiqueUtilisateur(login);
        request.setAttribute("listeHistorique", listeHistorique);
        request.setAttribute("aAfficher", "historique");
        request.getRequestDispatcher("/WEB-INF/homePage/homePage.jsp").forward(request, response);
    }

    private void voirAppreciations(HttpServletRequest request, HttpServletResponse response){

    }

    private void parametres(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("aAfficher", "parametres");
        request.getRequestDispatcher("/WEB-INF/homePage/homePage.jsp").forward(request, response);
    }

    private void ajouterVehicule(HttpServletRequest request, HttpServletResponse response){
        String nomvehicule = request.getParameter("nomVehicule");
        String modeleVehicule = request.getParameter("modeleVehicule");
        String gabaritVehicule = request.getParameter("gabaritVehicule");
        int nbPlaces = Integer.parseInt(request.getParameter("nbPlaces"));
    }

    private void enregistrerVehicule(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void changerMotDepasse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = (String) request.getSession().getAttribute("utilisateur");
        String motDePasse1 = request.getParameter("nouveauMdP1");
        String motDePasse2 = request.getParameter("nouveauMdP2");
        String message;
        if(motDePasse1.equals("") || motDePasse2.equals("")){
            message = "Un champs n'est pas rempli";
        } else if(!motDePasse1.equals(motDePasse2)){
            message = "Les mots de passe ne sont pas identiques";
        } else {
            boolean b = maFacade.changerMotDePasse(login, motDePasse1);
            if (!b) {
                message = "Le nouveau mot de passe est identique à l'actuel";
            } else {
                message = "Votre mot de passe a bien été changé";
            }
        }
        request.setAttribute("message",message);
        request.setAttribute("aAfficher", "parametres");
        request.getRequestDispatcher("/WEB-INF/homePage/homePage.jsp").forward(request, response);
    }

    private void supprimerCompte(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String confirmation = request.getParameter("confirmation");
        if(null == confirmation){
            request.setAttribute("aAfficher", "suppressionCompte");
            request.getRequestDispatcher("/WEB-INF/homePage/homePage.jsp").forward(request, response);
        } else {
            if(confirmation.equals("ok")){
                String login = (String) request.getSession().getAttribute("utilisateur");
                String motDePasse = request.getParameter("motDePasse");
                if(maFacade.verifierMotDePasse(login,motDePasse)){
                    maFacade.supprimerUtilisateur(login);
                    request.getSession().setAttribute("utilisateur",null);
                    response.sendRedirect("ControleurAnonyme");
                } else {
                    request.setAttribute("message","Mot de passe incorrect");
                    request.setAttribute("aAfficher", "suppressionCompte");
                    request.getRequestDispatcher("/WEB-INF/homePage/homePage.jsp").forward(request, response);
                }
            }
        }
    }
}
