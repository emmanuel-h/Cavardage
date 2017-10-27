package entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Etape {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEtape;
    private int prix;

    @ManyToOne
    private Ville villeEtape;

    @ManyToOne
    private Trajet trajet;

    public Etape() {
    }

    public int getIdEtape() {
        return idEtape;
    }

    public void setIdEtape(int idEtape) {
        this.idEtape = idEtape;
    }

    public Ville getVilleEtape() {
        return villeEtape;
    }

    public void setVilleEtape(Ville villeEtape) {
        this.villeEtape = villeEtape;
    }

    public Trajet getTrajet() {
        return trajet;
    }

    public void setTrajet(Trajet trajet) {
        this.trajet = trajet;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }
}
