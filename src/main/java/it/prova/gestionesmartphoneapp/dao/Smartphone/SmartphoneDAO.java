package it.prova.gestionesmartphoneapp.dao.Smartphone;

import it.prova.gestionesmartphoneapp.dao.IBaseDAO;
import it.prova.gestionesmartphoneapp.model.Smartphone;

import java.util.List;

public interface SmartphoneDAO extends IBaseDAO<Smartphone> {
    void updateOsVersion(Smartphone smartphone) throws Exception;
    List<Smartphone> listAllConApp() throws Exception;
    void removeSmartphoneAssociatedWTwoApps(Long idSmartphone)throws Exception;
}
