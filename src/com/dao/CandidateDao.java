package com.dao;

import com.daoInterface.CandidateDaoInterface;
import com.model.Candidate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class CandidateDao implements CandidateDaoInterface<Candidate, Integer> {

    private Session currentSession;

    private Transaction currentTransaction;

    public CandidateDao() {
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
    public void update(Candidate entity) {
        getCurrentSession().update(entity);
    }

    @Override
    public Candidate findById(Integer id) {
        return (Candidate) getCurrentSession().get(Candidate.class, id);
    }

    @Override
    public Candidate findByLoginAndPassword(String login, String password) {
        return (Candidate) getCurrentSession().createQuery("from Candidate where" +
                "login = "+ login + " and password = "+ password +"");
    }

    @Override
    public void delete(Candidate entity) {
        getCurrentSession().delete(entity);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Candidate> findAll() {
        return (List<Candidate>) getCurrentSession().createQuery("from Candidate").list();
    }

    @Override
    public void deleteAll() {
        for (Candidate c : findAll()) {
            delete(c);
        }
    }

    @Override
    public void save(Candidate entity) {
        getCurrentSession().save(entity);
    }
}
