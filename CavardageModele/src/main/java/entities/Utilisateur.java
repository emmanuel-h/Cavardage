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
    private Role roleUtilisateur;

    @OneToMany
    private List<Vehicule> listeVehicule;

    @OneToMany(mappedBy = "utilisateurReservation")
    private List<Reservation> listeReservation;

    @OneToMany(mappedBy = "donneNote")
    private List<Appreciation> note;

    @OneToMany(mappedBy = "estNote")
    private List<Appreciation> estNote;

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

    public Role getRoleUtilisateur() {
        return roleUtilisateur;
    }

    public void setRoleUtilisateur(Role roleUtilisateur) {
        this.roleUtilisateur = roleUtilisateur;
    }

    public List<Vehicule> getListeVehicule() {
        return listeVehicule;
    }

    public void setListeVehicule(List<Vehicule> listeVehicule) {
        this.listeVehicule = listeVehicule;
    }

    public List<Reservation> getListeReservation() {
        return listeReservation;
    }

    public void setListeReservation(List<Reservation> listeReservation) {
        this.listeReservation = listeReservation;
    }

    public List<Appreciation> getNote() {
        return note;
    }

    public void setNote(List<Appreciation> note) {
        this.note = note;
    }

    public List<Appreciation> getEstNote() {
        return estNote;
    }

    public void setEstNote(List<Appreciation> estNote) {
        this.estNote = estNote;
    }

    public void ajouterVehicule(Vehicule vehicule){
        this.listeVehicule.add(vehicule);
    }
}
