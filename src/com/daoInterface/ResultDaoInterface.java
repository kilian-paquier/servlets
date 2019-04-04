package com.daoInterface;

import java.io.Serializable;

public interface ResultDaoInterface<T, Id extends Serializable> extends DaoInterface<T> {
    T findByCandidate(Id candidate);
}
