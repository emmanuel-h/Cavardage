package ejbs;

import dtos.VilleDTO;
import entities.Gabarit;
import entities.Ville;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
}
