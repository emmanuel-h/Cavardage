package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Ville {

    @Id @GeneratedValue
    private int idVille;
    private String nomVille;

    public Ville() {
    }

    public Ville(String nomVille) {
        this.nomVille = nomVille;
    }

    public int getIdVille() {
        return idVille;
    }

    public void setIdVille(int idVille) {
        this.idVille = idVille;
    }

    public String getNomVille() {
        return nomVille;
    }

    public void setNomVille(String nomVille) {
        this.nomVille = nomVille;
    }
}
