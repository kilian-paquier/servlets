package com.model;

import javax.persistence.*;

@Entity
@Table(name = "candidats")
public class Candidate extends Voter{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
