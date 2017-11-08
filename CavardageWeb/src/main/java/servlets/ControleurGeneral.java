package servlets;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@DeclareRoles({"utilisateur","admin"})
@WebServlet("ControleurGeneral")
public class ControleurGeneral extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

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
