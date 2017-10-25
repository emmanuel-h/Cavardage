package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Reservation {

    @Id @GeneratedValue
    private int IdReservation;
    private String statut;
    private int nbPlace;

    @ManyToOne
    private Etape descendA;

    @ManyToOne
    private Trajet trajetReservation;

    @ManyToOne
    private Utilisateur utilisateurReservation;

    public Reservation() {
    }

    public Reservation(String statut) {
        this.statut = statut;
    }

    public int getIdReservation() {
        return IdReservation;
    }

    public void setIdReservation(int idReservation) {
        IdReservation = idReservation;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Etape getDescendA() {
        return descendA;
    }

    public void setDescendA(Etape descendA) {
        this.descendA = descendA;
    }

    public Trajet getTrajetReservation() {
        return trajetReservation;
    }

    public void setTrajetReservation(Trajet trajetReservation) {
        this.trajetReservation = trajetReservation;
    }

    public Utilisateur getUtilisateurReservation() {
        return utilisateurReservation;
    }

    public void setUtilisateurReservation(Utilisateur utilisateurReservation) {
        this.utilisateurReservation = utilisateurReservation;
    }

    public int getNbPlace() {
        return nbPlace;
    }

    public void setNbPlace(int nbPlace) {
        this.nbPlace = nbPlace;
    }
}
