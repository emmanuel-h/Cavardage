package servlets;

import dtos.TrajetDTO;
import dtos.UtilisateurDTO;
import dtos.VilleDTO;
import ejbs.MaFacadeAnonyme;
import entities.Ville;
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
                case "afficherRechercheTrajet":
                    getResultatRecherche(request,response);
                    getListeDernierTrajet(request);
                    break;
                default:
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
        List<TrajetDTO> listeTrajetRecherche = ejb.rechercheTrajet(nomVilleDepart, departementVilleDepart, nomVilleArrivee, departementVilleArrivee, date, prix);
        request.setAttribute("listeTrajetRecherche", listeTrajetRecherche);
        getListeDernierTrajet(request);
        getListeVilles(request);
        request.setAttribute("villeDepart",villeDepart);
        request.setAttribute("villeArrivee",villeArrive);
        request.setAttribute("date",date);
        if(!prix.equals("")) {
            request.setAttribute("prix", prix);
        }
        System.out.println("LA");
        request.setAttribute("resultatsRecherche","afficher");
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


    public void connexion(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
        String login,mdp;
        login = request.getParameter("login");
        mdp = request.getParameter("mdp");
        try {
            UtilisateurDTO utilisateurDTO = ejb.connexion(login, mdp);
            request.getSession().setAttribute("utilisateur",login);
            if(utilisateurDTO.getRole().equals("utilisateur")) {
                request.getRequestDispatcher("/WEB-INF/homePage/homePage.jsp").forward(request, response);
            }else {
                request.getRequestDispatcher("/WEB-INF/accueilAdmin.jsp").forward(request, response);
            }
        }catch (UtilisateurNonInscritException exception){
            getListeDernierTrajet(request);
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
        if((mdp.equals(mdp_confirmer))) {
            try {
                boolean succesInscription=ejb.inscription(login, nom, mdp);
                if(succesInscription) {
                    request.getSession().setAttribute("utilisateur", login);
                    request.getRequestDispatcher("/WEB-INF/homePage/homePage.jsp")
                            .forward(request, response);
                }else{
                    request.setAttribute("messageErreur","L'inscription n'a pas réussi.");
                    request.getRequestDispatcher("/WEB-INF/inscription.jsp")
                            .forward(request, response);
                }
            }catch(LoginExistantException e){
                request.setAttribute("messageErreur","Le login est déjà pris.");
                request.getRequestDispatcher("/WEB-INF/inscription.jsp")
                        .forward(request, response);
            }
        }else{

            request.setAttribute("messageErreur","Les mots de passe est différent.");
            request.getRequestDispatcher("/WEB-INF/inscription.jsp")
                    .forward(request, response);
        }
    }


}
