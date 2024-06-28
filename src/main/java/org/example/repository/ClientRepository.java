package org.example.repository;

import org.example.entity.Client;
import org.example.util.SessionfactorySingleton;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

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
}
