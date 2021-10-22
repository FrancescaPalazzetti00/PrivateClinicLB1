package com.example.privatecliniclb1.hibernateControllers;

import com.example.privatecliniclb1.ds.Archive;
import com.example.privatecliniclb1.ds.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;


public class ArchiveHibController {
    private EntityManagerFactory emf = null;

    public ArchiveHibController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void createArchive(Archive archive) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(archive);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void editArchive(Archive archive) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.merge(archive);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }


    public List<Archive> getAllArchive() {
        return getAllArchive(true, -1, -1);
    }

    public List<Archive> getAllArchive(boolean all, int resMax, int resFirst) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery query = em.getCriteriaBuilder().createQuery();
            query.select(query.from(Archive.class));
            Query q = em.createQuery(query);

            if (!all) {
                q.setMaxResults(resMax);
                q.setFirstResult(resFirst);
            }

            return q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return null;
    }

    public Archive getArchiveById(int id) {
        EntityManager em = null;
        Archive archive = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            archive = em.getReference(Archive.class, id);
            archive.getId();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("No such course by given Id");
        }
        return archive;
    }

    public void removeArchive(int id) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Archive archive = null;
            try {
                archive = em.getReference(Archive.class, id);
                archive.getId();
            } catch (Exception e) {
                System.out.println("No such user by given Id");
            }
            em.remove(archive);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
