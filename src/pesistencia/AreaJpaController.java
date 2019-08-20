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
import modelo.Objetivo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelo.Area;
import modelo.Usuario;
import pesistencia.exceptions.IllegalOrphanException;
import pesistencia.exceptions.NonexistentEntityException;
import pesistencia.exceptions.PreexistingEntityException;

/**
 *
 * @author Victor
 */
public class AreaJpaController implements Serializable {

    public AreaJpaController() {
        this.emf = Persistence.createEntityManagerFactory("SistemaBSCPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Area area) throws PreexistingEntityException, Exception {
        if (area.getObjetivoList() == null) {
            area.setObjetivoList(new ArrayList<Objetivo>());
        }
        if (area.getUsuarioList() == null) {
            area.setUsuarioList(new ArrayList<Usuario>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Objetivo> attachedObjetivoList = new ArrayList<Objetivo>();
            for (Objetivo objetivoListObjetivoToAttach : area.getObjetivoList()) {
                objetivoListObjetivoToAttach = em.getReference(objetivoListObjetivoToAttach.getClass(), objetivoListObjetivoToAttach.getCodigo());
                attachedObjetivoList.add(objetivoListObjetivoToAttach);
            }
            area.setObjetivoList(attachedObjetivoList);
            List<Usuario> attachedUsuarioList = new ArrayList<Usuario>();
            for (Usuario usuarioListUsuarioToAttach : area.getUsuarioList()) {
                usuarioListUsuarioToAttach = em.getReference(usuarioListUsuarioToAttach.getClass(), usuarioListUsuarioToAttach.getDi());
                attachedUsuarioList.add(usuarioListUsuarioToAttach);
            }
            area.setUsuarioList(attachedUsuarioList);
            em.persist(area);
            for (Objetivo objetivoListObjetivo : area.getObjetivoList()) {
                Area oldCodAreaOfObjetivoListObjetivo = objetivoListObjetivo.getCodArea();
                objetivoListObjetivo.setCodArea(area);
                objetivoListObjetivo = em.merge(objetivoListObjetivo);
                if (oldCodAreaOfObjetivoListObjetivo != null) {
                    oldCodAreaOfObjetivoListObjetivo.getObjetivoList().remove(objetivoListObjetivo);
                    oldCodAreaOfObjetivoListObjetivo = em.merge(oldCodAreaOfObjetivoListObjetivo);
                }
            }
            for (Usuario usuarioListUsuario : area.getUsuarioList()) {
                Area oldCodAreaOfUsuarioListUsuario = usuarioListUsuario.getCodArea();
                usuarioListUsuario.setCodArea(area);
                usuarioListUsuario = em.merge(usuarioListUsuario);
                if (oldCodAreaOfUsuarioListUsuario != null) {
                    oldCodAreaOfUsuarioListUsuario.getUsuarioList().remove(usuarioListUsuario);
                    oldCodAreaOfUsuarioListUsuario = em.merge(oldCodAreaOfUsuarioListUsuario);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findArea(area.getCodigo()) != null) {
                throw new PreexistingEntityException("Area " + area + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Area area) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Area persistentArea = em.find(Area.class, area.getCodigo());
            List<Objetivo> objetivoListOld = persistentArea.getObjetivoList();
            List<Objetivo> objetivoListNew = area.getObjetivoList();
            List<Usuario> usuarioListOld = persistentArea.getUsuarioList();
            List<Usuario> usuarioListNew = area.getUsuarioList();
            List<String> illegalOrphanMessages = null;
            for (Objetivo objetivoListOldObjetivo : objetivoListOld) {
                if (!objetivoListNew.contains(objetivoListOldObjetivo)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Objetivo " + objetivoListOldObjetivo + " since its codArea field is not nullable.");
                }
            }
            for (Usuario usuarioListOldUsuario : usuarioListOld) {
                if (!usuarioListNew.contains(usuarioListOldUsuario)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Usuario " + usuarioListOldUsuario + " since its codArea field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Objetivo> attachedObjetivoListNew = new ArrayList<Objetivo>();
            for (Objetivo objetivoListNewObjetivoToAttach : objetivoListNew) {
                objetivoListNewObjetivoToAttach = em.getReference(objetivoListNewObjetivoToAttach.getClass(), objetivoListNewObjetivoToAttach.getCodigo());
                attachedObjetivoListNew.add(objetivoListNewObjetivoToAttach);
            }
            objetivoListNew = attachedObjetivoListNew;
            area.setObjetivoList(objetivoListNew);
            List<Usuario> attachedUsuarioListNew = new ArrayList<Usuario>();
            for (Usuario usuarioListNewUsuarioToAttach : usuarioListNew) {
                usuarioListNewUsuarioToAttach = em.getReference(usuarioListNewUsuarioToAttach.getClass(), usuarioListNewUsuarioToAttach.getDi());
                attachedUsuarioListNew.add(usuarioListNewUsuarioToAttach);
            }
            usuarioListNew = attachedUsuarioListNew;
            area.setUsuarioList(usuarioListNew);
            area = em.merge(area);
            for (Objetivo objetivoListNewObjetivo : objetivoListNew) {
                if (!objetivoListOld.contains(objetivoListNewObjetivo)) {
                    Area oldCodAreaOfObjetivoListNewObjetivo = objetivoListNewObjetivo.getCodArea();
                    objetivoListNewObjetivo.setCodArea(area);
                    objetivoListNewObjetivo = em.merge(objetivoListNewObjetivo);
                    if (oldCodAreaOfObjetivoListNewObjetivo != null && !oldCodAreaOfObjetivoListNewObjetivo.equals(area)) {
                        oldCodAreaOfObjetivoListNewObjetivo.getObjetivoList().remove(objetivoListNewObjetivo);
                        oldCodAreaOfObjetivoListNewObjetivo = em.merge(oldCodAreaOfObjetivoListNewObjetivo);
                    }
                }
            }
            for (Usuario usuarioListNewUsuario : usuarioListNew) {
                if (!usuarioListOld.contains(usuarioListNewUsuario)) {
                    Area oldCodAreaOfUsuarioListNewUsuario = usuarioListNewUsuario.getCodArea();
                    usuarioListNewUsuario.setCodArea(area);
                    usuarioListNewUsuario = em.merge(usuarioListNewUsuario);
                    if (oldCodAreaOfUsuarioListNewUsuario != null && !oldCodAreaOfUsuarioListNewUsuario.equals(area)) {
                        oldCodAreaOfUsuarioListNewUsuario.getUsuarioList().remove(usuarioListNewUsuario);
                        oldCodAreaOfUsuarioListNewUsuario = em.merge(oldCodAreaOfUsuarioListNewUsuario);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = area.getCodigo();
                if (findArea(id) == null) {
                    throw new NonexistentEntityException("The area with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Area area;
            try {
                area = em.getReference(Area.class, id);
                area.getCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The area with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Objetivo> objetivoListOrphanCheck = area.getObjetivoList();
            for (Objetivo objetivoListOrphanCheckObjetivo : objetivoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Area (" + area + ") cannot be destroyed since the Objetivo " + objetivoListOrphanCheckObjetivo + " in its objetivoList field has a non-nullable codArea field.");
            }
            List<Usuario> usuarioListOrphanCheck = area.getUsuarioList();
            for (Usuario usuarioListOrphanCheckUsuario : usuarioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Area (" + area + ") cannot be destroyed since the Usuario " + usuarioListOrphanCheckUsuario + " in its usuarioList field has a non-nullable codArea field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(area);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Area> findAreaEntities() {
        return findAreaEntities(true, -1, -1);
    }

    public List<Area> findAreaEntities(int maxResults, int firstResult) {
        return findAreaEntities(false, maxResults, firstResult);
    }

    private List<Area> findAreaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Area.class));
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

    public Area findArea(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Area.class, id);
        } finally {
            em.close();
        }
    }

    public int getAreaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Area> rt = cq.from(Area.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
