package org.example.repository;

import org.example.entity.ArticleElectronique;
import org.example.util.SessionfactorySingleton;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import java.util.List;

public class ElectroniqueRepository {
    private SessionFactory sessionFactory;
    private Session session;

    public ElectroniqueRepository() {
        sessionFactory = SessionfactorySingleton.getSessionFactory();
    }

    public ArticleElectronique createElectronique(ArticleElectronique articleElectronique) {
        try{
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(articleElectronique);
            session.getTransaction().commit();
            return articleElectronique;
        }catch (Exception ex){
            session.getTransaction().rollback();
            return null;
        }finally {
            session.close();
        }
    }

    public boolean delete(ArticleElectronique articleElectronique){
        try{
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.delete(articleElectronique);
            session.getTransaction().commit();
            return true;
        }catch (Exception ex){
            session.getTransaction().rollback();
            return false;
        }finally {
            session.close();
        }
    }

    public ArticleElectronique findById (int id ){
        session = sessionFactory.openSession();
        ArticleElectronique client = session.get(ArticleElectronique.class,id);
        session.close();
        return client;
    }

    public void updateElectronique(ArticleElectronique articleElectronique){
        try{
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.update(articleElectronique);
            session.getTransaction().commit();
        }catch (Exception ex){
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
    }

    public List<ArticleElectronique> findAll (){
        Session session = null;
        try {
            session = sessionFactory.openSession();

            String hql = "SELECT a FROM ArticleElectronique a ";
            Query query = session.createQuery(hql, ArticleElectronique.class);
            List<ArticleElectronique> articleElectroniques = query.getResultList();

            for (ArticleElectronique articleElectronique : articleElectroniques) {
                System.out.println(articleElectronique);
            }

            return articleElectroniques;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
