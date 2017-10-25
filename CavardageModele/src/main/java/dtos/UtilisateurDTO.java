package dtos;

import entities.Utilisateur;

public class UtilisateurDTO {

    private String login;
    private String role;


    public UtilisateurDTO(Utilisateur utilisateur) {
        login=utilisateur.getLogin();
        role=utilisateur.getRoleUtilisateur().getMessage();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
