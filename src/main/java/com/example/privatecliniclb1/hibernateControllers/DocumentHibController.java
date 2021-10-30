package com.example.privatecliniclb1.hibernateControllers;

import com.example.privatecliniclb1.ds.Document;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class DocumentHibController {
    private EntityManagerFactory emf = null;

    public DocumentHibController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void createDocument(Document document) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(document);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void editDocument(Document document) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.merge(document);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }


    public List<Document> getAllDocument() {
        return getAllDocument(true, -1, -1);
    }

    public List<Document> getAllDocument(boolean all, int resMax, int resFirst) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery query = em.getCriteriaBuilder().createQuery();
            query.select(query.from(Document.class));
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

    public Document getDocumentById(int id) {
        EntityManager em = null;
        Document document= null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            document = em.getReference(Document.class, id);
            document.getId();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("No such course by given Id");
        }
        return document;
    }

    public void removeDocument(int id) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Document document = null;
            try {
                document = em.getReference(Document.class, id);
                document.getId();
            } catch (Exception e) {
                System.out.println("No such user by given Id");
            }
            em.remove(document);
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

