package entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Utilisateur {

    @Id
    private String login;
    private String nom;
    private String motDePasse;

    @ManyToOne
    //@JoinColumn(name = "idRole")
    private Role roleUtilisateur;

    @OneToMany
    private List<Vehicule> listeVehicule;

    @OneToMany(mappedBy = "utilisateurReservation")
    private List<Reservation> listeReservation;

    @OneToMany(mappedBy = "donneNote")
    private Appreciation note;

    @OneToMany(mappedBy = "estNote")
    private Appreciation estNote;

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
