package dtos;

import entities.Trajet;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class TrajetDTO implements Comparable{

    private String villeDepart;
    private String departementDepart;
    private String villeArrive;
    private String departementArrivee;
    private String date;
    private String heure;
    private String vehicule;

    public TrajetDTO() {
    }

    public TrajetDTO(Trajet t) {
        String[] tab= t.getVilleDepart().getNomVille().split("_");
        villeDepart=tab[0];
        departementDepart=tab[1];
        tab=t.getVilleArrivee().getNomVille().split("_");
        villeArrive=tab[0];
        departementArrivee=tab[1];
        date=t.getDate();
        heure=t.getHeure();
        vehicule=t.getVehiculeTrajet().getModele();
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

    public String getVilleArrive() {
        return villeArrive;
    }

    public void setVilleArrive(String villeArrive) {
        this.villeArrive = villeArrive;
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
}
