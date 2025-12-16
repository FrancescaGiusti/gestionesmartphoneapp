package it.prova.gestionesmartphoneapp.service;

import it.prova.gestionesmartphoneapp.dao.EntityManagerUtil;
import it.prova.gestionesmartphoneapp.dao.Smartphone.SmartphoneDAO;
import it.prova.gestionesmartphoneapp.model.Smartphone;
import javax.persistence.EntityManager;
import java.util.List;

public class SmartphoneServiceImpl implements SmartphoneService {

	private SmartphoneDAO smartphoneDAO;

	@Override
	public List<Smartphone> listAll() throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			smartphoneDAO.setEntityManager(entityManager);

			return smartphoneDAO.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public Smartphone caricaSingoloElemento(Long id) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			smartphoneDAO.setEntityManager(entityManager);

			return smartphoneDAO.findById(id);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void aggiorna(Smartphone smartphoneInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			entityManager.getTransaction().begin();

			smartphoneDAO.setEntityManager(entityManager);

			smartphoneDAO.update(smartphoneInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void inserisciNuovo(Smartphone smartphoneInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			entityManager.getTransaction().begin();

			smartphoneDAO.setEntityManager(entityManager);

			smartphoneDAO.insert(smartphoneInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void rimuovi(Long idSmatphone) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			entityManager.getTransaction().begin();

			smartphoneDAO.setEntityManager(entityManager);

			smartphoneDAO.delete(idSmatphone);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}
	@Override
	public void setSmartphoneDAO(SmartphoneDAO smartphoneDAO) {
		this.smartphoneDAO = smartphoneDAO;
	}

    @Override
    public void aggiornaVersioneOsSmartphoneEsistente(Smartphone smartphoneInstance) throws Exception {
        EntityManager entityManager = EntityManagerUtil.getEntityManager();

        try {
            entityManager.getTransaction().begin();

            smartphoneDAO.setEntityManager(entityManager);

            smartphoneDAO.updateOsVersion(smartphoneInstance);

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
            throw e;
        } finally {
            EntityManagerUtil.closeEntityManager(entityManager);
        }
    }

    @Override
    public Smartphone cercaPerId(Long id) throws Exception {
        EntityManager entityManager = EntityManagerUtil.getEntityManager();

        try {

            smartphoneDAO.setEntityManager(entityManager);

            return smartphoneDAO.findById(id);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            EntityManagerUtil.closeEntityManager(entityManager);
        }
    }

    @Override
    public List<Smartphone> listaDiTuttiITelefoniConApp() throws Exception {
        EntityManager entityManager = EntityManagerUtil.getEntityManager();

        try {
            smartphoneDAO.setEntityManager(entityManager);

            return smartphoneDAO.listAllConApp();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            EntityManagerUtil.closeEntityManager(entityManager);
        }
    }

    @Override
    public void rimuoviTelefonoConAlmenoDueApp(Long idSmartphone) throws Exception {
        EntityManager entityManager = EntityManagerUtil.getEntityManager();

        try {
            entityManager.getTransaction().begin();

            smartphoneDAO.setEntityManager(entityManager);

            smartphoneDAO.removeSmartphoneAssociatedWTwoApps(idSmartphone);

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
            throw e;
        } finally {
            EntityManagerUtil.closeEntityManager(entityManager);
        }
    }

}
