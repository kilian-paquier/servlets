package com.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Candidate")
public class Candidate extends Voter implements Serializable {

    @OneToOne(mappedBy = "candidate")
    private Result result;

    @ManyToOne
    @JoinColumn(name = "party", nullable = false)
    private Party party;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Party getParty() {
        return party;
    }

    public void setParty(Party party) {
        if (this.party != null) {
            this.party.getCandidates().remove(this);
            int nbCandidate = this.party.getNbCandidates();
            nbCandidate--;
            this.party.setNbCandidates(nbCandidate);
        }
        this.party = party;
        this.party.addCandidate(this);
    }

    public void incrementVote() {
        this.result.incrementVote();
    }
}
