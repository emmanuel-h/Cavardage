package servlets;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Permet de rediriger la personne connectée vers la bonne vue
 */
@DeclareRoles({"utilisateur","admin"})
@WebServlet("ControleurGeneral")
public class ControleurGeneral extends HttpServlet {

    /**
     * On traite les requêtes en get de la même manière qu'en post
     * @param request la requête
     * @param response la réponse
     * @throws ServletException servlet exception
     * @throws IOException entrée/sortie exception
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * Seules les personnes authentifiées peuvent accéder à cette méthode, ce qui force l'ouverture de la page de connexion. On les redirige ensuite vers leur vue.
     * @param request la requête
     * @param response la réponse
     * @throws ServletException servlet exception
     * @throws IOException entrée/sortie exception
     */
    @RolesAllowed({"utilisateur","admin"})
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.isUserInRole("utilisateur")){
            response.sendRedirect("ControleurUtilisateur");
        }
        if(request.isUserInRole("admin")){
            response.sendRedirect("ControleurAdmin");
        }
    }

}
