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

    public void delete(Party entity) {
        Manager.beginTransaction();
        if(!Manager.getSession().contains(entity))
            Manager.getSession().merge(entity);
        Manager.getSession().delete(entity);
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

    public void saveOrUpdate(Party entity) {
        Manager.beginTransaction();
        Manager.getSession().saveOrUpdate(entity);
        Manager.commitTransaction();
    }

    public Party findByPartyName(String partyName) {
        Manager.beginTransaction();
        Party party = Manager.getSession().find(Party.class, partyName);
        Manager.commitTransaction();
        return party;
    }
}
