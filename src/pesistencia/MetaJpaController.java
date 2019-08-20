/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pesistencia;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.Meta;
import modelo.Objetivo;
import pesistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author Victor
 */
public class MetaJpaController implements Serializable {

    public MetaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Meta meta) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Objetivo codObjetivo = meta.getCodObjetivo();
            if (codObjetivo != null) {
                codObjetivo = em.getReference(codObjetivo.getClass(), codObjetivo.getCodigo());
                meta.setCodObjetivo(codObjetivo);
            }
            em.persist(meta);
            if (codObjetivo != null) {
                codObjetivo.getMetaList().add(meta);
                codObjetivo = em.merge(codObjetivo);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Meta meta) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Meta persistentMeta = em.find(Meta.class, meta.getCodigo());
            Objetivo codObjetivoOld = persistentMeta.getCodObjetivo();
            Objetivo codObjetivoNew = meta.getCodObjetivo();
            if (codObjetivoNew != null) {
                codObjetivoNew = em.getReference(codObjetivoNew.getClass(), codObjetivoNew.getCodigo());
                meta.setCodObjetivo(codObjetivoNew);
            }
            meta = em.merge(meta);
            if (codObjetivoOld != null && !codObjetivoOld.equals(codObjetivoNew)) {
                codObjetivoOld.getMetaList().remove(meta);
                codObjetivoOld = em.merge(codObjetivoOld);
            }
            if (codObjetivoNew != null && !codObjetivoNew.equals(codObjetivoOld)) {
                codObjetivoNew.getMetaList().add(meta);
                codObjetivoNew = em.merge(codObjetivoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = meta.getCodigo();
                if (findMeta(id) == null) {
                    throw new NonexistentEntityException("The meta with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Meta meta;
            try {
                meta = em.getReference(Meta.class, id);
                meta.getCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The meta with id " + id + " no longer exists.", enfe);
            }
            Objetivo codObjetivo = meta.getCodObjetivo();
            if (codObjetivo != null) {
                codObjetivo.getMetaList().remove(meta);
                codObjetivo = em.merge(codObjetivo);
            }
            em.remove(meta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Meta> findMetaEntities() {
        return findMetaEntities(true, -1, -1);
    }

    public List<Meta> findMetaEntities(int maxResults, int firstResult) {
        return findMetaEntities(false, maxResults, firstResult);
    }

    private List<Meta> findMetaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Meta.class));
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

    public Meta findMeta(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Meta.class, id);
        } finally {
            em.close();
        }
    }

    public int getMetaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Meta> rt = cq.from(Meta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
