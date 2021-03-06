package com.daoInterface;

import java.util.List;

public interface DaoInterface<T> {
    boolean delete(T entity);
    List<T> findAll();
    void deleteAll();
    boolean saveOrUpdate(T entity);
}
