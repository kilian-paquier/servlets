package com.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Voter")
@DiscriminatorValue(value = "GenericUser")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Voter extends User{

    private String city;
    private LocalDate birthDate;
    private boolean vote;

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public void setId(int id) {
        super.setId(id);
    }

    @Override
    public String getFirstName() {
        return super.getFirstName();
    }

    @Override
    public void setFirstName(String firstName) {
        super.setFirstName(firstName);
    }

    @Override
    public String getLastName() {
        return super.getLastName();
    }

    @Override
    public void setLastName(String lastName) {
        super.setLastName(lastName);
    }

    @Override
    public String getLogin() {
        return super.getLogin();
    }

    @Override
    public void setLogin(String login) {
        super.setLogin(login);
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public void setPassword(String password) {
        super.setPassword(password);
    }

    @Column(name = "City", nullable = false)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(name = "BirthDate", nullable = false)
    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birth) {
        this.birthDate = birth;
    }

    @Column(name = "vote", nullable = false)
    public boolean hasVoted() {
        return vote;
    }

    public void setVote(boolean vote) {
        this.vote = vote;
    }
}
