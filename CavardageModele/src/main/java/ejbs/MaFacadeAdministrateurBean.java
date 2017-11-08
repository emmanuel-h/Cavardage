package ejbs;

import dtos.StatistiquesDTO;
import dtos.VilleDTO;
import entities.*;
import exceptions.GabaritException;
import exceptions.VilleExistante;
import exceptions.VilleNonTrouvee;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.*;

@SuppressWarnings("unchecked")

@Stateless(name = "AdministrateurBean")
public class MaFacadeAdministrateurBean implements MaFacadeAdministrateur {

    @PersistenceContext(unitName = "monUnite")
    private EntityManager em;
    @EJB
    private RechercheBean recherche;
    @EJB
    private Automate automate;

    public MaFacadeAdministrateurBean(){
    }

    public boolean ajouterVille(String nomVille,String departement) throws VilleExistante {
        Ville v = em.find(Ville.class,nomVille + "_" + departement);
        if(null == v){
            v = new Ville(nomVille,Integer.parseInt(departement));
            em.persist(v);
            return true;
        }else{
            throw new VilleExistante("Cette ville existe déjà");
        }
    }

    public List<Ville> getListeVilles(){
        return recherche.getListeVilles();
    }

    public List<VilleDTO> getListeVilleDTO(){
        return recherche.getListeVillesDTO();
    }

    public List<String> getListeGabarits(){
        Query q = em.createQuery("SELECT g FROM Gabarit g");
        List<Gabarit> listeTemp = q.getResultList();
        if(!listeTemp.isEmpty()){
            List<String> listeGabarits = new ArrayList<>();
            for(Gabarit g : listeTemp){
                listeGabarits.add(g.getType());
            }
            return listeGabarits;
        }
        return new ArrayList<>();
    }

    public boolean supprimerVille(String nomVille, String departement) throws VilleNonTrouvee {
        String idVille = nomVille + "_" + departement;
        Ville ville = em.find(Ville.class, idVille);
        if(null == ville){
            throw new VilleNonTrouvee("La ville n'existe pas dans la base de données");
        }else{
            Query q = em.createQuery("SELECT t FROM Trajet t where t.villeDepart.nomVille=:villeSupp or t.villeArrivee.nomVille=:villeSupp");
            q.setParameter("villeSupp", idVille);
            List<Trajet> listeTrajets = q.getResultList();
            for(Trajet t : listeTrajets){
                // On récupère le véhicule du trajet, et on supprime le trajet de ce dernier
                Vehicule v = t.getVehiculeTrajet();
                automate.creerNotification(v.getUtilisateur().getLogin(), "Votre trajet au départ de " + t.getVilleDepart().getNomVille()
                        + " le " + t.getDate() + " et arrivant à " + t.getVilleArrivee().getNomVille() + " a été annulée par l'administrateur "
                        + "du site car cette ville n'est plus desservie actuellement.");
                v.getListeTrajet().remove(t);

                List<Reservation> listeRes = t.getListeReservation();
                for(Reservation r : listeRes){
                    supprimerReservation(r, t);
                }

                List<Etape> listeEtapes = t.getListeEtape();
                for(Etape e : listeEtapes){
                    em.remove(e);
                }

                em.remove(t);
            }

            q = em.createQuery("SELECT e FROM Etape e where e.villeEtape.nomVille=:villeSupp");
            q.setParameter("villeSupp", idVille);
            List<Etape> listeEtapes = q.getResultList();
            for(Etape e : listeEtapes){
                Trajet t = e.getTrajet();
                t.getListeEtape().remove(e);

                List<Reservation> listeRes = t.getListeReservation();
                for(Reservation r : listeRes){
                    if(null != r.getDescendA() && r.getDescendA().getVilleEtape().getNomVille().equals(idVille)){
                        supprimerReservation(r, t);
                    }
                }

                em.remove(e);
            }

            em.remove(ville);
            return true;
        }
    }

    private void supprimerReservation(Reservation r, Trajet t){
        Utilisateur u = r.getUtilisateurReservation();
        u.getListeReservation().remove(r);

        List<Appreciation> listeAppreciation = u.getNote();
        for(Appreciation a : listeAppreciation){
            if(a.getNoteTrajet().getIdTrajet() == t.getIdTrajet()){
                u.getNote().remove(a);
                em.remove(a);
            }
        }

        listeAppreciation = u.getEstNote();
        for(Appreciation a : listeAppreciation){
            if(a.getNoteTrajet().getIdTrajet() == t.getIdTrajet()){
                u.getEstNote().remove(a);
                em.remove(a);
            }
        }

        automate.creerNotification(u.getLogin(), "Votre réservation au départ de " + t.getVilleDepart().getNomVille()
                + " le " + t.getDate() + " et arrivant à " + t.getVilleArrivee().getNomVille() + " a été annulée par l'administrateur "
                + "du site car cette ville n'est plus desservie actuellement.");
        em.remove(r);
    }

    public boolean ajouterGabarit(String nomGabarit) throws GabaritException {
        Query q = em.createQuery("SELECT g FROM Gabarit g WHERE g.type=:gabarit");
        q.setParameter("gabarit", nomGabarit);
        try {
            Gabarit g = (Gabarit) q.getSingleResult();
        }catch (NoResultException e){
            Gabarit gabarit = new Gabarit(nomGabarit);
            em.persist(gabarit);
            return true;
        }
        throw new GabaritException("Ce gabarit existe déjà");

        /*
        List<Gabarit> gabarits = q.getResultList();
        if(gabarits.isEmpty()){
            Gabarit gabarit = new Gabarit(nomGabarit);
            em.persist(gabarit);
            return true;
        }
        return false;
        */
    }

    @Override
    public void supprimerGabarit(String gabaritASupprimer, String gabaritARemplacer) throws GabaritException {
        System.out.println(gabaritASupprimer+"-"+gabaritARemplacer);
        Query qSupp = em.createQuery("SELECT g FROM Gabarit g WHERE g.type=:gabarit");
        qSupp.setParameter("gabarit", gabaritASupprimer);
        Query qRemp = em.createQuery("SELECT g FROM Gabarit g WHERE g.type=:gabarit");
        qRemp.setParameter("gabarit", gabaritARemplacer);
        try {
            Gabarit gSupp = (Gabarit) qSupp.getSingleResult();
            Gabarit gRemp = (Gabarit) qRemp.getSingleResult();
            Query vehiculesAChanger = em.createQuery("SELECT v FROM Vehicule v WHERE v.gabarit= :gabarit");
            vehiculesAChanger.setParameter("gabarit",gSupp);
            List<Vehicule> vehicules = vehiculesAChanger.getResultList();
            for (Vehicule vehicule : vehicules){
                vehicule.setGabarit(gRemp);
                automate.creerNotification(vehicule.getUtilisateur().getLogin(),"L'administrateur du site a supprimé " +
                        "le gabarit "+gSupp.getType()+" et l'a remplacé par "+gRemp.getType()+". Ce gabarit était celui " +
                        "de votre véhicule "+vehicule.getNom()+".");
            }
            em.remove(gSupp);
        }catch(NoResultException e){
            throw new GabaritException("Le gabarit que vous essayez de supprimer n'existe pas");
        }
    }

    public StatistiquesDTO recupererStatistiques(){
        Query q = em.createQuery("SELECT u FROM Utilisateur u WHERE u.roleUtilisateur.message = 'utilisateur'");
        List<Utilisateur> listeUtilisateur = q.getResultList();
        int nbUtilisateur = listeUtilisateur.size();

        int nbConducteurs = 0;
        for(Utilisateur u : listeUtilisateur){
            midLoop:
            for(Vehicule v : u.getListeVehicule()){
                for(Trajet t : v.getListeTrajet()){
                    if(t.getStatut().equals("fini")){
                        nbConducteurs++;
                        break midLoop;
                    }
                }
            }
        }

        q = em.createQuery("SELECT DISTINCT res.utilisateurReservation.login FROM Reservation res WHERE res.statut=:statutReservation");
        q.setParameter("statutReservation", "accepte");
        int nbPassagers = q.getResultList().size();

        q = em.createQuery("SELECT res FROM Reservation res where res.statut=:statutReservation");
        q.setParameter("statutReservation", "accepte");
        int nbTrajetsAcceptes = q.getResultList().size();

        q = em.createQuery("SELECT res.trajetReservation.prix FROM Reservation res where res.statut=:statutReservation and res.trajetReservation.statut=:statutTrajet");
        q.setParameter("statutReservation", "accepte");
        q.setParameter("statutTrajet", "fini");
        List<Integer> listePrix = q.getResultList();
        int prixTotal = 0;
        for(int n : listePrix){
            prixTotal += n;
        }

        q = em.createQuery("SELECT COUNT (t) FROM Trajet t WHERE t.statut = 'fini'");
        int nbTrajetsFinis = ((Long)q.getSingleResult()).intValue();
        /*for(Trajet t : listeTrajets){
            if(t.getStatut().equals("fini")) {
                nbTrajetsFinis++;
            }
            //TODO trouver la ville avec le plus de départs et celle avec le plus d'arrivées
        }*/

        return new StatistiquesDTO(nbUtilisateur, nbPassagers, nbConducteurs, nbTrajetsAcceptes, prixTotal, nbTrajetsFinis);
    }
}
