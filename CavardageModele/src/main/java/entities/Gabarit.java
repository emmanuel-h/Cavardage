package entities;

import javax.persistence.*;

/*@NamedQueries({
        @NamedQuery(name = "selectGabarit", query = "SELECT Gabarit AS g FROM Gabarit where g.type=:gabarit"),
        @NamedQuery(name = "selectAllGabarits", query = "SELECT Gabarit AS g FROM Gabarit")
})*/
@Entity
public class Gabarit {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idGabarit;
    private String type;


    public Gabarit() {
    }

    public Gabarit(String type) {
        this.type = type;
    }

    public int getIdGabarit() {
        return idGabarit;
    }

    public void setIdGabarit(int idGabarit) {
        this.idGabarit = idGabarit;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        if(((Gabarit)obj).getIdGabarit() == (this.idGabarit)){
            return true;
        } else {
            return false;
        }
    }
}
