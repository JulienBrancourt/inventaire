package org.example.repository;

import org.example.entity.Client;
import org.example.util.SessionfactorySingleton;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import java.util.List;

public class ClientRepository {
    private SessionFactory sessionFactory;
    private Session session;

    public ClientRepository() {
        sessionFactory = SessionfactorySingleton.getSessionFactory();
    }

    public Client createClient(Client client) {
        try{
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(client);
            session.getTransaction().commit();
            return client;
        }catch (Exception ex){
            session.getTransaction().rollback();
            return null;
        }finally {
            session.close();
        }
    }

    public boolean delete(Client client){
        try{
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.delete(client);
            session.getTransaction().commit();
            return true;
        }catch (Exception ex){
            session.getTransaction().rollback();
            return false;
        }finally {
            session.close();
        }
    }

    public Client findById (int id ){
        session = sessionFactory.openSession();
        Client client = session.get(Client.class,id);
        session.close();
        return client;
    }

    public void updateClient(Client client){
        try{
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.update(client);
            session.getTransaction().commit();
        }catch (Exception ex){
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
    }

    public List<Client> findAll (){
        Session session = null;
        try {
            session = sessionFactory.openSession();

            String hql = "SELECT c FROM Client c ";
            Query query = session.createQuery(hql, Client.class);
            List<Client> clients = query.getResultList();

            for (Client client : clients) {
                System.out.println(client);
            }

            return clients;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
