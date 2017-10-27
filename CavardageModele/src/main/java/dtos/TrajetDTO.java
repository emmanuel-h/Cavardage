package dtos;

import entities.Trajet;

public class TrajetDTO {

    private String villeDepart;
    private String departementDepart;
    private String villeArrive;
    private String departementArrivee;
    private String date;
    private String heure;
    private String vehicule;

    public TrajetDTO() {
    }

    public TrajetDTO(Trajet t) {
        String[] tab= t.getVilleDepart().getNomVille().split("_");
        villeDepart=tab[0];
        departementDepart=tab[1];
        tab=t.getVilleArrivee().getNomVille().split("_");
        villeArrive=tab[0];
        departementArrivee=tab[1];
        date=t.getDate();
        heure=t.getHeure();
        vehicule=t.getVehiculeTrajet().getModele();
    }

    public String getDepartementDepart() {
        return departementDepart;
    }

    public void setDepartementDepart(String departementDepart) {
        this.departementDepart = departementDepart;
    }

    public String getDepartementArrivee() {
        return departementArrivee;
    }

    public void setDepartementArrivee(String departementArrivee) {
        this.departementArrivee = departementArrivee;
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
