package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Appreciation {

    @Id @GeneratedValue
    private int idAppreciation;
    private int note;
    private String commentaire;

    @ManyToOne
    private Trajet noteTrajet;

    @ManyToOne
    private Utilisateur donneNote;

    @ManyToOne
    private Utilisateur estNote;

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

    public Trajet getNoteTrajet() {
        return noteTrajet;
    }

    public void setNoteTrajet(Trajet noteTrajet) {
        this.noteTrajet = noteTrajet;
    }

    public Utilisateur getDonneNote() {
        return donneNote;
    }

    public void setDonneNote(Utilisateur donneNote) {
        this.donneNote = donneNote;
    }

    public Utilisateur getEstNote() {
        return estNote;
    }

    public void setEstNote(Utilisateur estNote) {
        this.estNote = estNote;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        if(((Appreciation)obj).getEstNote().equals(this.estNote) && ((Appreciation)obj).getDonneNote().equals(this.donneNote) && ((Appreciation)obj).getNoteTrajet().equals(this.noteTrajet)){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Appreciation{" +
                "idAppreciation=" + idAppreciation +
                ", note=" + note +
                ", commentaire='" + commentaire + '\'' +
                ", noteTrajet=" + noteTrajet +
                ", donneNote=" + donneNote +
                ", estNote=" + estNote +
                '}';
    }
}
