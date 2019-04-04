package com.dao;

import com.daoInterface.ResultDaoInterface;
import com.model.Candidate;
import com.model.Result;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ResultDao implements ResultDaoInterface<Result, Candidate> {

    private Session currentSession;

    private Transaction currentTransaction;

    public ResultDao() {
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
    public void update(Result entity) {
        getCurrentSession().update(entity);
    }

    @Override
    public Result findById(Candidate candidate) {
        return getCurrentSession().get(Result.class, candidate);
    }

    @Override
    public void delete(Result entity) {
        getCurrentSession().delete(entity);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Result> findAll() {
        return (List<Result>) getCurrentSession().createQuery("from Result").list();
    }

    @Override
    public void deleteAll() {
        for (Result r : findAll())
            delete(r);
    }

    @Override
    public void save(Result entity) {
        getCurrentSession().save(entity);
    }
}
