package ejbs;

import dtos.StatistiquesDTO;
import dtos.VilleDTO;
import entities.*;
import exceptions.VilleNonTrouvee;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.*;

@Stateless(name = "AdministrateurBean")
public class MaFacadeAdministrateurBean implements MaFacadeAdministrateur {

    @PersistenceContext(unitName = "monUnite")
    EntityManager em;
    @EJB
    RechercheBean recherche;
    @EJB
    Automate automate;

    public MaFacadeAdministrateurBean(){

    }


    public boolean ajouterVille(String nomVille,String departement){
        Query q = em.createQuery("From Ville v where v.nomVille=:nom");
        q.setParameter("nom", nomVille + "_" + departement);
        Ville v;
        try{
            v = (Ville) q.getSingleResult();
        }catch(NoResultException e){
            v = new Ville(nomVille,Integer.parseInt(departement));
            em.persist(v);
            return true;
        }
        return false;
    }

    public List<Ville> getListeVilles(){
        return recherche.getListeVilles();
    }

    public List<VilleDTO> getListeVilleDTO(){
        return recherche.getListeVillesDTO();
    }

    public List<String> getListeGabarits(){
        Query q = em.createQuery("From Gabarit g");
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
            throw new VilleNonTrouvee();
        }else{
            List<Utilisateur> utilisateursANotifier = new ArrayList<>();
            Query q = em.createQuery("SELECT t FROM Trajet t WHERE t.villeDepart.nomVille=:villeSupp OR t.villeArrivee.nomVille=:villeSupp");
            q.setParameter("villeSupp", idVille);
            List<Trajet> listeTrajets = q.getResultList();
            for(Trajet t : listeTrajets){
                Vehicule v = t.getVehiculeTrajet();
                v.getListeTrajet().remove(t);

                List<Etape> listeEtapes = t.getListeEtape();
                for(Etape e : listeEtapes){
                    em.remove(e);
                }

                //utilisateursANotifier.addAll(supprimerReservation(t));
                supprimerReservation(t);

                em.remove(t);

            }

            q = em.createQuery("SELECT e FROM Etape e WHERE e.villeEtape.nomVille=:villeSupp");
            q.setParameter("villeSupp", idVille);
            List<Etape> listeEtapes = q.getResultList();
            for(Etape e : listeEtapes){
                Trajet t = e.getTrajet();
                //utilisateursANotifier.addAll(supprimerReservation(t));
                supprimerReservation(t);
                t.getListeEtape().remove(e);
                em.remove(e);
            }

            em.remove(ville);
            return true;
        }
        /*
        String idVille = nomVille + "_" + departement;
        Ville v = em.find(Ville.class, idVille);
        if(null != v){
            em.remove(v);
            return true;
        }else{
            return false;
        }
        */
    }

    private void supprimerReservation(Trajet t){
        List<Utilisateur> utilisateursANotifier = new ArrayList<>();
        List<Reservation> listeReservation = t.getListeReservation();
        for(Reservation r : listeReservation){
            Utilisateur u = r.getUtilisateurReservation();
            utilisateursANotifier.add(u);
            u.getListeReservation().remove(r);

            List<Appreciation> listeAppreciations = u.getEstNote();
            for(Appreciation a : listeAppreciations){
                if(a.getNoteTrajet().getIdTrajet() == t.getIdTrajet()){
                    u.getEstNote().remove(a);
                    em.remove(a);
                }
            }
            listeAppreciations = u.getNote();
            for(Appreciation a : listeAppreciations){
                if(a.getNoteTrajet().getIdTrajet() == t.getIdTrajet()){
                    u.getNote().remove(a);
                    em.remove(a);
                }
            }
            String villeDepartTemp = t.getVilleDepart().getNomVille();
            String villeArriveeTemp = t.getVilleArrivee().getNomVille();
            StringTokenizer st = new StringTokenizer(villeDepartTemp, "_");
            String villeDepart = st.nextToken() + "(" + st.nextToken() + ")";
            st = new StringTokenizer(villeArriveeTemp, "_");
            String villeArrivee = st.nextToken() + "(" + st.nextToken() + ")";
            automate.creerNotification(u.getLogin(), "Votre réservation au départ de " + villeDepart
                    + " le " + t.getDate() + " et arrivant à " + villeArrivee + " a été annulée par l'administrateur "
                    + "du site car cette ville n'est plus desservie actuellement.");
            em.remove(r);
        }

        //return utilisateursANotifier;
    }

    public boolean ajouterGabarit(String nomGabarit){
        Query q = em.createQuery("From Gabarit g where g.type=:gabarit");
        q.setParameter("gabarit", nomGabarit);
        Gabarit g;
        try{
            g = (Gabarit) q.getSingleResult();
        }catch(NoResultException e){
            g = new Gabarit(nomGabarit);
            em.persist(g);
            return true;
        }
        return false;
    }

    public boolean supprimerGabarit(String gabaritASupprimer, String gabaritARemplacer){
        System.out.println(gabaritASupprimer+"-"+gabaritARemplacer);
        Query qSupp = em.createQuery("SELECT g FROM Gabarit g where g.type=:gabarit");
        qSupp.setParameter("gabarit", gabaritASupprimer);
        Query qRemp = em.createQuery("SELECT g FROM Gabarit g where g.type=:gabarit");
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
            return true;
        }catch(NoResultException e){
            return false;
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

        StatistiquesDTO stat = new StatistiquesDTO(nbUtilisateur, nbPassagers, nbConducteurs, nbTrajetsAcceptes, prixTotal, nbTrajetsFinis);

        return stat;

    }
}
