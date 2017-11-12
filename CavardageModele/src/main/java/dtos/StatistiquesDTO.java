package dtos;

public class StatistiquesDTO {

    private int nbUtilisateur;
    private int nbPassagers;
    private int nbConducteurs;
    private int nbTrajetsReserves;
    private int totalPrixTrajets;
    private int nbTrajetsFinis;
    private int nbVilles;
    private String topVilleDepart;
    private String topVilleArrivee;
    private long duration;

    public StatistiquesDTO() {
    }

    public StatistiquesDTO(int nbUtilisateur, int nbPassagers, int nbConducteurs, int nbTrajetsReserves,
                           int totalPrixTrajets, int nbTrajetsFinis, int nbVilles, String topVilleDepart,
                           String topVilleArrivee, long duration) {
        this.nbUtilisateur = nbUtilisateur;
        this.nbPassagers = nbPassagers;
        this.nbConducteurs = nbConducteurs;
        this.nbTrajetsReserves = nbTrajetsReserves;
        this.totalPrixTrajets = totalPrixTrajets;
        this.nbTrajetsFinis = nbTrajetsFinis;
        this.nbVilles = nbVilles;
        this.topVilleDepart = topVilleDepart;
        this.topVilleArrivee = topVilleArrivee;
        this.duration = duration;
    }

    public int getNbUtilisateur() {
        return nbUtilisateur;
    }

    public void setNbUtilisateur(int nbUtilisateur) {
        this.nbUtilisateur = nbUtilisateur;
    }

    public int getNbPassagers() {
        return nbPassagers;
    }

    public void setNbPassagers(int nbPassagers) {
        this.nbPassagers = nbPassagers;
    }

    public int getNbConducteurs() {
        return nbConducteurs;
    }

    public void setNbConducteurs(int nbConducteurs) {
        this.nbConducteurs = nbConducteurs;
    }

    public int getNbTrajetsReserves() {
        return nbTrajetsReserves;
    }

    public void setNbTrajetsReserves(int nbTrajetsReserves) {
        this.nbTrajetsReserves = nbTrajetsReserves;
    }

    public int getTotalPrixTrajets() {
        return totalPrixTrajets;
    }

    public void setTotalPrixTrajets(int totalPrixTrajets) {
        this.totalPrixTrajets = totalPrixTrajets;
    }

    public int getNbTrajetsFinis() {
        return nbTrajetsFinis;
    }

    public void setNbTrajetsFinis(int nbTrajetsFinis) {
        this.nbTrajetsFinis = nbTrajetsFinis;
    }

    public int getNbVilles() {
        return nbVilles;
    }

    public void setNbVilles(int nbVilles) {
        this.nbVilles = nbVilles;
    }

    public String getTopVilleDepart() {
        return topVilleDepart;
    }

    public void setTopVilleDepart(String topVilleDepart) {
        this.topVilleDepart = topVilleDepart;
    }

    public String getTopVilleArrivee() {
        return topVilleArrivee;
    }

    public void setTopVilleArrivee(String topVilleArrivee) {
        this.topVilleArrivee = topVilleArrivee;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }
}
