package entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Etape {

    @Id @GeneratedValue
    private int idEtape;
    private String nomVille;

    @ManyToOne
    private Ville villeEtape;

    @ManyToMany
    private List<Trajet> listeTrajet;

    public Etape() {
    }

    public Etape(String nomVille) {
        this.nomVille = nomVille;
    }

    public int getIdEtape() {
        return idEtape;
    }

    public void setIdEtape(int idEtape) {
        this.idEtape = idEtape;
    }

    public String getNomVille() {
        return nomVille;
    }

    public void setNomVille(String nomVille) {
        this.nomVille = nomVille;
    }

    public Ville getVilleEtape() {
        return villeEtape;
    }

    public void setVilleEtape(Ville villeEtape) {
        this.villeEtape = villeEtape;
    }

    public List<Trajet> getListeTrajet() {
        return listeTrajet;
    }

    public void setListeTrajet(List<Trajet> listeTrajet) {
        this.listeTrajet = listeTrajet;
    }
}
