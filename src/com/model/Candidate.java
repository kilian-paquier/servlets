package com.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "Candidate")
public class Candidate extends Voter implements Serializable {

    private Result result;
    private Party party;

    @Override
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return super.getId();
    }

    @Override
    public void setId(int id) {
        super.setId(id);
    }

    @Override
    @Column(name = "FirstName",  nullable = false)
    public String getFirstName() {
        return super.getFirstName();
    }

    @Override
    public void setFirstName(String firstName) {
        super.setFirstName(firstName);
    }

    @Override
    @Column(name = "Name",  nullable = false)
    public String getLastName() {
        return super.getLastName();
    }

    @Override
    public void setLastName(String lastName) {
        super.setLastName(lastName);
    }

    @Override
    @Column(name = "Login",  nullable = false, unique = true)
    public String getLogin() {
        return super.getLogin();
    }

    @Override
    public void setLogin(String login) {
        super.setLogin(login);
    }

    @Override
    @Column(name = "Password",  nullable = false)
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public void setPassword(String password) {
        super.setPassword(password);
    }

    @Override
    @Column(name = "City", nullable = false)
    public String getCity() {
        return super.getCity();
    }

    public void setCity(String city) {
        super.setCity(city);
    }

    @Override
    @Column(name = "BirthDate", nullable = false)
    public LocalDate getBirthDate() {
        return super.getBirthDate();
    }

    @Override
    public void setBirthDate(LocalDate birth) {
        super.setBirthDate(birth);
    }

    @Override
    @Column(name = "vote", nullable = false)
    public boolean hasVoted() {
        return super.hasVoted();
    }

    @Override
    public void setVote(boolean vote) {
        super.setVote(vote);
    }


    @OneToOne(mappedBy = "candidate")
    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    @ManyToOne
    @JoinColumn(name = "party", nullable = false)
    public Party getParty() {
        return party;
    }

    public void setParty(Party party) {
        this.party = party;
    }
}
