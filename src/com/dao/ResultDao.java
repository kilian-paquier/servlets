package com.dao;

import com.daoInterface.ResultDaoInterface;
import com.manager.Manager;
import com.model.Candidate;
import com.model.Result;

import java.util.List;

public class ResultDao implements ResultDaoInterface<Result, Candidate> {

    public boolean delete(Result entity) {
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
    public List<Result> findAll() {
        Manager.beginTransaction();
        List<Result> results = (List<Result>) Manager.getSession().createQuery("from Result").list();
        Manager.commitTransaction();
        return results;
    }

    public void deleteAll() {
        Manager.beginTransaction();
        for (Result r : findAll()) {
            if (!Manager.getSession().contains(r))
                Manager.getSession().merge(r);
            Manager.getSession().delete(r);
        }
        Manager.commitTransaction();
    }

    public boolean saveOrUpdate(Result entity) {
        try {
            Manager.beginTransaction();
            Manager.getSession().saveOrUpdate(entity);
            Manager.commitTransaction();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Result findByCandidate(Candidate candidate) {
        Manager.beginTransaction();
        Result result = Manager.getSession().find(Result.class, candidate);
        Manager.commitTransaction();
        return result;
    }
}
