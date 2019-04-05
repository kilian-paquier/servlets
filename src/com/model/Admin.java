package com.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Admin")
@DiscriminatorValue(value = "GenericUser")
public class Admin extends User {

}
