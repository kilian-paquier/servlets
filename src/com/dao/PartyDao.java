package com.dao;

import com.daoInterface.PartyDaoInterface;
import com.model.Party;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class PartyDao implements PartyDaoInterface<Party,String> {

    private Session currentSession;

    private Transaction currentTransaction;

    public PartyDao() {
    }

    public Session openCurrentSession() {
        currentSession = getSessionFactory().openSession();
        return currentSession;
    }

    public Session openCurrentSessionWithTransaction() {
        currentSession = getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    public void closeCurrentSession() {
        currentSession.close();
    }

    public void closeCurrentSessionWithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }

    private static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration().configure();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());
        return configuration.buildSessionFactory(builder.build());
    }

    public Session getCurrentSession() {
        return currentSession;
    }

    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }

    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }

    public void setCurrentTransaction(Transaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }


    @Override
    public void update(Party entity) {
        getCurrentSession().update(entity);
    }

    @Override
    public Party findById(String s) {
        return getCurrentSession().get(Party.class, s);
    }

    @Override
    public void delete(Party entity) {
        getCurrentSession().delete(entity);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Party> findAll() {
        return (List<Party>) getCurrentSession().createQuery("from Party").list();
    }

    @Override
    public void deleteAll() {
        for (Party p : findAll())
            delete(p);
    }

    @Override
    public void save(Party entity) {
        getCurrentSession().save(entity);
    }
}
