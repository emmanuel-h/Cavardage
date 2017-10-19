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
}
