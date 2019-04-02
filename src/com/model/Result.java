package com.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Result")
public class Result implements Serializable {
    private static final long serialVersionUID = -2092390836751020965L;
    private int nbVotes;
    private Candidate candidate;

    @Column(name = "nbVotes", nullable = false)
    public int getNbVote() {
        return nbVotes;
    }

    public void setNbVote(int nbVotes) {
        this.nbVotes = nbVotes;
    }

    @Id
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidate", referencedColumnName = "id")
    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }
}
