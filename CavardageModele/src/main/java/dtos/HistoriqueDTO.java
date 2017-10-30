package dtos;

import java.util.StringTokenizer;

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
        StringTokenizer st = new StringTokenizer(villeArrivee, "_");
        this.villeArrivee = st.nextToken() + "(" + st.nextToken() + ")";
        st = new StringTokenizer(villeDepart, "_");
        this.villeDepart = st.nextToken() + "(" + st.nextToken() + ")";
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
        StringTokenizer st = new StringTokenizer(villeDepart, "_");
        this.villeDepart = st.nextToken() + "(" + st.nextToken() + ")";
    }

    public String getVilleArrivee() {
        return villeArrivee;
    }

    public void setVilleArrivee(String villeArrivee) {
        StringTokenizer st = new StringTokenizer(villeArrivee, "_");
        this.villeArrivee = st.nextToken() + "(" + st.nextToken() + ")";
    }

    public String getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(String dateDepart) {
        this.dateDepart = dateDepart;
    }
}
