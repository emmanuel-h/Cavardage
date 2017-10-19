package entities;

import com.sun.org.glassfish.gmbal.ManagedObject;

import javax.persistence.*;
import java.util.List;

@Entity
public class Trajet {

    @Id @GeneratedValue
    private int idTrajet;
    private String date;
    private String heure;

    @ManyToOne
    //@JoinColumn(name = "idVille")
    private Ville villeDepart;
    @ManyToOne
    //@JoinColumn(name = "idVille")
    private Ville villeArrivee;

    @OneToMany(mappedBy = "trajetReservation")
    private List<Reservation> listeReservation;

    @ManyToOne
    private Vehicule vehiculeTrajet;

    @ManyToMany(mappedBy = "listeTrajet")
    private List<Etape> listeEtape;

    public Trajet() {
    }

    public Trajet(String date, String heure) {
        this.date = date;
        this.heure = heure;
    }

    public int getIdTrajet() {
        return idTrajet;
    }

    public void setIdTrajet(int idTrajet) {
        this.idTrajet = idTrajet;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }
}
