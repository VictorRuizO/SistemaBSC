/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pesistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.Area;
import modelo.Indicador;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import modelo.Iniciativa;
import modelo.Meta;
import modelo.Objetivo;
import pesistencia.exceptions.IllegalOrphanException;
import pesistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author Victor
 */
public class ObjetivoJpaController implements Serializable {

    public ObjetivoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Objetivo objetivo) {
        if (objetivo.getIndicadorList() == null) {
            objetivo.setIndicadorList(new ArrayList<Indicador>());
        }
        if (objetivo.getIniciativaList() == null) {
            objetivo.setIniciativaList(new ArrayList<Iniciativa>());
        }
        if (objetivo.getMetaList() == null) {
            objetivo.setMetaList(new ArrayList<Meta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Area codArea = objetivo.getCodArea();
            if (codArea != null) {
                codArea = em.getReference(codArea.getClass(), codArea.getCodigo());
                objetivo.setCodArea(codArea);
            }
            List<Indicador> attachedIndicadorList = new ArrayList<Indicador>();
            for (Indicador indicadorListIndicadorToAttach : objetivo.getIndicadorList()) {
                indicadorListIndicadorToAttach = em.getReference(indicadorListIndicadorToAttach.getClass(), indicadorListIndicadorToAttach.getCodigo());
                attachedIndicadorList.add(indicadorListIndicadorToAttach);
            }
            objetivo.setIndicadorList(attachedIndicadorList);
            List<Iniciativa> attachedIniciativaList = new ArrayList<Iniciativa>();
            for (Iniciativa iniciativaListIniciativaToAttach : objetivo.getIniciativaList()) {
                iniciativaListIniciativaToAttach = em.getReference(iniciativaListIniciativaToAttach.getClass(), iniciativaListIniciativaToAttach.getCodigo());
                attachedIniciativaList.add(iniciativaListIniciativaToAttach);
            }
            objetivo.setIniciativaList(attachedIniciativaList);
            List<Meta> attachedMetaList = new ArrayList<Meta>();
            for (Meta metaListMetaToAttach : objetivo.getMetaList()) {
                metaListMetaToAttach = em.getReference(metaListMetaToAttach.getClass(), metaListMetaToAttach.getCodigo());
                attachedMetaList.add(metaListMetaToAttach);
            }
            objetivo.setMetaList(attachedMetaList);
            em.persist(objetivo);
            if (codArea != null) {
                codArea.getObjetivoList().add(objetivo);
                codArea = em.merge(codArea);
            }
            for (Indicador indicadorListIndicador : objetivo.getIndicadorList()) {
                Objetivo oldCodObjetivoOfIndicadorListIndicador = indicadorListIndicador.getCodObjetivo();
                indicadorListIndicador.setCodObjetivo(objetivo);
                indicadorListIndicador = em.merge(indicadorListIndicador);
                if (oldCodObjetivoOfIndicadorListIndicador != null) {
                    oldCodObjetivoOfIndicadorListIndicador.getIndicadorList().remove(indicadorListIndicador);
                    oldCodObjetivoOfIndicadorListIndicador = em.merge(oldCodObjetivoOfIndicadorListIndicador);
                }
            }
            for (Iniciativa iniciativaListIniciativa : objetivo.getIniciativaList()) {
                Objetivo oldCodObjetivoOfIniciativaListIniciativa = iniciativaListIniciativa.getCodObjetivo();
                iniciativaListIniciativa.setCodObjetivo(objetivo);
                iniciativaListIniciativa = em.merge(iniciativaListIniciativa);
                if (oldCodObjetivoOfIniciativaListIniciativa != null) {
                    oldCodObjetivoOfIniciativaListIniciativa.getIniciativaList().remove(iniciativaListIniciativa);
                    oldCodObjetivoOfIniciativaListIniciativa = em.merge(oldCodObjetivoOfIniciativaListIniciativa);
                }
            }
            for (Meta metaListMeta : objetivo.getMetaList()) {
                Objetivo oldCodObjetivoOfMetaListMeta = metaListMeta.getCodObjetivo();
                metaListMeta.setCodObjetivo(objetivo);
                metaListMeta = em.merge(metaListMeta);
                if (oldCodObjetivoOfMetaListMeta != null) {
                    oldCodObjetivoOfMetaListMeta.getMetaList().remove(metaListMeta);
                    oldCodObjetivoOfMetaListMeta = em.merge(oldCodObjetivoOfMetaListMeta);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Objetivo objetivo) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Objetivo persistentObjetivo = em.find(Objetivo.class, objetivo.getCodigo());
            Area codAreaOld = persistentObjetivo.getCodArea();
            Area codAreaNew = objetivo.getCodArea();
            List<Indicador> indicadorListOld = persistentObjetivo.getIndicadorList();
            List<Indicador> indicadorListNew = objetivo.getIndicadorList();
            List<Iniciativa> iniciativaListOld = persistentObjetivo.getIniciativaList();
            List<Iniciativa> iniciativaListNew = objetivo.getIniciativaList();
            List<Meta> metaListOld = persistentObjetivo.getMetaList();
            List<Meta> metaListNew = objetivo.getMetaList();
            List<String> illegalOrphanMessages = null;
            for (Indicador indicadorListOldIndicador : indicadorListOld) {
                if (!indicadorListNew.contains(indicadorListOldIndicador)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Indicador " + indicadorListOldIndicador + " since its codObjetivo field is not nullable.");
                }
            }
            for (Iniciativa iniciativaListOldIniciativa : iniciativaListOld) {
                if (!iniciativaListNew.contains(iniciativaListOldIniciativa)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Iniciativa " + iniciativaListOldIniciativa + " since its codObjetivo field is not nullable.");
                }
            }
            for (Meta metaListOldMeta : metaListOld) {
                if (!metaListNew.contains(metaListOldMeta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Meta " + metaListOldMeta + " since its codObjetivo field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (codAreaNew != null) {
                codAreaNew = em.getReference(codAreaNew.getClass(), codAreaNew.getCodigo());
                objetivo.setCodArea(codAreaNew);
            }
            List<Indicador> attachedIndicadorListNew = new ArrayList<Indicador>();
            for (Indicador indicadorListNewIndicadorToAttach : indicadorListNew) {
                indicadorListNewIndicadorToAttach = em.getReference(indicadorListNewIndicadorToAttach.getClass(), indicadorListNewIndicadorToAttach.getCodigo());
                attachedIndicadorListNew.add(indicadorListNewIndicadorToAttach);
            }
            indicadorListNew = attachedIndicadorListNew;
            objetivo.setIndicadorList(indicadorListNew);
            List<Iniciativa> attachedIniciativaListNew = new ArrayList<Iniciativa>();
            for (Iniciativa iniciativaListNewIniciativaToAttach : iniciativaListNew) {
                iniciativaListNewIniciativaToAttach = em.getReference(iniciativaListNewIniciativaToAttach.getClass(), iniciativaListNewIniciativaToAttach.getCodigo());
                attachedIniciativaListNew.add(iniciativaListNewIniciativaToAttach);
            }
            iniciativaListNew = attachedIniciativaListNew;
            objetivo.setIniciativaList(iniciativaListNew);
            List<Meta> attachedMetaListNew = new ArrayList<Meta>();
            for (Meta metaListNewMetaToAttach : metaListNew) {
                metaListNewMetaToAttach = em.getReference(metaListNewMetaToAttach.getClass(), metaListNewMetaToAttach.getCodigo());
                attachedMetaListNew.add(metaListNewMetaToAttach);
            }
            metaListNew = attachedMetaListNew;
            objetivo.setMetaList(metaListNew);
            objetivo = em.merge(objetivo);
            if (codAreaOld != null && !codAreaOld.equals(codAreaNew)) {
                codAreaOld.getObjetivoList().remove(objetivo);
                codAreaOld = em.merge(codAreaOld);
            }
            if (codAreaNew != null && !codAreaNew.equals(codAreaOld)) {
                codAreaNew.getObjetivoList().add(objetivo);
                codAreaNew = em.merge(codAreaNew);
            }
            for (Indicador indicadorListNewIndicador : indicadorListNew) {
                if (!indicadorListOld.contains(indicadorListNewIndicador)) {
                    Objetivo oldCodObjetivoOfIndicadorListNewIndicador = indicadorListNewIndicador.getCodObjetivo();
                    indicadorListNewIndicador.setCodObjetivo(objetivo);
                    indicadorListNewIndicador = em.merge(indicadorListNewIndicador);
                    if (oldCodObjetivoOfIndicadorListNewIndicador != null && !oldCodObjetivoOfIndicadorListNewIndicador.equals(objetivo)) {
                        oldCodObjetivoOfIndicadorListNewIndicador.getIndicadorList().remove(indicadorListNewIndicador);
                        oldCodObjetivoOfIndicadorListNewIndicador = em.merge(oldCodObjetivoOfIndicadorListNewIndicador);
                    }
                }
            }
            for (Iniciativa iniciativaListNewIniciativa : iniciativaListNew) {
                if (!iniciativaListOld.contains(iniciativaListNewIniciativa)) {
                    Objetivo oldCodObjetivoOfIniciativaListNewIniciativa = iniciativaListNewIniciativa.getCodObjetivo();
                    iniciativaListNewIniciativa.setCodObjetivo(objetivo);
                    iniciativaListNewIniciativa = em.merge(iniciativaListNewIniciativa);
                    if (oldCodObjetivoOfIniciativaListNewIniciativa != null && !oldCodObjetivoOfIniciativaListNewIniciativa.equals(objetivo)) {
                        oldCodObjetivoOfIniciativaListNewIniciativa.getIniciativaList().remove(iniciativaListNewIniciativa);
                        oldCodObjetivoOfIniciativaListNewIniciativa = em.merge(oldCodObjetivoOfIniciativaListNewIniciativa);
                    }
                }
            }
            for (Meta metaListNewMeta : metaListNew) {
                if (!metaListOld.contains(metaListNewMeta)) {
                    Objetivo oldCodObjetivoOfMetaListNewMeta = metaListNewMeta.getCodObjetivo();
                    metaListNewMeta.setCodObjetivo(objetivo);
                    metaListNewMeta = em.merge(metaListNewMeta);
                    if (oldCodObjetivoOfMetaListNewMeta != null && !oldCodObjetivoOfMetaListNewMeta.equals(objetivo)) {
                        oldCodObjetivoOfMetaListNewMeta.getMetaList().remove(metaListNewMeta);
                        oldCodObjetivoOfMetaListNewMeta = em.merge(oldCodObjetivoOfMetaListNewMeta);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = objetivo.getCodigo();
                if (findObjetivo(id) == null) {
                    throw new NonexistentEntityException("The objetivo with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Objetivo objetivo;
            try {
                objetivo = em.getReference(Objetivo.class, id);
                objetivo.getCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The objetivo with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Indicador> indicadorListOrphanCheck = objetivo.getIndicadorList();
            for (Indicador indicadorListOrphanCheckIndicador : indicadorListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Objetivo (" + objetivo + ") cannot be destroyed since the Indicador " + indicadorListOrphanCheckIndicador + " in its indicadorList field has a non-nullable codObjetivo field.");
            }
            List<Iniciativa> iniciativaListOrphanCheck = objetivo.getIniciativaList();
            for (Iniciativa iniciativaListOrphanCheckIniciativa : iniciativaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Objetivo (" + objetivo + ") cannot be destroyed since the Iniciativa " + iniciativaListOrphanCheckIniciativa + " in its iniciativaList field has a non-nullable codObjetivo field.");
            }
            List<Meta> metaListOrphanCheck = objetivo.getMetaList();
            for (Meta metaListOrphanCheckMeta : metaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Objetivo (" + objetivo + ") cannot be destroyed since the Meta " + metaListOrphanCheckMeta + " in its metaList field has a non-nullable codObjetivo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Area codArea = objetivo.getCodArea();
            if (codArea != null) {
                codArea.getObjetivoList().remove(objetivo);
                codArea = em.merge(codArea);
            }
            em.remove(objetivo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Objetivo> findObjetivoEntities() {
        return findObjetivoEntities(true, -1, -1);
    }

    public List<Objetivo> findObjetivoEntities(int maxResults, int firstResult) {
        return findObjetivoEntities(false, maxResults, firstResult);
    }

    private List<Objetivo> findObjetivoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Objetivo.class));
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

    public Objetivo findObjetivo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Objetivo.class, id);
        } finally {
            em.close();
        }
    }

    public int getObjetivoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Objetivo> rt = cq.from(Objetivo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
