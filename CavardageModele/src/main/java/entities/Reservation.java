package entities;

import javax.persistence.*;

@SuppressWarnings({"unused", "RedundantIfStatement"})

@Entity
public class Reservation {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idReservation;
    // accepte, refuse, en attente
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
        return idReservation;
    }

    public void setIdReservation(int idReservation) {
        this.idReservation = idReservation;
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        if(((Reservation)obj).getIdReservation() == (this.idReservation)){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "idReservation=" + idReservation +
                ", statut='" + statut + '\'' +
                ", nbPlace=" + nbPlace +
                ", utilisateurReservation=" + utilisateurReservation +
                '}';
    }
}
