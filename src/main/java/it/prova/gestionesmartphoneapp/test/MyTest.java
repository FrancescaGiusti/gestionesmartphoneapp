package it.prova.gestionesmartphoneapp.test;

import it.prova.gestionesmartphoneapp.model.App;
import it.prova.gestionesmartphoneapp.model.Smartphone;
import it.prova.gestionesmartphoneapp.service.AppService;
import it.prova.gestionesmartphoneapp.service.MyServiceFactory;
import it.prova.gestionesmartphoneapp.service.SmartphoneService;

import java.time.LocalDate;

public class MyTest {
    public static void main(String[] args) throws Exception {
        AppService appServiceInstance = MyServiceFactory.getAppServiceInstance();
        SmartphoneService smartphoneServiceInstance = MyServiceFactory.getSmartphoneServiceInstance();

        System.out.println("In tabella Smartphone ci sono " + smartphoneServiceInstance.listAll().size() + " elementi.");
        System.out.println("In tabella App ci sono " + appServiceInstance.listAll().size() + " elementi.");
        System.out.println(
                "**************************** inizio batteria di test ********************************************");

        testInserisciNuovoSmartphone(smartphoneServiceInstance);
        testAggiornaVersioneOsSmartphone(smartphoneServiceInstance);
        testInserisciNuovaApp(appServiceInstance);
        testAggiornaVersioneEDataApp(appServiceInstance);
        testInstallaAppInSmartphone(appServiceInstance, smartphoneServiceInstance);
        testDisinstallaAppDaSmartphone(appServiceInstance, smartphoneServiceInstance);
        testInstallaAppInSmartphoneSecondaVersione(appServiceInstance, smartphoneServiceInstance);
        testRimuoviSmartphoneAssociatoConalmenoDueApp(appServiceInstance, smartphoneServiceInstance);

        System.out.println(
                "****************************** fine batteria di test ********************************************");
    }

    private static void testInserisciNuovoSmartphone(SmartphoneService smartphoneServiceInstance) throws Exception {
        System.out.println(".......testInserisciNuovoSmartphone inizio.............");

        Smartphone smartphoneInstance = new Smartphone("Apple", "Iphone-13", 800f, 10.2f);
        smartphoneServiceInstance.inserisciNuovo(smartphoneInstance);
        if (smartphoneInstance.getId() == null)
            throw new RuntimeException("testInserisciNuovoSmartphone fallito ");

        System.out.println(".......testInserisciNuovoSmartphone fine: PASSED.............");
    }

    private static void testAggiornaVersioneOsSmartphone(SmartphoneService smartphoneServiceInstance) throws Exception {
        System.out.println(".......testAggiornaVersioneOsSmartphone inizio.............");
        Smartphone smartphoneDaCambiare = smartphoneServiceInstance.listAll().get(0);
        Float vecchiaVersioneOs = smartphoneDaCambiare.getVersioneOS();
        smartphoneDaCambiare.setVersioneOS(13.2f);
        smartphoneServiceInstance.aggiornaVersioneOsSmartphoneEsistente(smartphoneDaCambiare);
        Smartphone smartphoneAggiornato = smartphoneServiceInstance.cercaPerId(smartphoneDaCambiare.getId());
        Float nuovaVersioneOs = smartphoneAggiornato.getVersioneOS();
        if (vecchiaVersioneOs == nuovaVersioneOs)
            throw new RuntimeException("testAggiornaVersioneOsSmartphone fallito ");

        System.out.println(".......testAggiornaVersioneOsSmartphone fine: PASSED.............");
    }

    private static void testInserisciNuovaApp(AppService appServiceInstance) throws Exception {
        System.out.println(".......testInserisciNuovaApp inizio.............");

        App appInstance = new App("Google", LocalDate.parse("2020-12-10"), LocalDate.parse("2025-03-12"), 10.2f);
        appServiceInstance.inserisciNuovo(appInstance);
        if (appInstance.getId() == null)
            throw new RuntimeException("testInserisciNuovaApp fallito ");

        System.out.println(".......testInserisciNuovaApp fine: PASSED.............");
    }

    private static void testAggiornaVersioneEDataApp(AppService appServiceInstance) throws Exception {
        System.out.println(".......testAggiornaVersioneEDataApp inizio.............");
        App appDaCambiare = appServiceInstance.listAll().get(0);
        Float vecchiaVersione = appDaCambiare.getVersione();
        LocalDate vecchiaDataAggiornamento = appDaCambiare.getDataUltimoAggiornamento();
        appDaCambiare.setVersione(20.2f);
        appDaCambiare.setDataUltimoAggiornamento(LocalDate.parse("2025-10-11"));
        appServiceInstance.aggiornaVersioneEDataApp(appDaCambiare);
        App appAggiornata = appServiceInstance.cercaPerId(appDaCambiare.getId());
        Float nuovaVersione = appAggiornata.getVersione();
        LocalDate nuovaDataAggiornamento = appAggiornata.getDataUltimoAggiornamento();
        if (vecchiaVersione == nuovaVersione)
            throw new RuntimeException("testAggiornaVersioneEDataApp fallito ");

        System.out.println(".......testAggiornaVersioneEDataApp fine: PASSED.............");
    }

    private static void testInstallaAppInSmartphone(AppService appServiceInstance, SmartphoneService smartphoneServiceInstance) throws Exception {
        System.out.println(".......testInstallaAppInSmartphone inizio.............");
        App appDaInstallareSuTelefono = appServiceInstance.listAll().get(0);
        Smartphone smartphoneSuCuiInstallareApp = smartphoneServiceInstance.listaDiTuttiITelefoniConApp().get(0);
        appServiceInstance.installaAppInSmartphone(appDaInstallareSuTelefono, smartphoneSuCuiInstallareApp);

        Smartphone smartphoneAggiornato = smartphoneServiceInstance.listaDiTuttiITelefoniConApp().get(0);
        boolean aggiornato = smartphoneAggiornato.getApps().stream().anyMatch(a -> a.getId().equals(appDaInstallareSuTelefono.getId()));

        if (!aggiornato) {
            throw new RuntimeException("testInstallaAppInSmartphone fallito: l'app non è stata installata correttamente");
        }
        System.out.println(".......testInstallaAppInSmartphone fine: PASSED.............");
    }

    private static void testDisinstallaAppDaSmartphone(AppService appServiceInstance, SmartphoneService smartphoneServiceInstance) throws Exception {
        System.out.println(".......testDisinstallaAppDaSmartphone inizio.............");

        Smartphone smartphone = smartphoneServiceInstance.listaDiTuttiITelefoniConApp().get(0);
        App appDaDisinstallare = smartphone.getApps().stream().findFirst().orElse(null);

        int numeroAppPrima = smartphone.getApps().size();

        appServiceInstance.disinstallaAppDaSmartphone(appDaDisinstallare, smartphone);

        Smartphone smartphoneAggiornato = smartphoneServiceInstance.listaDiTuttiITelefoniConApp().get(0);

        boolean appPresente = smartphoneAggiornato.getApps().stream()
                .anyMatch(a -> a.getId().equals(appDaDisinstallare.getId()));

        if (appPresente || smartphoneAggiornato.getApps().size() != numeroAppPrima - 1) {
            throw new RuntimeException("testDisinstallaAppDaSmartphone fallito");
        }

        System.out.println(".......testDisinstallaAppDaSmartphone fine: PASSED.............");
    }

    private static void testInstallaAppInSmartphoneSecondaVersione(AppService appServiceInstance, SmartphoneService smartphoneServiceInstance) throws Exception {
        System.out.println(".......testInstallaAppInSmartphone inizio.............");
        App appDaInstallareSuTelefono = appServiceInstance.listAll().get(0);
        Smartphone smartphoneSuCuiInstallareApp = smartphoneServiceInstance.listaDiTuttiITelefoniConApp().get(0);
        appServiceInstance.installaAppInSmartphone(appDaInstallareSuTelefono.getId(), smartphoneSuCuiInstallareApp.getId());

        Smartphone smartphoneAggiornato = smartphoneServiceInstance.listaDiTuttiITelefoniConApp().get(0);
        boolean aggiornato = smartphoneAggiornato.getApps().stream().anyMatch(a -> a.getId().equals(appDaInstallareSuTelefono.getId()));

        if (!aggiornato) {
            throw new RuntimeException("testInstallaAppInSmartphone fallito: l'app non è stata installata correttamente");
        }
        System.out.println(".......testInstallaAppInSmartphone fine: PASSED.............");
    }

    private static void testRimuoviSmartphoneAssociatoConalmenoDueApp(AppService appServiceInstance, SmartphoneService smartphoneServiceInstance) throws Exception {
        System.out.println(".......testRimuoviSmartphoneAssociatoConalmenoDueApp inizio.............");

        Smartphone smartphoneDaEliminare = smartphoneServiceInstance.listaDiTuttiITelefoniConApp().get(0);
        Long idTelefonoDaEliminare = smartphoneDaEliminare.getId();
        smartphoneServiceInstance.rimuoviTelefonoConAlmenoDueApp(idTelefonoDaEliminare);
        Long idTelefonoEliminato = smartphoneServiceInstance.listaDiTuttiITelefoniConApp().get(0).getId();
        if (idTelefonoEliminato.equals(idTelefonoDaEliminare)) {
            throw new RuntimeException("testRimuoviSmartphoneAssociatoConalmenoDueApp fallito: il telefono non è stato rimosso correttamente");
        }
        System.out.println(".......testRimuoviSmartphoneAssociatoConalmenoDueApp fine: PASSED.............");
    }

}
