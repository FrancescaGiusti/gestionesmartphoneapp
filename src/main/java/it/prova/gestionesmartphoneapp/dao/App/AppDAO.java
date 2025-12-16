package it.prova.gestionesmartphoneapp.dao.App;

import it.prova.gestionesmartphoneapp.dao.IBaseDAO;
import it.prova.gestionesmartphoneapp.model.App;
import it.prova.gestionesmartphoneapp.model.Smartphone;


public interface AppDAO extends IBaseDAO<App> {
    void updateVersionAndDateApp(App appInstance) throws Exception;
    void installationAppInSmartphone(App appInstance, Smartphone smartphoneInstance) throws Exception;
    void removeAppFromSmartphone(App appInstance, Smartphone smartphoneInstance) throws Exception;
    public void installationAppInSmartphone (Long idApp, Long idSmartphone) throws Exception;
}
