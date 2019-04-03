package com.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Party")
public class Party {

    private String partyName;
    private String headquarters;
    private int nbCandidates;
    private List<Candidate> candidates;

    @Id
    @Column(name = "partyName", nullable = false, unique = true)
    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    @Column(name = "Headquarters", nullable = false)
    public String getHeadquarters() {
        return headquarters;
    }

    public void setHeadquarters(String headquarters) {
        this.headquarters = headquarters;
    }

    @Column(name = "NbCandidates", nullable = false)
    public int getNbCandidates() {
        return nbCandidates;
    }

    public void setNbCandidates(int nbCandidates) {
        this.nbCandidates = nbCandidates;
    }

    @OneToMany(mappedBy = "party")
    public List<Candidate> getCandidates() {
        return candidates;
    }

    public void setCandidates(List<Candidate> candidates) {
        this.candidates = candidates;
    }
}
