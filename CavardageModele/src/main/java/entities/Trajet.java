package entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Trajet {

    @Id @GeneratedValue
    private int idTrajet;
    private String date;
    private String heure;

    @ManyToOne
    private Ville villeDepart;
    @ManyToOne
    private Ville villeArrivee;

    @OneToMany(mappedBy = "trajetReservation")
    private List<Reservation> listeReservation;

    @ManyToOne
    private Vehicule vehiculeTrajet;

    @OneToMany(mappedBy = "trajet")
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

    public Ville getVilleDepart() {
        return villeDepart;
    }

    public void setVilleDepart(Ville villeDepart) {
        this.villeDepart = villeDepart;
    }

    public Ville getVilleArrivee() {
        return villeArrivee;
    }

    public void setVilleArrivee(Ville villeArrivee) {
        this.villeArrivee = villeArrivee;
    }

    public List<Reservation> getListeReservation() {
        return listeReservation;
    }

    public void setListeReservation(List<Reservation> listeReservation) {
        this.listeReservation = listeReservation;
    }

    public Vehicule getVehiculeTrajet() {
        return vehiculeTrajet;
    }

    public void setVehiculeTrajet(Vehicule vehiculeTrajet) {
        this.vehiculeTrajet = vehiculeTrajet;
    }

    public List<Etape> getListeEtape() {
        return listeEtape;
    }

    public void setListeEtape(List<Etape> listeEtape) {
        this.listeEtape = listeEtape;
    }
}
