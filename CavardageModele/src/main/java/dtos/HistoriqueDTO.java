package dtos;

public class HistoriqueDTO {

    private int idTrajet;
    private String nomVehicule;
    private int nbReservation;
    private String role;
    private String villeDepart;
    private String villeArrivee;
    private String dateDepart;

    public HistoriqueDTO(){

    }

    public HistoriqueDTO(int idTrajet, String nomVehicule, int nbReservation, String role, String villeDepart, String villeArrivee, String dateDepart){
        this.idTrajet = idTrajet;
        this.nomVehicule = nomVehicule;
        this.nbReservation = nbReservation;
        this.role = role;
        this.villeArrivee = villeArrivee;
        this.villeDepart = villeDepart;
        this.dateDepart = dateDepart;
    }

    public int getIdTrajet() {
        return idTrajet;
    }

    public void setIdTrajet(int idTrajet) {
        this.idTrajet = idTrajet;
    }

    public String getNomVehicule() {
        return nomVehicule;
    }

    public void setNomVehicule(String nomVehicule) {
        this.nomVehicule = nomVehicule;
    }

    public int getNbReservation() {
        return nbReservation;
    }

    public void setNbReservation(int nbReservation) {
        this.nbReservation = nbReservation;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getVilleDepart() {
        return villeDepart;
    }

    public void setVilleDepart(String villeDepart) {
        this.villeDepart = villeDepart;
    }

    public String getVilleArrivee() {
        return villeArrivee;
    }

    public void setVilleArrivee(String villeArrivee) {
        this.villeArrivee = villeArrivee;
    }

    public String getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(String dateDepart) {
        this.dateDepart = dateDepart;
    }
}
