package servlets;

import dtos.HistoriqueDTO;
import dtos.VehiculeDTO;
import ejbs.MaFacadeUtilisateur;
import entities.Gabarit;

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
        String nomVehicule = request.getParameter("vehicule");
        String[] etapes = request.getParameterValues("etape");
    }

    private void voirTrajetsEnCours(HttpServletRequest request, HttpServletResponse response){

    }

    private void voirCreerTrajet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = (String) request.getSession().getAttribute("utilisateur");
        List<VehiculeDTO> vehiculeDTOS = maFacade.listeVehicules(login);
        request.setAttribute("listeVehicules", vehiculeDTOS);
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

    private void parametres(HttpServletRequest request, HttpServletResponse response){

    }

    private void ajouterVehicule(HttpServletRequest request, HttpServletResponse response){
        String nomvehicule = request.getParameter("nomVehicule");
        String modeleVehicule = request.getParameter("modeleVehicule");
        String gabaritVehicule = request.getParameter("gabaritVehicule");
        int nbPlaces = Integer.parseInt(request.getParameter("nbPlaces"));
    }

    private void enregistrerVehicule(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


}
