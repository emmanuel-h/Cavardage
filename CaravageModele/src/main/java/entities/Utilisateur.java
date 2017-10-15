package entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Utilisateur {

    @Id
    private String login;
    private String nom;
    private String motDePasse;

    public Utilisateur() {
    }

    public Utilisateur(String login, String nom, String motDePasse) {
        this.login = login;
        this.nom = nom;
        this.motDePasse = motDePasse;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
}
