package dtos;

public class PrixMoyenDTO {

    private String ville1;
    private String ville2;
    private float prix;

    public PrixMoyenDTO() {
    }

    public PrixMoyenDTO(String ville1, String ville2, float prix) {
        this.ville1 = ville1;
        this.ville2 = ville2;
        this.prix = prix;
    }

    public String getVille1() {
        return ville1;
    }

    public void setVille1(String ville1) {
        this.ville1 = ville1;
    }

    public String getVille2() {
        return ville2;
    }

    public void setVille2(String ville2) {
        this.ville2 = ville2;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }
}
