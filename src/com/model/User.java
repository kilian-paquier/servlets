package com.model;

import org.hibernate.annotations.DiscriminatorFormula;

import javax.persistence.*;

@MappedSuperclass
@DiscriminatorFormula("'GenericUser'")
public  abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "FirstName",  nullable = false)
    private String firstName;

    @Column(name = "Name",  nullable = false)
    private String lastName;

    @Column(name = "Login",  nullable = false, unique = true)
    private String login;

    @Column(name = "Password",  nullable = false)
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
