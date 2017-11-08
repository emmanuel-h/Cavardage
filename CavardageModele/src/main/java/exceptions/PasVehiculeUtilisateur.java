package exceptions;

public class PasVehiculeUtilisateur extends Exception {
    public PasVehiculeUtilisateur(String pas_votre_vehicule) {
        super(pas_votre_vehicule);
    }
}
