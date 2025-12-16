package it.prova.gestionesmartphoneapp.dao.App;

import it.prova.gestionesmartphoneapp.model.App;
import it.prova.gestionesmartphoneapp.model.Smartphone;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

public class AppDAOImpl implements AppDAO {

    private EntityManager entityManager;

    @Override
    public List<App> findAll() throws Exception {
        return entityManager.createQuery("from App", App.class).getResultList();
    }

    @Override
    public App findById(Long id) throws Exception {
        return entityManager.find(App.class, id);
    }

    @Override
    public void update(App input) throws Exception {
        if (input == null) {
            throw new Exception("Problema valore in input");
        }
        input = entityManager.merge(input);
    }

    @Override
    public void insert(App input) throws Exception {
        if (input == null) {
            throw new Exception("Problema valore in input");
        }
        entityManager.persist(input);
    }

    @Override
    public void delete(Long id) throws Exception {
        if (id == null)
            throw new Exception("Problema valore in input");

        entityManager.createQuery("delete from App where id=?1").setParameter(1, id).executeUpdate();
    }

    @Override
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void updateVersionAndDateApp(App appInstance) throws Exception {
        if (appInstance.getId() == null) {
            throw new Exception("App non valida");
        }
        App app = entityManager.find(App.class, appInstance.getId());
        if(app != null) {
            app.setVersione(appInstance.getVersione());
            app.setDataUltimoAggiornamento(appInstance.getDataUltimoAggiornamento());
        } else {
            throw new Exception("App non trovato");
        }
    }

    @Override
    public void installationAppInSmartphone(App appInstance, Smartphone smartphoneInstance) throws Exception {
        if (appInstance.getId() == null || smartphoneInstance.getId() == null){
            throw new Exception("Valori input non validi");
        }

        App app = entityManager.find(App.class, appInstance.getId());
        Smartphone smartphone = entityManager.find(Smartphone.class, smartphoneInstance.getId());

        smartphone.getApps().add(app);
        app.getTelefoni().add(smartphone);
    }

    public void installationAppInSmartphone (Long idApp, Long idSmartphone) throws Exception {
        if (idApp == null || idSmartphone == null){
            throw new Exception("Valori input non validi");
        }
        entityManager.createNativeQuery("insert into app_smartphone (app_id, smartphone_id) values (?, ?)").setParameter(1, idApp).setParameter(2, idSmartphone).executeUpdate();
    }

    @Override
    public void removeAppFromSmartphone(App appInstance, Smartphone smartphoneInstance) throws Exception {
        if (appInstance.getId() == null || smartphoneInstance.getId() == null){
            throw new Exception("Valori input non validi");
        }
        Smartphone smartphone = entityManager.find(Smartphone.class, smartphoneInstance.getId());
        App app = entityManager.find(App.class, appInstance.getId());
        smartphone.getApps().remove(app);
        app.getTelefoni().remove(smartphone);
    }
}
