package it.prova.gestionesmartphoneapp.dao;


import it.prova.gestionesmartphoneapp.dao.App.AppDAO;
import it.prova.gestionesmartphoneapp.dao.App.AppDAOImpl;
import it.prova.gestionesmartphoneapp.dao.Smartphone.SmartphoneDAO;
import it.prova.gestionesmartphoneapp.dao.Smartphone.SmartphoneDAOImpl;

public class MyDaoFactory {

	private static AppDAO appDaoInstance = null;
	private static SmartphoneDAO smartphoneDaoInstance = null;

	public static AppDAO getAppDAOInstance() {
		if (appDaoInstance == null)
            appDaoInstance = new AppDAOImpl();

		return appDaoInstance;
	}

	public static SmartphoneDAO getSmartphoneDAOInstance() {
		if (smartphoneDaoInstance == null)
            smartphoneDaoInstance = new SmartphoneDAOImpl();

		return smartphoneDaoInstance;
	}

}
