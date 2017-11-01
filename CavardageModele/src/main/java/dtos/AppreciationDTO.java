package dtos;

import entities.Appreciation;

public class AppreciationDTO {

    private int note;
    private String commentaire;
    private String emetteurAppreciation;

    public AppreciationDTO() {

    }

    public AppreciationDTO(Appreciation appreciation) {
        this.note = appreciation.getNote();
        this.commentaire = appreciation.getCommentaire();
        this.emetteurAppreciation = appreciation.getDonneNote().getNom();
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

    public String getEmetteurAppreciation() {
        return emetteurAppreciation;
    }

    public void setEmetteurAppreciation(String emetteurAppreciation) {
        this.emetteurAppreciation = emetteurAppreciation;
    }

}
