package org.example.repository;

import org.example.entity.ArticleNourriture;
import org.example.util.SessionfactorySingleton;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import java.util.List;

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

    public void updateNourriture(ArticleNourriture articleNourriture){
        try{
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.update(articleNourriture);
            session.getTransaction().commit();
        }catch (Exception ex){
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
    }

    public List<ArticleNourriture> findAll (){
        Session session = null;
        try {
            session = sessionFactory.openSession();

            String hql = "SELECT a FROM ArticleNourriture a ";
            Query query = session.createQuery(hql, ArticleNourriture.class);
            List<ArticleNourriture> articleNourritures = query.getResultList();

            for (ArticleNourriture articleNourriture : articleNourritures) {
                System.out.println(articleNourriture);
            }

            return articleNourritures;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
