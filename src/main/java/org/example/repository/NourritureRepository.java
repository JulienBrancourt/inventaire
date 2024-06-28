package org.example.repository;

import org.example.entity.ArticleNourriture;
import org.example.util.SessionfactorySingleton;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class NourritureRepository {
    private SessionFactory sessionFactory;
    private Session session;

    public NourritureRepository() {
        sessionFactory = SessionfactorySingleton.getSessionFactory();
    }

    public ArticleNourriture createNourriture(ArticleNourriture articleNourriture) {
        try{
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(articleNourriture);
            session.getTransaction().commit();
            return articleNourriture;
        }catch (Exception ex){
            session.getTransaction().rollback();
            return null;
        }finally {
            session.close();
        }
    }

    public boolean delete(ArticleNourriture articleNourriture){
        try{
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.delete(articleNourriture);
            session.getTransaction().commit();
            return true;
        }catch (Exception ex){
            session.getTransaction().rollback();
            return false;
        }finally {
            session.close();
        }
    }

    public ArticleNourriture findById (int id ){
        session = sessionFactory.openSession();
        ArticleNourriture client = session.get(ArticleNourriture.class,id);
        session.close();
        return client;
    }
}
