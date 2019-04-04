package com.dao;

import com.daoInterface.UserDaoInterface;
import com.manager.Manager;
import com.model.Candidate;

import java.util.List;

public class CandidateUserDao implements UserDaoInterface<Candidate, Integer> {

    public Candidate findById(Integer id) {
        Manager.beginTransaction();
        Candidate candidate = Manager.getSession().find(Candidate.class, id);
        Manager.commitTransaction();
        return candidate;
    }

    public Candidate findByLoginAndPassword(String login, String password) {
        Manager.beginTransaction();
        Candidate candidate = (Candidate) Manager.getSession().createQuery("from Candidate where" +
                " login = " + login + " and password = " + password);
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

    public void saveOrUpdate(Candidate entity) {
        Manager.beginTransaction();
        Manager.getSession().saveOrUpdate(entity);
        Manager.commitTransaction();
    }
}
