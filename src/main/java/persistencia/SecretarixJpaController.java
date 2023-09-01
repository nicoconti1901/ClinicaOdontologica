package persistencia;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import logica.Secretarix;
import persistencia.exceptions.NonexistentEntityException;

public class SecretarixJpaController implements Serializable {

    public SecretarixJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public SecretarixJpaController() {
        emf = Persistence.createEntityManagerFactory("consultorioOdontologico_PU");
    }

    public void create(Secretarix secretarix) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(secretarix);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Secretarix secretarix) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            secretarix = em.merge(secretarix);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = secretarix.getId();
                if (findSecretarix(id) == null) {
                    throw new NonexistentEntityException("The secretarix with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Secretarix secretarix;
            try {
                secretarix = em.getReference(Secretarix.class, id);
                secretarix.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The secretarix with id " + id + " no longer exists.", enfe);
            }
            em.remove(secretarix);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Secretarix> findSecretarixEntities() {
        return findSecretarixEntities(true, -1, -1);
    }

    public List<Secretarix> findSecretarixEntities(int maxResults, int firstResult) {
        return findSecretarixEntities(false, maxResults, firstResult);
    }

    private List<Secretarix> findSecretarixEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Secretarix.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Secretarix findSecretarix(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Secretarix.class, id);
        } finally {
            em.close();
        }
    }

    public int getSecretarixCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Secretarix> rt = cq.from(Secretarix.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
