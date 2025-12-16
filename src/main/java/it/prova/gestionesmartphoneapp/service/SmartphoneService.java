package it.prova.gestionesmartphoneapp.service;

import it.prova.gestionesmartphoneapp.dao.Smartphone.SmartphoneDAO;
import it.prova.gestionesmartphoneapp.model.Smartphone;

import java.util.List;

public interface SmartphoneService {

	public List<Smartphone> listAll() throws Exception;

	public Smartphone caricaSingoloElemento(Long id) throws Exception;

	public void aggiorna(Smartphone smartphoneInstance) throws Exception;

	public void inserisciNuovo(Smartphone genereInstance) throws Exception;

	public void rimuovi(Long idSmartphone) throws Exception;

	public void setSmartphoneDAO(SmartphoneDAO smartphoneDAO);

    public void aggiornaVersioneOsSmartphoneEsistente(Smartphone smartphoneInstance) throws Exception;

    public Smartphone cercaPerId(Long id) throws Exception;

    public List<Smartphone> listaDiTuttiITelefoniConApp() throws Exception;

    public void rimuoviTelefonoConAlmenoDueApp(Long idSmartphone)throws Exception;

}
