package dtos;

public class StatistiquesDTO {

    private int nbUtilisateur;
    private int nbTrajetsReserves;
    private int totalPrixTrajets;
    private int nbTrajetsFinis;

    public StatistiquesDTO() {
    }

    public StatistiquesDTO(int nbUtilisateur, int nbTrajetsReserves, int totalPrixTrajets, int nbTrajetsFinis) {
        this.nbUtilisateur = nbUtilisateur;
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
