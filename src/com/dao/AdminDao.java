package com.dao;

import com.daoInterface.AdminDaoInterface;
import com.model.Admin;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class AdminDao implements AdminDaoInterface<Admin, Integer> {

    private Session currentSession;

    private Transaction currentTransaction;

    public AdminDao() {
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
    public void update(Admin entity) {
        getCurrentSession().update(entity);
    }

    @Override
    public Admin findById(Integer id) {
        return (Admin) getCurrentSession().get(Admin.class, id);
    }

    @Override
    public Admin findByLoginAndPassword(String login, String password) {
        return (Admin) getCurrentSession().createQuery("from Admin where" +
                " login = "+ login +" and password =" + password +"");
    }

    @Override
    public void delete(Admin entity) {
        getCurrentSession().delete(entity);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Admin> findAll() {
        return (List<Admin>) getCurrentSession().createQuery("from Admin").list();
    }

    @Override
    public void deleteAll() {
        for (Admin a : findAll()) {
            delete(a);
        }
    }

    @Override
    public void save(Admin entity) {
        getCurrentSession().save(entity);
    }

}
