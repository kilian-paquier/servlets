package com.singleton;

import com.dao.*;

public class Singleton {

    private static Singleton instance = null;

    public AdminDao adminDao;
    public CandidateDao candidateDao;
    public PartyDao partyDao;
    public ResultDao resultDao;
    public VoterDao voterDao;

    private Singleton() {

    }

    public static Singleton getInstance() {
        if (instance == null)
            instance = new Singleton();
        return instance;
    }



}
