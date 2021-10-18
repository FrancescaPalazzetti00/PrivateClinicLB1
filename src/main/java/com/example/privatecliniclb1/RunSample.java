package com.example.privatecliniclb1;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class RunSample {
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("FP_Clinic");

       /* person.getMyAdminCourses().add(course);

        course.getAdministrators().add(person2);

        SessionFactory factory = null;
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            //ProjectV2 project = new ProjectV2("TestPr", "TestPr", new Date());
            //AuthorHib ah = new AuthorHib("name", "sname", 1655, "1233456", 500);
            //session.save(person);
            session.save(course);
            //session.save(company);
            tx.commit();
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        //System.out.println(person.getLogin());*/
    }
}
