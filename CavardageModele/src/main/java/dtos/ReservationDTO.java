package dtos;

import entities.Reservation;

public class ReservationDTO {

    private int idReservation;
    private String statut;
    private int nbPlaces;
    private String nomVilleDepart;
    private String departementDepart;
    private String nomVilleArrivee;
    private String departementArrivee;
    private String loginUtilisateur;
    private String nomUtilisateur;
    private int idTrajet;
    private String date;

    public ReservationDTO() {
    }

    public ReservationDTO(Reservation reservation){
        this.idReservation = reservation.getIdReservation();
        this.statut = reservation.getStatut();
        this.nbPlaces = reservation.getNbPlace();
        String[] tab = reservation.getTrajetReservation().getVilleDepart().getNomVille().split("_");
        this.nomVilleDepart = tab[0];
        this.departementDepart = tab[1];
        this.idTrajet = reservation.getTrajetReservation().getIdTrajet();
        if(null == reservation.getDescendA()){
            tab = reservation.getTrajetReservation().getVilleArrivee().getNomVille().split("_");
            this.nomVilleArrivee = tab[0];
            this.departementArrivee = tab[1];
        } else {
            tab = reservation.getDescendA().getVilleEtape().getNomVille().split("_");
            this.nomVilleArrivee = tab[0];
            this.departementArrivee = tab[1];
        }
        this.loginUtilisateur = reservation.getUtilisateurReservation().getLogin();
        this.nomUtilisateur =reservation.getUtilisateurReservation().getNom();
        this.date = reservation.getTrajetReservation().getDate()+ " "+reservation.getTrajetReservation().getHeure();
    }

    public int getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(int idReservation) {
        this.idReservation = idReservation;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public int getNbPlaces() {
        return nbPlaces;
    }

    public void setNbPlaces(int nbPlaces) {
        this.nbPlaces = nbPlaces;
    }

    public String getNomVilleDepart() {
        return nomVilleDepart;
    }

    public void setNomVilleDepart(String nomVilleDepart) {
        this.nomVilleDepart = nomVilleDepart;
    }

    public String getDepartementDepart() {
        return departementDepart;
    }

    public void setDepartementDepart(String departementDepart) {
        this.departementDepart = departementDepart;
    }

    public String getNomVilleArrivee() {
        return nomVilleArrivee;
    }

    public void setNomVilleArrivee(String nomVilleArrivee) {
        this.nomVilleArrivee = nomVilleArrivee;
    }

    public String getLoginUtilisateur() {
        return loginUtilisateur;
    }

    public void setLoginUtilisateur(String loginUtilisateur) {
        this.loginUtilisateur = loginUtilisateur;
    }

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }

    public String getDepartementArrivee() {
        return departementArrivee;
    }

    public void setDepartementArrivee(String departementArrivee) {
        this.departementArrivee = departementArrivee;
    }

    public int getIdTrajet() {
        return idTrajet;
    }

    public void setIdTrajet(int idTrajet) {
        this.idTrajet = idTrajet;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
