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
import modelo.Indicador;
import modelo.Objetivo;
import pesistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author Victor
 */
public class IndicadorJpaController implements Serializable {

    public IndicadorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Indicador indicador) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Objetivo codObjetivo = indicador.getCodObjetivo();
            if (codObjetivo != null) {
                codObjetivo = em.getReference(codObjetivo.getClass(), codObjetivo.getCodigo());
                indicador.setCodObjetivo(codObjetivo);
            }
            em.persist(indicador);
            if (codObjetivo != null) {
                codObjetivo.getIndicadorList().add(indicador);
                codObjetivo = em.merge(codObjetivo);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Indicador indicador) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Indicador persistentIndicador = em.find(Indicador.class, indicador.getCodigo());
            Objetivo codObjetivoOld = persistentIndicador.getCodObjetivo();
            Objetivo codObjetivoNew = indicador.getCodObjetivo();
            if (codObjetivoNew != null) {
                codObjetivoNew = em.getReference(codObjetivoNew.getClass(), codObjetivoNew.getCodigo());
                indicador.setCodObjetivo(codObjetivoNew);
            }
            indicador = em.merge(indicador);
            if (codObjetivoOld != null && !codObjetivoOld.equals(codObjetivoNew)) {
                codObjetivoOld.getIndicadorList().remove(indicador);
                codObjetivoOld = em.merge(codObjetivoOld);
            }
            if (codObjetivoNew != null && !codObjetivoNew.equals(codObjetivoOld)) {
                codObjetivoNew.getIndicadorList().add(indicador);
                codObjetivoNew = em.merge(codObjetivoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = indicador.getCodigo();
                if (findIndicador(id) == null) {
                    throw new NonexistentEntityException("The indicador with id " + id + " no longer exists.");
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
            Indicador indicador;
            try {
                indicador = em.getReference(Indicador.class, id);
                indicador.getCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The indicador with id " + id + " no longer exists.", enfe);
            }
            Objetivo codObjetivo = indicador.getCodObjetivo();
            if (codObjetivo != null) {
                codObjetivo.getIndicadorList().remove(indicador);
                codObjetivo = em.merge(codObjetivo);
            }
            em.remove(indicador);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Indicador> findIndicadorEntities() {
        return findIndicadorEntities(true, -1, -1);
    }

    public List<Indicador> findIndicadorEntities(int maxResults, int firstResult) {
        return findIndicadorEntities(false, maxResults, firstResult);
    }

    private List<Indicador> findIndicadorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Indicador.class));
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

    public Indicador findIndicador(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Indicador.class, id);
        } finally {
            em.close();
        }
    }

    public int getIndicadorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Indicador> rt = cq.from(Indicador.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
