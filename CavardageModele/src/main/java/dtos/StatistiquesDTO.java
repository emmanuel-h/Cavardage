package dtos;

public class StatistiquesDTO {

    private int nbUtilisateur;
    private int nbPassagers;
    private int nbConducteurs;
    private int nbTrajetsReserves;
    private int totalPrixTrajets;
    private int nbTrajetsFinis;

    public StatistiquesDTO() {
    }

    public StatistiquesDTO(int nbUtilisateur, int nbPassagers, int nbConducteurs, int nbTrajetsReserves, int totalPrixTrajets, int nbTrajetsFinis) {
        this.nbUtilisateur = nbUtilisateur;
        this.nbPassagers = nbPassagers;
        this.nbConducteurs = nbConducteurs;
        this.nbTrajetsReserves = nbTrajetsReserves;
        this.totalPrixTrajets = totalPrixTrajets;
        this.nbTrajetsFinis = nbTrajetsFinis;
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
}
