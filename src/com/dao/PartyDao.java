package com.dao;

import com.daoInterface.PartyDaoInterface;
import com.daoInterface.UserDaoInterface;
import com.manager.Manager;
import com.model.Party;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class PartyDao implements PartyDaoInterface<Party,String> {

    public boolean delete(Party entity) {
        try {
            Manager.beginTransaction();
            if (!Manager.getSession().contains(entity))
                Manager.getSession().merge(entity);
            Manager.getSession().delete(entity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    public List<Party> findAll() {
        Manager.beginTransaction();
        List<Party> parties = (List<Party>) Manager.getSession().createQuery("from Party").list();
        Manager.commitTransaction();
        return parties;
    }

    public void deleteAll() {
        Manager.beginTransaction();
        for (Party p : findAll()) {
            if (!Manager.getSession().contains(p))
                Manager.getSession().merge(p);
            Manager.getSession().delete(p);
        }
        Manager.commitTransaction();
    }

    public boolean saveOrUpdate(Party entity) {
        try {
            Manager.beginTransaction();
            Manager.getSession().saveOrUpdate(entity);
            Manager.commitTransaction();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Party findByPartyName(String partyName) {
        Manager.beginTransaction();
        Party party = Manager.getSession().find(Party.class, partyName);
        Manager.commitTransaction();
        return party;
    }
}
