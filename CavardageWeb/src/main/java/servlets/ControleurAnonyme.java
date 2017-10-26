package servlets;

import dtos.UtilisateurDTO;
import ejbs.MaFacadeAnonyme;
import exceptions.LoginExistantException;
import exceptions.UtilisateurNonInscritException;

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
        String login,mdp,nom,mdp_confirmer;
        System.out.println(test);
        if(null == test){
            System.out.println("null");
            request.getRequestDispatcher("/WEB-INF/accueil.jsp")
                    .forward(request, response);
        }else{
            switch (test){
                case "connexion":
                    System.out.println("connexion");
                    login = request.getParameter("login");
                    mdp = request.getParameter("mdp");
                    try {
                        UtilisateurDTO utilisateurDTO = ejb.connexion(login, mdp);
                        request.getSession().setAttribute("utilisateur",utilisateurDTO);
                        request.getRequestDispatcher("/WEB-INF/accueil.jsp")
                                .forward(request, response);
                    }catch (UtilisateurNonInscritException exception){
                        request.getRequestDispatcher("/WEB-INF/accueil.jsp")
                                .forward(request, response);
                    }
                    break;
                case "inscription":
                    System.out.println("inscription");
                    request.getRequestDispatcher("/WEB-INF/inscription.jsp")
                            .forward(request, response);
                    break;
                case "inscrire":
                    login = request.getParameter("login");
                    nom = request.getParameter("nom");
                    mdp = request.getParameter("mdp");
                    mdp_confirmer = request.getParameter("mdp_confirmer");
                    if(mdp.equals(mdp_confirmer)) {
                        try {
                            UtilisateurDTO utilisateurDTO = ejb.inspcription(login, nom, mdp);
                            if(utilisateurDTO != null) {
                                request.getSession().setAttribute("utilisateur", utilisateurDTO);
                                request.getRequestDispatcher("/WEB-INF/accueil.jsp")
                                        .forward(request, response);
                            }else{
                                request.getRequestDispatcher("/WEB-INF/inscription.jsp")
                                        .forward(request, response);
                            }
                        }catch(LoginExistantException e){
                            request.getRequestDispatcher("/WEB-INF/inscription.jsp")
                                    .forward(request, response);
                        }
                    }else{
                        request.getRequestDispatcher("/WEB-INF/inscription.jsp")
                                .forward(request, response);
                    }

                    break;
                default:
                    System.out.println("default");
                    break;
            }
        }
    }
}
