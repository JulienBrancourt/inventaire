package org.example.repository;

import org.example.entity.ArticleMode;
import org.example.util.SessionfactorySingleton;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ModeRepository {
    private SessionFactory sessionFactory;
    private Session session;

    public ModeRepository() {

        sessionFactory = SessionfactorySingleton.getSessionFactory();
    }

    public ArticleMode createElectronique(ArticleMode articleMode) {
        try{
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(articleMode);
            session.getTransaction().commit();
            return articleMode;
        }catch (Exception ex){
            session.getTransaction().rollback();
            return null;
        }finally {
            session.close();
        }
    }

    public boolean delete(ArticleMode articleMode){
        try{
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.delete(articleMode);
            session.getTransaction().commit();
            return true;
        }catch (Exception ex){
            session.getTransaction().rollback();
            return false;
        }finally {
            session.close();
        }
    }

    public ArticleMode findById (int id ){
        session = sessionFactory.openSession();
        ArticleMode client = session.get(ArticleMode.class,id);
        session.close();
        return client;
    }
}
