package com.daoInterface;

import java.io.Serializable;
import java.util.List;

public interface PartyDaoInterface<T, Id extends Serializable> {

    void update(T entity);
    T findById(Id id);
    void delete(T entity);
    List<T> findAll();
    void deleteAll();
    void save(T entity);

}
