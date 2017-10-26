package dtos;

import entities.Trajet;

public class TrajetDTO {

    private String villeDepart;
    private String villeArrive;
    private String date;
    private String heure;
    private String vehicule;

    public TrajetDTO() {
    }

    public TrajetDTO(Trajet t) {
        villeDepart=t.getVilleDepart().getNomVille();
        villeArrive=t.getVilleArrivee().getNomVille();
        date=t.getDate();
        heure=t.getHeure();
        vehicule=t.getVehiculeTrajet().getModele();
    }

    public String getVilleDepart() {
        return villeDepart;
    }

    public void setVilleDepart(String villeDepart) {
        this.villeDepart = villeDepart;
    }

    public String getVilleArrive() {
        return villeArrive;
    }

    public void setVilleArrive(String villeArrive) {
        this.villeArrive = villeArrive;
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

    public String getVehicule() {
        return vehicule;
    }

    public void setVehicule(String vehicule) {
        this.vehicule = vehicule;
    }
}
