package servlets;

import dtos.TrajetDTO;
import dtos.VilleDTO;
import ejbs.MaFacadeAnonyme;
import exceptions.DatePosterieureException;
import exceptions.LoginExistantException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@WebServlet("ControleurAnonyme")
public class ControleurAnonyme extends HttpServlet {

    @EJB
    private MaFacadeAnonyme ejb;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String test = request.getParameter("afaire");
        if(null == test){
            getListeVilles(request);
            getListeDernierTrajet(request);
            request.getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
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
                case "afficherRechercheTrajet":
                    getResultatRecherche(request,response);
                    getListeDernierTrajet(request);
                    break;
                case "deconnexion":
                    request.getSession().removeAttribute("utilisateur");
                    request.getSession().invalidate();
                    response.sendRedirect(request.getContextPath());
                    break;
                default:
                    getListeVilles(request);
                    getListeDernierTrajet(request);
                    request.getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
                    break;
            }
        }
    }

    private void getResultatRecherche(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
        String villeDepart = request.getParameter("nomVilleDepart");
        String nomVilleDepart = villeDepart.substring(0,villeDepart.length()-4);
        String departementVilleDepart = villeDepart.substring(villeDepart.length()-3,villeDepart.length()-1);
        String villeArrive = request.getParameter("nomVilleArrivee");
        String nomVilleArrivee = villeArrive.substring(0,villeArrive.length()-4);
        String departementVilleArrivee = villeArrive.substring(villeArrive.length()-3,villeArrive.length()-1);
        String date = request.getParameter("date");
        String prix = request.getParameter("prix");
        List<TrajetDTO> listeTrajetRecherche;
        try {
            listeTrajetRecherche = ejb.rechercheTrajet(nomVilleDepart, departementVilleDepart, nomVilleArrivee, departementVilleArrivee, date, prix);
            request.setAttribute("listeTrajetRecherche", listeTrajetRecherche);
            getListeDernierTrajet(request);
            getListeVilles(request);
            request.setAttribute("villeDepart",villeDepart);
            request.setAttribute("villeArrivee",villeArrive);
            request.setAttribute("date",date);
            if(!prix.equals("")) {
                request.setAttribute("prix", prix);
            }
            request.setAttribute("resultatsRecherche","afficher");
            request.getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
        } catch (DatePosterieureException e) {
            request.setAttribute("messageErreur", e.getMessage());
            retournerAccueil(request, response);
        } catch (ParseException e) {
            request.setAttribute("messageErreur", "Merci de rentrer une date valide (jj/mm/aaaa)");
            retournerAccueil(request, response);
        }
    }

    private void retournerAccueil(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getListeVilles(request);
        getListeDernierTrajet(request);
        request.getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
    }

    private void getListeDernierTrajet(HttpServletRequest request) {
        List<TrajetDTO> listeDernierTrajet = ejb.dernierAjout();
        request.setAttribute("listeDernierTrajet",listeDernierTrajet);
    }

    private void getListeVilles(HttpServletRequest request){
        List<VilleDTO> listeVilles = ejb.getListeVilleDTO();
        request.setAttribute("listeVilles",listeVilles);
    }

    private void connexion(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
        request.getRequestDispatcher("ControleurGeneral").forward(request,response);
    }

    private void inscription(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
        String login,mdp,nom,mdp_confirmer;
        login = request.getParameter("login");
        nom = request.getParameter("nom");
        mdp = request.getParameter("mdp");
        mdp_confirmer = request.getParameter("mdp_confirmer");
        if((mdp.equals(mdp_confirmer))) {
            try {
                boolean succesInscription=ejb.inscription(login, nom, mdp);
                if(succesInscription) {
                    request.getSession().setAttribute("utilisateur", login);
                    request.getRequestDispatcher("/WEB-INF/homePage/homePage.jsp")
                            .forward(request, response);
                }else{
                    request.setAttribute("messageErreur","L'inscription n'a pas réussi");
                    request.getRequestDispatcher("/WEB-INF/inscription.jsp")
                            .forward(request, response);
                }
            }catch(LoginExistantException e){
                request.setAttribute("messageErreur","Le login est déjà pris");
                request.getRequestDispatcher("/WEB-INF/inscription.jsp")
                        .forward(request, response);
            }
        }else{

            request.setAttribute("messageErreur","Les mots de passe sont différents");
            request.getRequestDispatcher("/WEB-INF/inscription.jsp")
                    .forward(request, response);
        }
    }
}
