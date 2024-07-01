package org.example.repository;

import org.example.entity.Article;
import org.example.util.SessionfactorySingleton;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ArticleRepository {
    private SessionFactory sessionFactory;
    private Session session;

    public ArticleRepository() {
        sessionFactory = SessionfactorySingleton.getSessionFactory();
        session = sessionFactory.openSession();
    }

    public Article findById (int id ){
        session = sessionFactory.openSession();
        Article client = session.get(Article.class,id);
        session.close();
        return client;
    }
}
