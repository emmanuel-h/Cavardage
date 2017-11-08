package entities;

import javax.persistence.*;
import java.util.StringTokenizer;

@SuppressWarnings({"unused", "RedundantIfStatement"})

@Entity
public class Ville {

    @Id
    private String nomVille;

    public Ville() {
    }

    public Ville(String nomVille,int departement) {
        this.nomVille = nomVille + "_" + departement;
    }

    public Ville(String nom, String departement){
        this.nomVille = nom + "_" + departement;
    }

    public void setNomVille(String idVille) {
        this.nomVille = idVille;
    }

    public void setNomVille(String nom, String departement){
        this.nomVille = nom + "_" + departement;
    }

    public void setNomVille(String nom, int departement){
        this.nomVille = nom + "_" + departement;
    }

    public String getNomVille() {
        return nomVille;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        if(((Ville)obj).getNomVille().equals(this.nomVille)){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Ville{" +
                "nomVille='" + nomVille + '\'' +
                '}';
    }
}
