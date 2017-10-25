package servlets;

import ejbs.MaFacadeAnonyme;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("ControleurAnonyme")
public class ControleurAnonyme extends HttpServlet {

    @EJB
    MaFacadeAnonyme ejb;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String test = request.getParameter("afaire");
        if(null == test){
            request.getRequestDispatcher("/WEB-INF/accueil.jsp")
                    .forward(request, response);
        }else{
            switch (test){
                case "connexion":
                    request.getRequestDispatcher("/WEB-INF/accueil.jsp")
                            .forward(request, response);
                    break;
                case "inspcription":
                    request.getRequestDispatcher("/WEB-INF/inscription.jsp")
                            .forward(request, response);
                    break;
                default: break;
            }
        }
    }
}
