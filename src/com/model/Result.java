package com.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Result")
public class Result implements Serializable {
    private static final long serialVersionUID = -2092390836751020965L;

    @Id
    @OneToOne
    @JoinColumn(name = "candidate", referencedColumnName = "id")
    private Candidate candidate;

    @Column(name = "nb_votes", nullable = false)
    private int nbVotes;

    public Result(int nbVotes) {
        this.nbVotes = nbVotes;
    }

    public Result() {

    }

    public int getNbVote() {
        return nbVotes;
    }

    public void setNbVote(int nbVotes) {
        this.nbVotes = nbVotes;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    void incrementVote() {
        this.nbVotes++;
    }
}
