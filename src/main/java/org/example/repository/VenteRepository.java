package org.example.repository;

import org.example.entity.Vente;
import org.example.util.SessionfactorySingleton;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import java.util.List;

public class VenteRepository {
    private SessionFactory sessionFactory;
    private Session session;

    public VenteRepository() {
        sessionFactory = SessionfactorySingleton.getSessionFactory();
    }

    public Vente createVente(Vente vente) {
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(vente);
            session.getTransaction().commit();
            return vente;
        } catch (Exception ex) {
            session.getTransaction().rollback();
            return null;
        } finally {
            session.close();
        }
    }

    public boolean delete(Vente vente) {
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.delete(vente);
            session.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            session.getTransaction().rollback();
            return false;
        } finally {
            session.close();
        }
    }

    public Vente findById(int id) {
        session = sessionFactory.openSession();
        Vente vente = session.get(Vente.class, id);
        session.close();
        return vente;
    }

   public List<Vente> findAll() {
        Session session = null;
        try {
            session = sessionFactory.openSession();

            String hql = "SELECT v FROM Vente v ";
            Query query = session.createQuery(hql, Vente.class);
            List<Vente> ventes = query.getResultList();

            //Affichage des résultats
            for (Vente vente : ventes) {
                System.out.println(vente);
            }

            return ventes;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }


}
