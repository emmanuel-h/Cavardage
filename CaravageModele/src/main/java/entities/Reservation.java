package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Reservation {

    @Id @GeneratedValue
    private int IdReservation;
    private String statut;

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
}
