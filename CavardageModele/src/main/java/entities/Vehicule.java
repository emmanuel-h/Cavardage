package entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Vehicule {

    @Id @GeneratedValue
    private int idVehicule;
    private String modele;
    private int nombrePlaces;
    private String nom;

    @ManyToOne
    private Gabarit gabarit;

    @OneToMany(mappedBy = "vehiculeTrajet")
    private List<Trajet> listeTrajet;

    public Vehicule() {
    }

    public Vehicule(String modele, int nombrePlaces) {
        this.modele = modele;
        this.nombrePlaces = nombrePlaces;
    }

    public int getIdVehicule() {
        return idVehicule;
    }

    public void setIdVehicule(int idVehicule) {
        this.idVehicule = idVehicule;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public int getNombrePlaces() {
        return nombrePlaces;
    }

    public void setNombrePlaces(int nombrePlaces) {
        this.nombrePlaces = nombrePlaces;
    }

    public Gabarit getGabarit() {
        return gabarit;
    }

    public void setGabarit(Gabarit gabarit) {
        this.gabarit = gabarit;
    }

    public List<Trajet> getListeTrajet() {
        return listeTrajet;
    }

    public void setListeTrajet(List<Trajet> listeTrajet) {
        this.listeTrajet = listeTrajet;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public boolean equals(Object o){
        if(o instanceof Vehicule){
            if(((Vehicule) o).getIdVehicule() == idVehicule){
                return true;
            }
        }
        return false;
    }
}
