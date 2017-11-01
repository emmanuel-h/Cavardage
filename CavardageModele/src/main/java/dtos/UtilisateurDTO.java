package dtos;

import entities.Utilisateur;

public class UtilisateurDTO {

    private String login;
    private String nom;
    private String role;


    public UtilisateurDTO(Utilisateur utilisateur) {
        login=utilisateur.getLogin();
        nom = utilisateur.getNom();
        role=utilisateur.getRoleUtilisateur().getMessage();
    }

    @Override
    public String toString() {
        return "UtilisateurDTO{" +
                "login='" + login + '\'' +
                ", nom='" + nom + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        if(((UtilisateurDTO)obj).getLogin().equals(this.login)){
            return true;
        } else {
            return false;
        }
    }

}
