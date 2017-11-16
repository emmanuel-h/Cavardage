package servlets;

import dtos.TrajetDTO;
import dtos.VilleDTO;
import ejbs.MaFacadeAnonyme;
import exceptions.DateAnterieureException;
import exceptions.LoginExistantException;
import exceptions.VilleNonTrouvee;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * Cette classe permet de gérer tous les comportements qu'un utilisateur peut avoir sans être authentifié
 */
@DeclareRoles({"admin","utilisateur"})
@WebServlet("ControleurAnonyme")
public class ControleurAnonyme extends HttpServlet {

    /**
     * L'ejb métier correspondant
     */
    @EJB
    private MaFacadeAnonyme ejb;

    /**
     * Si on a une méthode en get, on la traite de la même manière qu'en post
     * @param request la requête
     * @param response la réponse
     * @throws ServletException servlet exception
     * @throws IOException entrée/sortie exception
     */
    @PermitAll
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * Cette méthode va nous permettre de rediriger l'utilisateur vers la bonne page, ou d'exécuter la bonne action
     * @param request la requête
     * @param response la réponse
     * @throws ServletException servlet exception
     * @throws IOException entrée/sortie exception
     */
    @PermitAll
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String test = request.getParameter("afaire");
        if(request.isUserInRole("utilisateur") || request.isUserInRole("admin")){
            request.getRequestDispatcher("ControleurGeneral").forward(request,response);
        }
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
                default:
                    getListeVilles(request);
                    getListeDernierTrajet(request);
                    request.getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
                    break;
            }
        }
    }

    /**
     * Permet de lister tous les résultats de la recherche faite par l'utilisateur
     * @param request la requête
     * @param response la réponse
     * @throws ServletException servlet exception
     * @throws IOException entrée/sortie exception
     */
    @PermitAll
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
        } catch (DateAnterieureException | VilleNonTrouvee e) {
            request.setAttribute("messageErreur", e.getMessage());
            retournerAccueil(request, response);
        } catch (ParseException e) {
            request.setAttribute("messageErreur", "Merci de rentrer une date valide (jj/mm/aaaa)");
            retournerAccueil(request, response);
        }
    }

    /**
     * Permet de retourner à l'accueil
     * @param request la requête
     * @param response la réponse
     * @throws ServletException servlet exception
     * @throws IOException entrée/sortie exception
     */
    @PermitAll
    private void retournerAccueil(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getListeVilles(request);
        getListeDernierTrajet(request);
        request.getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
    }

    /**
     * Retourne la liste des dix derniers trajets enregsitrés sur le site
     * @param request la requête
     */
    @PermitAll
    private void getListeDernierTrajet(HttpServletRequest request) {
        List<TrajetDTO> listeDernierTrajet = ejb.dernierAjout();
        request.setAttribute("listeDernierTrajet",listeDernierTrajet);
    }

    /**
     * Retourne la liste des villes admises sur le site
     * @param request la requête
     */
    @PermitAll
    private void getListeVilles(HttpServletRequest request){
        List<VilleDTO> listeVilles = ejb.getListeVilleDTO();
        request.setAttribute("listeVilles",listeVilles);
    }

    /**
     * Lance la connexion puis la redirection vers la page correspondant au rôle de l'utilisateur
     * @param request la requête
     * @param response la réponse
     * @throws ServletException servlet exception
     * @throws IOException entrée/sortie exception
     */
    @PermitAll
    private void connexion(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
        request.getRequestDispatcher("ControleurGeneral").forward(request,response);
    }

    /**
     * Permet d'inscrire un nouvel utilisateur
     * @param request la requête
     * @param response la réponse
     * @throws ServletException servlet exception
     * @throws IOException entrée/sortie exception
     */
    @PermitAll
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
                    request.setAttribute("message", "Compte créé avec succès");
                    retournerAccueil(request,response);
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
