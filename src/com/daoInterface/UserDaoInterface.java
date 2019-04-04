package com.daoInterface;

import java.io.Serializable;

public interface UserDaoInterface<T, Id extends Serializable> extends DaoInterface<T> {
    T findByLoginAndPassword(String login, String password);
    T findById(Id id);
}
