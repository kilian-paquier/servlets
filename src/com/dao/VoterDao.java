package com.dao;

import com.daoInterface.VoterDaoInterface;
import com.model.Voter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class VoterDao implements VoterDaoInterface<Voter, Integer> {

    private Session currentSession;
    private Transaction currentTransaction;

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
    public void update(Voter entity) {
        currentSession.update(entity);
    }

    @Override
    public Voter findById(Integer id) {
        return getCurrentSession().get(Voter.class, id);
    }

    @Override
    public Voter findByLoginAndPassword(String login, String password) {
        return (Voter) getCurrentSession().createQuery("from Voter where" +
                "login = " + login + " and password = " + password + "");
    }

    @Override
    public void delete(Voter entity) {
        getCurrentSession().delete(entity);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Voter> findAll() {
        return (List<Voter>) getCurrentSession().createQuery("from Voter").list();
    }

    @Override
    public void deleteAll() {
        for (Voter v : findAll()) {
            delete(v);
        }
    }

    @Override
    public void save(Voter entity) {
        getCurrentSession().save(entity);
    }
}
