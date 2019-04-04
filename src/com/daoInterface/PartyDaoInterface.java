package com.daoInterface;

import java.io.Serializable;

public interface PartyDaoInterface<T, Id extends Serializable> extends DaoInterface<T> {
    T findByPartyName(Id partyName);
}
