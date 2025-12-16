package it.prova.gestionesmartphoneapp.service;

import it.prova.gestionesmartphoneapp.dao.App.AppDAO;
import it.prova.gestionesmartphoneapp.model.App;
import it.prova.gestionesmartphoneapp.model.Smartphone;

import java.util.List;

public interface AppService {

	public List<App> listAll() throws Exception;

	public App caricaSingoloElemento(Long id) throws Exception;

	public void aggiorna(App appInstance) throws Exception;

	public void inserisciNuovo(App appInstance) throws Exception;

	public void rimuovi(Long idApp) throws Exception;

	public void setAppDAO(AppDAO appDAO);

    public void aggiornaVersioneEDataApp(App appInstance) throws Exception;

    public App cercaPerId(Long id) throws Exception;

    public void installaAppInSmartphone(App appinstance, Smartphone smartphoneInstance)throws Exception;

    public void disinstallaAppDaSmartphone(App appinstance, Smartphone smartphoneInstance) throws Exception;

    public void installaAppInSmartphone(Long idApp, Long idSmartphone)throws Exception;

}
