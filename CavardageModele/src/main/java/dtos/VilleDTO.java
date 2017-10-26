package dtos;

import entities.Ville;

public class VilleDTO {

    String nom;
    String departement;

    public VilleDTO(Ville v){
        this.nom = v.getNomVille();
        this.departement = String.valueOf(v.getDepartement());
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDepartement() {
        return departement;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }
}
