package dtos;

import entities.Trajet;

public class TrajetDTO {

    private String villeDepart;
    private String villeArrive;
    private String date;
    private String heure;
    private String vehicule;

    public TrajetDTO(Trajet t) {
        villeDepart=t.getVilleDepart().getNomVille();
        villeArrive=t.getVilleArrivee().getNomVille();
        date=t.getDate();
        heure=t.getHeure();
        vehicule=t.getVehiculeTrajet().getModele();
    }


}
