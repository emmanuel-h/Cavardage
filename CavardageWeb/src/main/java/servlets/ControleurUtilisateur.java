package servlets;

import dtos.HistoriqueDTO;
import ejbs.MaFacadeUtilisateur;
import entities.Gabarit;
import entities.Vehicule;

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
                case "creerTrajet":
                    creerTrajet(request,response);
                    break;
                case "voirHistorique":
                    voirHistorique(request,response);
                    break;
                case "voirVehicules":
                    voirVehicules(request,response);
                    break;
                case "enregistrerVehicule":
                    enregistrerVehicule(request,response);
                default :
                    //display homepage
            }
        }
    }

    private void creerTrajet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void voirHistorique(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login =(String) request.getSession().getAttribute("utilisateur");
        List<HistoriqueDTO> listeHistorique = maFacade.historiqueUtilisateur(login);
        request.setAttribute("listeHistorique", listeHistorique);
        request.getRequestDispatcher("/WEB-INF/homePage/historique.jsp").forward(request, response);
    }

    private void voirVehicules(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Gabarit> listeGabarit = maFacade.listeGabarits();
        request.setAttribute("listeGabarits",listeGabarit);
        request.getRequestDispatcher("/WEB-INF/homePage/vehicules.jsp").forward(request, response);
    }

    private void enregistrerVehicule(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


}
