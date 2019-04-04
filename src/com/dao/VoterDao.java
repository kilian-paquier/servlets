package com.dao;

import com.daoInterface.UserDaoInterface;
import com.manager.Manager;
import com.model.Voter;

import javax.persistence.TypedQuery;
import java.util.List;

public class VoterDao implements UserDaoInterface<Voter, Integer> {

    public Voter findById(Integer id) {
        Manager.beginTransaction();
        Voter voter = Manager.getSession().find(Voter.class, id);
        Manager.commitTransaction();
        return voter;
    }

    public Voter findByLoginAndPassword(String login, String password) {
        Manager.beginTransaction();
        TypedQuery<Voter> query = Manager.getSession().createQuery("FROM Voter WHERE login = :login AND password = :password", Voter.class);
        query.setParameter("login", login);
        query.setParameter("password", password);
        List<Voter> voters = query.getResultList();
        Manager.commitTransaction();
        if (voters.size() == 0)
            return null;
        else
            return voters.get(0);
    }

    public boolean delete(Voter entity) {
        try {
            Manager.beginTransaction();
            if (!Manager.getSession().contains(entity))
                Manager.getSession().merge(entity);
            Manager.getSession().delete(entity);
            Manager.commitTransaction();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    public List<Voter> findAll() {
        Manager.beginTransaction();
        List<Voter> voters = (List<Voter>) Manager.getSession().createQuery("from Voter").list();
        Manager.commitTransaction();
        return voters;
    }

    public void deleteAll() {
        Manager.beginTransaction();
        for (Voter voter : findAll()) {
            if (!Manager.getSession().contains(voter))
                Manager.getSession().merge(voter);
            Manager.getSession().delete(voter);
        }
        Manager.commitTransaction();
    }

    public boolean saveOrUpdate(Voter entity) {
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
