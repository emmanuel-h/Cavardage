package dtos;

public class VilleDTO {

    private String idVille;
    private String nomVille;
    private int departement;

    public VilleDTO(){

    }

    public VilleDTO(String idVille){
        String[] id = idVille.split("_");
        this.idVille = idVille;
        this.nomVille = id[0];
        this.departement = Integer.parseInt(id[1]);
    }

    public VilleDTO(String nom, String departement){
        this.nomVille = nom;
        this.departement = Integer.parseInt(departement);
    }

    public VilleDTO(String nom, int departement){
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
        this.departement = Integer.parseInt(id[1]);
    }

    public String getNomVille() {
        return nomVille;
    }

    public void setNomVille(String nomVille) {
        this.nomVille = nomVille;
    }

    public int getDepartement() {
        return departement;
    }

    public void setDepartement(int departement) {
        this.departement = departement;
    }
}
