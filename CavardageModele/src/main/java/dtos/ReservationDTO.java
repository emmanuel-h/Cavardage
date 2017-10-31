package dtos;

import entities.Reservation;

public class ReservationDTO {

    private int idReservation;
    private String statut;
    private int nbPlaces;
    private String nomVilleArrivee;
    private String departementArrivee;
    private String loginUtilisateur;
    private String nomUtilisateur;

    public ReservationDTO() {
    }

    public ReservationDTO(Reservation reservation){
        this.idReservation = reservation.getIdReservation();
        this.statut = reservation.getStatut();
        this.nbPlaces = reservation.getNbPlace();
        if(null == reservation.getDescendA()){
            String[] tab = reservation.getTrajetReservation().getVilleArrivee().getNomVille().split("_");
            this.nomVilleArrivee = tab[0];
            this.departementArrivee = tab[1];
        } else {
            String[] tab = reservation.getDescendA().getVilleEtape().getNomVille().split("_");
            this.nomVilleArrivee = tab[0];
            this.departementArrivee = tab[1];
        }
        this.loginUtilisateur = reservation.getUtilisateurReservation().getLogin();
        this.nomUtilisateur =reservation.getUtilisateurReservation().getNom();
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
}
