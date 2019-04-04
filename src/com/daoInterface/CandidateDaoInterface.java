package com.daoInterface;

import java.io.Serializable;
import java.util.List;

public interface CandidateDaoInterface<T, Id extends Serializable> {

    void update(T entity);
    T findById(Id id);
    T findByLoginAndPassword(String login, String password);
    void delete(T entity);
    List<T> findAll();
    void deleteAll();
    void save(T entity);

}
