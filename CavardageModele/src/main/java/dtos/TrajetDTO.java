package dtos;

import entities.Etape;
import entities.Reservation;
import entities.Trajet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class TrajetDTO implements Comparable{

    private int id;
    private String loginConducteur;
    private String villeDepart;
    private String departementDepart;
    private String villeArrivee;
    private String departementArrivee;
    private String date;
    private String heure;
    private String vehicule;
    private String nomVehicule;
    private int prix;
    private int nbPlaces;
    private List<EtapeDTO> etapes;

    public TrajetDTO() {
    }

    public TrajetDTO(Trajet t) {
        String[] tab = t.getVilleDepart().getNomVille().split("_");
        this.villeDepart = tab[0];
        this.departementDepart = tab[1];
        tab = t.getVilleArrivee().getNomVille().split("_");
        this.villeArrivee = tab[0];
        this.departementArrivee = tab[1];
        this.date = t.getDate();
        this.heure = t.getHeure();
        this.vehicule = t.getVehiculeTrajet().getModele();
        this.loginConducteur = t.getVehiculeTrajet().getUtilisateur().getLogin();
        this.id = t.getIdTrajet();
        this.prix = t.getPrix();
        this.nomVehicule = t.getVehiculeTrajet().getNom();
        int nbPlacesTemp = t.getVehiculeTrajet().getNombrePlaces();
        for (Reservation reservation : t.getListeReservation()){
            if(reservation.getStatut().equals("accepte")){
                nbPlacesTemp -= reservation.getNbPlace();
            }
        }
        this.nbPlaces = nbPlacesTemp;
        this.etapes = new ArrayList<>();
        for(Etape e:t.getListeEtape()){
            this.etapes.add(new EtapeDTO(e));
        }
    }



    public String getLoginConducteur() {
        return loginConducteur;
    }

    public void setLoginConducteur(String loginConducteur) {
        this.loginConducteur = loginConducteur;
    }

    public String getDepartementDepart() {
        return departementDepart;
    }

    public void setDepartementDepart(String departementDepart) {
        this.departementDepart = departementDepart;
    }

    public String getDepartementArrivee() {
        return departementArrivee;
    }

    public void setDepartementArrivee(String departementArrivee) {
        this.departementArrivee = departementArrivee;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getVehicule() {
        return vehicule;
    }

    public void setVehicule(String vehicule) {
        this.vehicule = vehicule;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getNbPlaces() {
        return nbPlaces;
    }

    public void setNbPlaces(int nbPlaces) {
        this.nbPlaces = nbPlaces;
    }

    public List<EtapeDTO> getEtapes() {
        return etapes;
    }

    public void setEtapes(List<EtapeDTO> etapes) {
        this.etapes = etapes;
    }

    public String getNomVehicule() {
        return nomVehicule;
    }

    public void setNomVehicule(String nomVehicule) {
        this.nomVehicule = nomVehicule;
    }

    @Override
    public int compareTo(Object o) {

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        int result=0;
        try {
            Date date1 = format.parse(this.getDate()+" "+this.getHeure());
            Date date2 = format.parse(((TrajetDTO)o).getDate()+" "+((TrajetDTO)o).getHeure());
            if (date1.compareTo(date2) > 0) {
                result=1;
            } else if (date1.compareTo(date2) < 0) {
                result=-1;
            } else if (date1.compareTo(date2) == 0) {
                result=0;
            }
        }catch(Exception e){
            e.printStackTrace();
            System.out.println(e.toString());
        }
        return result;
    }

    @Override
    public String toString() {
        return "TrajetDTO{" +
                "id=" + id +
                ", loginConducteur='" + loginConducteur + '\'' +
                ", villeDepart='" + villeDepart + '\'' +
                ", departementDepart='" + departementDepart + '\'' +
                ", villeArrivee='" + villeArrivee + '\'' +
                ", departementArrivee='" + departementArrivee + '\'' +
                ", date='" + date + '\'' +
                ", heure='" + heure + '\'' +
                ", vehicule='" + vehicule + '\'' +
                ", nomVehicule='" + nomVehicule + '\'' +
                ", prix=" + prix +
                ", nbPlaces=" + nbPlaces +
                ", etapes=" + etapes +
                '}';
    }
}
