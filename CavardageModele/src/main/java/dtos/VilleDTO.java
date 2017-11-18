package dtos;

public class VilleDTO {

    private String idVille;
    private String nomVille;
    private String departement;

    public VilleDTO(){

    }

    public VilleDTO(String idVille){
        String[] id = idVille.split("_");
        this.idVille = idVille;
        this.nomVille = id[0];
        this.departement = id[1];
    }

    public VilleDTO(String nom, String departement){
        this.nomVille = nom;
        this.departement = departement;
    }


    public String getIdVille() {
        return idVille;
    }

    public void setIdVille(String idVille) {
        String[] id = idVille.split("_");
        this.idVille = idVille;
        this.nomVille = id[0];
        this.departement = id[1];
    }

    public String getNomVille() {
        return nomVille;
    }

    public void setNomVille(String nomVille) {
        this.nomVille = nomVille;
    }

    public String getDepartement() {
        return departement;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }
}
