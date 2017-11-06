package servlets;

import dtos.StatistiquesDTO;
import dtos.VilleDTO;
import ejbs.MaFacadeAdministrateur;
import exceptions.VilleNonTrouvee;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.StringTokenizer;

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
            request.getRequestDispatcher("/WEB-INF/admin/accueilAdmin.jsp").forward(request, response);
        }else{
            switch (test){
                case "gererVille":
                    setListeVilles(request);
                    setGestionVille(request);
                    request.getRequestDispatcher("/WEB-INF/admin/accueilAdmin.jsp").forward(request, response);
                    break;
                case "gererGabarit":
                    setListeGabarits(request);
                    setGestionGabarit(request);
                    request.getRequestDispatcher("/WEB-INF/admin/accueilAdmin.jsp").forward(request, response);
                    break;
                case "ajouterVille":
                    String nomVilleAjouter = request.getParameter("nomVilleAAjouter");
                    String departementVilleAjouter = request.getParameter("departementVilleAAjouter");
                    ajouterVille(nomVilleAjouter,departementVilleAjouter);
                    setGestionVille(request);
                    setListeVilles(request);
                    request.getRequestDispatcher("/WEB-INF/admin/accueilAdmin.jsp").forward(request, response);
                    break;
                case "ajouterGabarit":
                    String nomGabaritAjouter = request.getParameter("nomGabaritAAjouter");
                    ajouterGabarit(nomGabaritAjouter);
                    setGestionGabarit(request);
                    setListeGabarits(request);
                    request.getRequestDispatcher("/WEB-INF/admin/accueilAdmin.jsp").forward(request, response);
                    break;
                case "supprimerGabarit":
                    String nomGabaritSupp = request.getParameter("nomGabaritASupprimer");
                    String nomGabaritRemp = request.getParameter("nomGabaritARemplacer");
                    supprimerGabarit(nomGabaritSupp, nomGabaritRemp);
                    setGestionGabarit(request);
                    setListeGabarits(request);
                    request.getRequestDispatcher("/WEB-INF/admin/accueilAdmin.jsp").forward(request, response);
                    break;
                case "supprimerVille":
                    String villeSupp = request.getParameter("nomVilleASupprimer");
                    StringTokenizer st = new StringTokenizer(villeSupp, "()");
                    String nomVilleSupp = st.nextToken();
                    String departementVilleSupp = st.nextToken();
                    supprimerVille(nomVilleSupp,departementVilleSupp);
                    setGestionVille(request);
                    setListeVilles(request);
                    request.getRequestDispatcher("/WEB-INF/admin/accueilAdmin.jsp").forward(request, response);
                    break;
                case "statistiques":
                    voirStatistiques(request, response);
                    break;
                case "actualiserStat":
                    voirStatistiques(request, response);
                    break;
                case "deconnexion":
                    request.getSession().removeAttribute("utilisateur");
                    request.getSession().invalidate();
                    response.sendRedirect(request.getContextPath());
                    break;
                default:
                    break;
            }
        }
    }

    private void setGestionVille(HttpServletRequest request){
        request.setAttribute("aAfficher", "gestionVille");
    }

    private void setGestionGabarit(HttpServletRequest request){
        request.setAttribute("aAfficher", "gestionGabarit");
    }

    private void setStatistiques(HttpServletRequest request){
        request.setAttribute("aAfficher", "statistiques");
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
        try {
            ejb.supprimerVille(nomVille, departement);
            return true;
        } catch (VilleNonTrouvee villeNonTrouvee) {
            villeNonTrouvee.printStackTrace();
            return false;
        }
    }

    private boolean supprimerGabarit(String gabaritASupprimer, String gabaritARemplacer){
        return ejb.supprimerGabarit(gabaritASupprimer,gabaritARemplacer);
    }

    private void voirStatistiques(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setStatistiques(request);
        StatistiquesDTO stat = ejb.recupererStatistiques();
        LocalDate date = LocalDate.now();
        request.setAttribute("stat", stat);
        request.setAttribute("date", date.getDayOfMonth() + " / " + date.getMonthValue() + " / " + date.getYear());
        setStatistiques(request);
        request.getRequestDispatcher("/WEB-INF/admin/accueilAdmin.jsp").forward(request, response);
    }
}
