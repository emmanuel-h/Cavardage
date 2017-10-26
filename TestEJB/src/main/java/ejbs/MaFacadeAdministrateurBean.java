package ejbs;

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


    public List<String> getListeVilles(){
        Query q = em.createQuery("From Ville v");
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


}
