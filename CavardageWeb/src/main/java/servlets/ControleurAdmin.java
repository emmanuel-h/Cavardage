package servlets;

import ejbs.MaFacadeAdministrateur;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("ControleurAdmin")
public class ControleurAdmin extends HttpServlet {

    @EJB
    MaFacadeAdministrateur ejb;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String test = request.getParameter("boutonAdmin");

        if(null == test){
            request.getRequestDispatcher("/WEB-INF/accueilAdmin.jsp").forward(request, response);
        }else{
            switch (test){
                case "gererVille":
                    List<String> listeVilles = ejb.getListeVilles();
                    if(null != listeVilles) {
                        request.setAttribute("listeVilles", listeVilles);
                        request.setAttribute("gestionVille", true);
                    }else{
                        request.setAttribute("gestionVille", false);
                    }
                    request.getRequestDispatcher("/WEB-INF/accueilAdmin.jsp").forward(request, response);
                    break;
                case "gererGabarit":
                    List<String> listeGabarits = ejb.getListeGabarits();
                    if(null != listeGabarits){
                        request.setAttribute("listeGabarits", listeGabarits);
                        request.setAttribute("gestionGabarit", true);
                    }else{
                        request.setAttribute("gestionGabarit", false);
                    }
                    request.getRequestDispatcher("/WEB-INF/accueilAdmin.jsp").forward(request, response);
                    break;
                default:
                    break;
            }
        }
    }
}
