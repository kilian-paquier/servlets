package com.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Party")
public class Party {

    @Id
    @Column(name = "party_name", nullable = false, unique = true)
    private String partyName;

    @Column(name = "headquarters", nullable = false)
    private String headquarters;

    @Column(name = "nb_candidates", nullable = false)
    private int nbCandidates = 0;

    @OneToMany(mappedBy = "party")
    private List<Candidate> candidates = new ArrayList<>();

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    public String getHeadquarters() {
        return headquarters;
    }

    public void setHeadquarters(String headquarters) {
        this.headquarters = headquarters;
    }

    public int getNbCandidates() {
        return nbCandidates;
    }

    public void setNbCandidates(int nbCandidates) {
        this.nbCandidates = nbCandidates;
    }

    public List<Candidate> getCandidates() {
        return candidates;
    }

    public void setCandidates(List<Candidate> candidates) {
        this.candidates = candidates;
    }

    void addCandidate(Candidate candidate) {
        this.candidates.add(candidate);
        this.nbCandidates++;
    }
}
