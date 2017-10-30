package dtos;

import entities.Etape;

public class EtapeDTO {

    private int idEtape;
    private int prix;
    private String nomVilleArrivee;
    private String departementArrivee;

    public EtapeDTO() {
    }

    public EtapeDTO(Etape etape) {
        String[] tab = etape.getVilleEtape().getNomVille().split("_");
        this.nomVilleArrivee = tab[0];
        this.departementArrivee = tab[1];
        this.idEtape = etape.getIdEtape();
        this.prix = etape.getPrix();
    }

    public int getIdEtape() {
        return idEtape;
    }

    public void setIdEtape(int idEtape) {
        this.idEtape = idEtape;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getNomVilleArrivee() {
        return nomVilleArrivee;
    }

    public void setNomVilleArrivee(String nomVilleArrivee) {
        this.nomVilleArrivee = nomVilleArrivee;
    }

    public String getDepartementArrivee() {
        return departementArrivee;
    }

    public void setDepartementArrivee(String departementArrivee) {
        this.departementArrivee = departementArrivee;
    }

    @Override
    public String toString() {
        return "EtapeDTO{" +
                "idEtape=" + idEtape +
                ", prix=" + prix +
                ", nomVilleArrivee='" + nomVilleArrivee + '\'' +
                ", departementArrivee='" + departementArrivee + '\'' +
                '}';
    }
}
