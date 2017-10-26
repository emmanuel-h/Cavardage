package servlets;

import ejbs.MaFacadeUtilisateur;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
                default :
                    //display homepage
            }
        }
    }

    private void creerTrajet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    private void voirHistorique(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

}
