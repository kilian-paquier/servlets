package com.dao;

import com.daoInterface.UserDaoInterface;
import com.manager.Manager;
import com.model.Candidate;
import org.hibernate.query.Query;

import java.util.List;

public class CandidateDao implements UserDaoInterface<Candidate, Integer> {

    public Candidate findById(Integer id) {
        Manager.beginTransaction();
        Candidate candidate = Manager.getSession().find(Candidate.class, id);
        Manager.commitTransaction();
        return candidate;
    }

    public Candidate findByLoginAndPassword(String login, String password) {
        Manager.beginTransaction();
        Query query = Manager.getSession().createQuery("FROM Candidate WHERE login = :login AND password = :password");
        query.setParameter("login", login);
        query.setParameter("password", password);
        Candidate candidate = (Candidate) query.getResultList().get(0);
        Manager.commitTransaction();
        return candidate;
    }

    public void delete(Candidate entity) {
        Manager.beginTransaction();
        if (!Manager.getSession().contains(entity))
            Manager.getSession().merge(entity); // On récupère l'objet s'il n'appartient pas à la session
        Manager.getSession().delete(entity);

        Manager.commitTransaction();
    }

    @SuppressWarnings("unchecked")
    public List<Candidate> findAll() {
        Manager.beginTransaction();
        List<Candidate> candidates = (List<Candidate>) Manager.getSession().createQuery("from Candidate").list();
        Manager.commitTransaction();
        return candidates;
    }

    public void deleteAll() {
        Manager.beginTransaction();
        for (Candidate c : findAll()) {
            if (!Manager.getSession().contains(c))
                Manager.getSession().merge(c);
            Manager.getSession().delete(c);
        }
        Manager.commitTransaction();
    }

    public boolean saveOrUpdate(Candidate entity) {
        try {
            Manager.beginTransaction();
            Manager.getSession().saveOrUpdate(entity);
            Manager.commitTransaction();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
