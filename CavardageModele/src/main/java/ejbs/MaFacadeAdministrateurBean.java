package ejbs;

import dtos.VilleDTO;
import entities.Gabarit;
import entities.Ville;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Stateless(name = "AdministrateurBean")
public class MaFacadeAdministrateurBean implements MaFacadeAdministrateur {

    @PersistenceContext(unitName = "monUnite")
    EntityManager em;

    private String selectVille = /*"SELECT Ville AS v FROM Ville where v.nomVille=:nom";*/ "From Ville v where v.nomVille=:nom";
    private String selectAllVilles = /*"SELECT Ville AS v FROM Ville";*/ "From Ville v";

    private String selectGabarit = /*"SELECT Gabarit AS g FROM Gabarit where g.type=:gabarit";*/ "From Gabarit g where g.type=:gabarit";
    private String selectAllGabarits = /*"SELECT Gabarit AS g FROM Gabarit";*/ "FROM Gabarit  g";


    public MaFacadeAdministrateurBean(){

    }


    public boolean ajouterVille(String nomVille,String departement){
        Query q = em.createQuery("From Ville v where v.nomVille=:nom");
        q.setParameter("nom", nomVille);
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

    public List<VilleDTO> getListeVilles(){
        Query q = em.createQuery("From Ville v");
        List<Ville> listeTemp = q.getResultList();
        if(!listeTemp.isEmpty()){
            List<VilleDTO> listeVilles = new ArrayList<>();
            for(Ville v : listeTemp){
                listeVilles.add(new VilleDTO(v));
            }
            return listeVilles;
        }
        return new ArrayList<>();
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
        Query q = em.createQuery("From Ville v where v.nomVille=:nom and v.departement=:departement ");
        q.setParameter("nom", nomVille);
        q.setParameter("departement", Integer.parseInt(departement));
        System.out.println("Modele" +departement);
        try {
            Ville v = (Ville) q.getSingleResult();
            em.remove(v);
            return true;
        }catch (NoResultException e){
            System.out.println("no ville");
            return false;
        }

        /*
        Query q = em.createQuery("DELETE FROM Ville v where v.nomVille=:nom");
        q.setParameter("nom", nomVille);
        q.executeUpdate();

        Query q2 = em.createNativeQuery("From Ville v where v.nomVille=:nom");
        q2.setParameter("nom", nomVille);
        try{
            q2.getSingleResult();
        }catch (NoResultException e){
            return true;
        }
        return false;
        */
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

        /*
        Query q = em.createQuery("DELETE FROM Gabarit g WHERE g.type=:gabarit");
        q.setParameter("gabarit", nomGabarit);
        q.executeUpdate();

        Query q2 = em.createNativeQuery("From Gabarit g where g.type=:gabarit");
        q2.setParameter("gabarit", nomGabarit);
        try{
            q2.getSingleResult();
        }catch(NoResultException e){
            return true;
        }
        return false;
        */
    }
}
