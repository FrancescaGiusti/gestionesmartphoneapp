package it.prova.gestionesmartphoneapp.dao.Smartphone;

import it.prova.gestionesmartphoneapp.model.App;
import it.prova.gestionesmartphoneapp.model.Smartphone;
import javax.persistence.EntityManager;
import java.util.List;

public class SmartphoneDAOImpl implements SmartphoneDAO {

	private EntityManager entityManager;

	@Override
	public List<Smartphone> findAll() throws Exception {
		return entityManager.createQuery("from Smartphone", Smartphone.class).getResultList();
	}

	@Override
	public Smartphone findById(Long id) throws Exception {
		return entityManager.find(Smartphone.class, id);
	}

	@Override
	public void update(Smartphone input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		input = entityManager.merge(input);
	}

	@Override
	public void insert(Smartphone input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.persist(input);
	}

	@Override
	public void delete(Long id) throws Exception {
		if (id == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.createQuery("delete from Smartphone where id=?1").setParameter(1, id).executeUpdate();
	}
    @Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

    @Override
    public void updateOsVersion(Smartphone smartphone) throws Exception {
        Smartphone s = entityManager.find(Smartphone.class, smartphone.getId());
        if(s != null) {
            s.setVersioneOS(smartphone.getVersioneOS());
        } else {
            throw new Exception("Smartphone non trovato");
        }
    }

    @Override
    public List<Smartphone> listAllConApp() throws Exception {
        return entityManager.createQuery("select s from Smartphone s left join fetch s.apps", Smartphone.class).getResultList();
    }

    @Override
    public void removeSmartphoneAssociatedWTwoApps(Long idSmartphone) throws Exception {
       Smartphone smartphone = entityManager.createQuery("select s from Smartphone s where s.id = :id", Smartphone.class).setParameter("id", idSmartphone).getSingleResult();

       if (smartphone.getApps().size() < 2){
           throw new Exception("Lo smartphone non è associato ad almeno due app");
       }
        for (App app : smartphone.getApps()) {
            app.getTelefoni().remove(smartphone);
        }
        smartphone.getApps().clear();

        entityManager.remove(smartphone);
    }
}
