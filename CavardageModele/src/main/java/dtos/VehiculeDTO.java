package dtos;

public class VehiculeDTO {

    private int id;
    private String modele;
    private String nomVehicule;
    private String nomGabarit;
    private int nbPlaces;

    public VehiculeDTO(){

    }

    public VehiculeDTO(int id, String modele, String nomVehicule, String nomGabarit, int nbPlaces){
        this.id = id;
        this.modele = modele;
        this.nomVehicule = nomVehicule;
        this.nomGabarit = nomGabarit;
        this.nbPlaces = nbPlaces;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getNomVehicule() {
        return nomVehicule;
    }

    public void setNomVehicule(String nomVehicule) {
        this.nomVehicule = nomVehicule;
    }

    public String getNomGabarit() {
        return nomGabarit;
    }

    public void setNomGabarit(String nomGabarit) {
        this.nomGabarit = nomGabarit;
    }

    public int getNbPlaces() {
        return nbPlaces;
    }

    public void setNbPlaces(int nbPlaces) {
        this.nbPlaces = nbPlaces;
    }
}
