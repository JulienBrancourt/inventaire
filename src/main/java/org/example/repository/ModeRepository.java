package org.example.repository;

import org.example.entity.ArticleMode;
import org.example.util.SessionfactorySingleton;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import java.util.List;

public class ModeRepository {
    private SessionFactory sessionFactory;
    private Session session;

    public ModeRepository() {

        sessionFactory = SessionfactorySingleton.getSessionFactory();
    }

    public ArticleMode createMode(ArticleMode articleMode) {
        try{
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(articleMode);
            session.getTransaction().commit();
            System.out.println("ça marche");
            return articleMode;
        }catch (Exception ex){
            System.out.println("ça marche pas");
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

    public void updateMode(ArticleMode articleMode){
        try{
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.update(articleMode);
            session.getTransaction().commit();
        }catch (Exception ex){
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
    }

    public List<ArticleMode> findAll (){
        Session session = null;
        try {
            session = sessionFactory.openSession();

            String hql = "SELECT a FROM ArticleMode a ";
            Query query = session.createQuery(hql, ArticleMode.class);
            List<ArticleMode> articleModes = query.getResultList();

            for (ArticleMode articleMode : articleModes) {
                System.out.println(articleMode);
            }

            return articleModes;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
