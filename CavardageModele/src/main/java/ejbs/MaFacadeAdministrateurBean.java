package ejbs;

import dtos.StatistiquesDTO;
import dtos.VilleDTO;
import entities.*;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Stateless(name = "AdministrateurBean")
public class MaFacadeAdministrateurBean implements MaFacadeAdministrateur {

    @PersistenceContext(unitName = "monUnite")
    EntityManager em;
    @EJB
    RechercheBean recherche;

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

    public boolean supprimerVille(String nomVille, String departement){
        String idVille = nomVille + "_" + departement;
        Ville v = em.find(Ville.class, idVille);
        if(null != v){
            em.remove(v);
            return true;
        }else{
            return false;
        }
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

    public boolean supprimerGabarit(String nomGabarit){
        Query q = em.createQuery("From Gabarit g where g.type=:gabarit");
        q.setParameter("gabarit", nomGabarit);
        try {
            Gabarit g = (Gabarit) q.getSingleResult();
            em.remove(g);
            return true;
        }catch(NoResultException e){
            return false;
        }
    }

    public StatistiquesDTO recupererStatistiques(){
        Query q = em.createQuery("From Utilisateur u");
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

        q = em.createQuery("FROM Reservation res where res.statut=:statutReservation");
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

        q = em.createQuery("FROM Trajet t");
        List<Trajet> listeTrajets = q.getResultList();
        int nbTrajetsFinis = 0;
        //Map<String, Integer> mapDeparts = new TreeMap<>();
        //Map<String, Integer> mapArrivees = new TreeMap<>();
        for(Trajet t : listeTrajets){
            if(t.getStatut().equals("fini")) {
                nbTrajetsFinis++;
            }
            //TODO trouver la ville avec le plus de départs et celle avec le plus d'arrivées
        }

        StatistiquesDTO stat = new StatistiquesDTO(nbUtilisateur, nbPassagers, nbConducteurs, nbTrajetsAcceptes, prixTotal, nbTrajetsFinis);

        return stat;

    }
}
