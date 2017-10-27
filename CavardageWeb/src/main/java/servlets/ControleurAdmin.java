package servlets;

import dtos.VilleDTO;
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
            request.setAttribute("gestionVille", false);
            request.setAttribute("gestionGabarit", false);
        }else{
            switch (test){
                case "gererVille":
                    setListeVilles(request);
                    setGestionVille(request);
                    request.getRequestDispatcher("/WEB-INF/accueilAdmin.jsp").forward(request, response);
                    break;
                case "gererGabarit":
                    setListeGabarits(request);
                    setGestionGabarit(request);
                    request.getRequestDispatcher("/WEB-INF/accueilAdmin.jsp").forward(request, response);
                    break;
                case "ajouterVille":
                    String nomVilleAjouter = request.getParameter("nomVilleAAjouter");
                    String departementVilleAjouter = request.getParameter("departementVilleAAjouter");
                    System.out.println("Ville : " + nomVilleAjouter);
                    ajouterVille(nomVilleAjouter,departementVilleAjouter);
                    setGestionVille(request);
                    setListeVilles(request);
                    request.getRequestDispatcher("/WEB-INF/accueilAdmin.jsp").forward(request, response);
                    break;
                case "ajouterGabarit":
                    String nomGabaritAjouter = request.getParameter("nomGabaritAAjouter");
                    ajouterGabarit(nomGabaritAjouter);
                    setGestionGabarit(request);
                    setListeGabarits(request);
                    request.getRequestDispatcher("/WEB-INF/accueilAdmin.jsp").forward(request, response);
                    break;
                case "supprimerGabarit":
                    String nomGabaritSupp = request.getParameter("nomGabaritASupprimer");
                    supprimerGabarit(nomGabaritSupp);
                    setGestionGabarit(request);
                    setListeGabarits(request);
                    request.getRequestDispatcher("/WEB-INF/accueilAdmin.jsp").forward(request, response);
                    break;
                case "supprimerVille":
                    String villeSupp = request.getParameter("nomVilleASupprimer");
                    String nomVilleSupp = villeSupp.substring(0,villeSupp.length()-4);
                    String departementVilleSupp = villeSupp.substring(villeSupp.length()-3,villeSupp.length()-1);
                    System.out.println(departementVilleSupp);
                    supprimerVille(nomVilleSupp,departementVilleSupp);
                    setGestionVille(request);
                    setListeVilles(request);
                    request.getRequestDispatcher("/WEB-INF/accueilAdmin.jsp").forward(request, response);
                    break;
                default:
                    break;
            }
        }
    }

    private void setGestionVille(HttpServletRequest request){
        request.setAttribute("gestionVille", true);
        request.setAttribute("gestionGabarit", false);
    }

    private void setGestionGabarit(HttpServletRequest request){
        request.setAttribute("gestionVille", false);
        request.setAttribute("gestionGabarit", true);
    }

    private void setListeVilles(HttpServletRequest request){
        List<VilleDTO> listeVilles = ejb.getListeVilleDTO();
        request.setAttribute("listeVilles",listeVilles);
    }

    private void setListeGabarits(HttpServletRequest request){
        List<String> listeGabarits = ejb.getListeGabarits();
        request.setAttribute("listeGabarits", listeGabarits);
    }

    private boolean ajouterVille(String nomVille,String departement){
        return ejb.ajouterVille(nomVille,departement);
    }

    private boolean ajouterGabarit(String nomGabarit){
        return ejb.ajouterGabarit(nomGabarit);
    }

    private boolean supprimerVille(String nomVille,String departement){
        return ejb.supprimerVille(nomVille,departement);
    }

    private boolean supprimerGabarit(String nomGabarit){
        return ejb.supprimerGabarit(nomGabarit);
    }
}
