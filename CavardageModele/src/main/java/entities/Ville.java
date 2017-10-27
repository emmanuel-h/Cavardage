package entities;

import javax.persistence.*;

/*@NamedQueries({
        @NamedQuery(name = "selectVille", query = "SELECT Ville AS v FROM Ville where v.nomVille=:nom"),
        @NamedQuery(name = "selectAllVilles", query = "SELECT Ville AS v FROM Ville")
})*/
@Entity
public class Ville {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idVille;
    private String nomVille;
    private int departement;

    public Ville() {
    }

    public Ville(String nomVille,int departement) {
        this.nomVille = nomVille;
        this.departement = departement;
    }

    public int getDepartement() {
        return departement;
    }

    public void setDepartement(int departement) {
        this.departement = departement;
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        if(((Ville)obj).getIdVille() == (this.idVille)){
            return true;
        } else {
            return false;
        }
    }
}
