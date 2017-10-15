package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Gabarit {

    @Id @GeneratedValue
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
}
