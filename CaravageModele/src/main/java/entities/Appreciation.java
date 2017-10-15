package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Appreciation {

    @Id @GeneratedValue
    private int idAppreciation;
    private int note;
    private String commentaire;

    public Appreciation() {
    }

    public Appreciation(int note, String commentaire) {
        this.note = note;
        this.commentaire = commentaire;
    }

    public int getIdAppreciation() {
        return idAppreciation;
    }

    public void setIdAppreciation(int idAppreciation) {
        this.idAppreciation = idAppreciation;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }
}
