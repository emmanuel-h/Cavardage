package ejbs;

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


    public MaFacadeAdministrateurBean(){

    }


    public boolean ajouterVille(String nomVille){
        Query q = em.createNamedQuery("selectVille");
        q.setParameter("nom", nomVille);
        Ville v;
        try{
            v = (Ville) q.getSingleResult();
        }catch(NoResultException e){
            v = new Ville(nomVille);
            em.persist(v);
            return true;
        }
        return false;
    }

    public List<String> getListeVilles(){
        Query q = em.createNamedQuery("selectAllVilles");
        List<Ville> listeTemp = q.getResultList();
        if(!listeTemp.isEmpty()){
            List<String> listeVilles = new ArrayList<>();
            for(Ville v : listeTemp){
                listeVilles.add(v.getNomVille());
            }
            return listeVilles;
        }
        return null;
    }

    public List<String> getListeGabarits(){
        Query q = em.createNamedQuery("selectAllGabarits");
        List<Gabarit> listeTemp = q.getResultList();
        if(!listeTemp.isEmpty()){
            List<String> listeGabarits = new ArrayList<>();
            for(Gabarit g : listeTemp){
                listeGabarits.add(g.getType());
            }
            return listeGabarits;
        }
        return null;
    }

    public boolean supprimerVille(String nomVille){
        Query q = em.createQuery("DELETE FROM Ville v where v.nomVille=:nom");
        q.setParameter("nom", nomVille);
        q.executeUpdate();

        Query q2 = em.createNamedQuery("selectVille");
        q2.setParameter("nom", nomVille);
        try{
            q2.getSingleResult();
        }catch (NoResultException e){
            return true;
        }
        return false;
    }

    public boolean ajouterGabarit(String nomGabarit){
        Query q = em.createNamedQuery("selectGabarit");
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
        Query q = em.createQuery("DELETE FROM Gabarit g where g.type=:gabarit");
        q.setParameter("gabarit", nomGabarit);
        q.executeUpdate();

        Query q2 = em.createNamedQuery("selectGabarit");
        q2.setParameter("gabarit", nomGabarit);
        try{
            q2.getSingleResult();
        }catch(NoResultException e){
            return true;
        }
        return false;
    }
}
