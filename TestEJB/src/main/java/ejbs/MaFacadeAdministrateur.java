package ejbs;

import javax.ejb.Local;
import java.util.List;

@Local
public interface MaFacadeAdministrateur {



    List<String> getListeVilles();


}
