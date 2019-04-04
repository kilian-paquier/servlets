package com.manager;

import com.dao.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Manager {
    private static SessionFactory sessionFactory;

    private static Session session;

    private static Transaction transaction;

    private static AdminUserDao adminDao;

    private static CandidateUserDao candidateDao;

    private static PartyDao partyDao;

    private static ResultDao resultDao;

    private static VoterDao voterDao;

    public Manager() {
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        sessionFactory = configuration.buildSessionFactory();
        session = sessionFactory.openSession();
        transaction = null;

        adminDao = new AdminUserDao();
        candidateDao = new CandidateUserDao();
        partyDao = new PartyDao();
        resultDao = new ResultDao();
        voterDao = new VoterDao();
    }

    public static void beginTransaction() {
        if (transaction == null)
            transaction = session.beginTransaction();
        else
            transaction = session.getTransaction();
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

    public static AdminUserDao getAdminDao() {
        return adminDao;
    }

    public static CandidateUserDao getCandidateDao() {
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
