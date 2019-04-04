package com.dao;

import com.daoInterface.UserDaoInterface;
import com.manager.Manager;
import com.model.Voter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

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
        Voter voter = (Voter) Manager.getSession().createQuery("from Voter where" +
                " login = " + login + " and password =" + password + "");
        Manager.commitTransaction();
        return voter;
    }

    public void delete(Voter entity) {
        Manager.beginTransaction();
        if (!Manager.getSession().contains(entity))
            Manager.getSession().merge(entity);
        Manager.getSession().delete(entity);
        Manager.commitTransaction();
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

    public void saveOrUpdate(Voter entity) {
        Manager.beginTransaction();
        Manager.getSession().saveOrUpdate(entity);
        Manager.commitTransaction();
    }
}
