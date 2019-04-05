package com.manager;

import com.dao.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.resource.transaction.spi.TransactionStatus;

public class Manager {
    private static Manager manager = null;

    private static SessionFactory sessionFactory;

    private static Session session;

    private static Transaction transaction;

    private static AdminDao adminDao;

    private static CandidateDao candidateDao;

    private static PartyDao partyDao;

    private static ResultDao resultDao;

    private static VoterDao voterDao;

    private Manager() {
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        sessionFactory = configuration.buildSessionFactory();
        session = sessionFactory.openSession();
        transaction = null;

        adminDao = new AdminDao();
        candidateDao = new CandidateDao();
        partyDao = new PartyDao();
        resultDao = new ResultDao();
        voterDao = new VoterDao();
    }

    public static void beginTransaction() {
        if (transaction == null || !transaction.isActive())
            transaction = session.beginTransaction();

    }

    public static Manager getManager() {
        if (manager == null)
            manager = new Manager();
        return manager;
    }

    public static void commitTransaction() {
        transaction.commit();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static Session getSession() {
        return session;
    }

    public static Transaction getTransaction() {
        return transaction;
    }

    public static AdminDao getAdminDao() {
        return adminDao;
    }

    public static CandidateDao getCandidateDao() {
        return candidateDao;
    }

    public static PartyDao getPartyDao() {
        return partyDao;
    }

    public static ResultDao getResultDao() {
        return resultDao;
    }

    public static VoterDao getVoterDao() {
        return voterDao;
    }
}
