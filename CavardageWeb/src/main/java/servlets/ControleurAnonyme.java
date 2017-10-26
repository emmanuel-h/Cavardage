package servlets;

import dtos.TrajetDTO;
import dtos.UtilisateurDTO;
import dtos.VilleDTO;
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
import java.util.List;

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
            getListeVilles(request);
            getListeDernierTrajet(request);
            request.getRequestDispatcher("/WEB-INF/accueil.jsp")
                    .forward(request, response);
        }else{
            switch (test){
                case "connexion":
                    connexion(request,response);
                    break;
                case "inscription": request.getRequestDispatcher("/WEB-INF/inscription.jsp")
                            .forward(request, response);
                    break;
                case "inscrire":
                    inscription(request,response);
                    break;
                default:
                    System.out.println("default");
                    break;
            }
        }
    }

    private void getListeDernierTrajet(HttpServletRequest request) {
        List<TrajetDTO> listeDernierTrajet = ejb.dernierAjout();
        request.setAttribute("listeDernierTrajet",listeDernierTrajet);
    }


    private void getListeVilles(HttpServletRequest request){
        List<VilleDTO> listeVilles = ejb.getListeVille();
        request.setAttribute("listeVilles",listeVilles);
    }


    public void connexion(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
        String login,mdp;
        login = request.getParameter("login");
        mdp = request.getParameter("mdp");
        try {
            UtilisateurDTO utilisateurDTO = ejb.connexion(login, mdp);
            request.getSession().setAttribute("utilisateur",login);
            if(utilisateurDTO.getRole().equals("utilisateur")) {
                request.getRequestDispatcher("/WEB-INF/homePage/homePage.jsp").forward(request, response);;
            }else {
                request.getRequestDispatcher("/WEB-INF/accueilAdmin.jsp").forward(request, response);;
            }
        }catch (UtilisateurNonInscritException exception){
            request.getRequestDispatcher("/WEB-INF/accueil.jsp")
                    .forward(request, response);
        }
    }



    public void inscription(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
        String login,mdp,nom,mdp_confirmer;
        login = request.getParameter("login");
        nom = request.getParameter("nom");
        mdp = request.getParameter("mdp");
        mdp_confirmer = request.getParameter("mdp_confirmer");
        if(mdp.equals(mdp_confirmer)) {
            try {
                boolean succesInscription=ejb.inscription(login, nom, mdp);
                if(succesInscription) {
                    request.getSession().setAttribute("utilisateur", login);
                    request.getRequestDispatcher("/WEB-INF/homePage/homePage.jsp")
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
    }


}
