package com.jennmichelle.theborrowers1.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.*;

@Entity
public class Borrower extends AbstractEntity{

    @NotBlank(message = "Borrower must have a name")
    @Size(min = 3, max = 255)
    private String firstName;

    private String lastName;

    @NotNull
    @Email
    private String email;

    @Size(min = 10, max = 10, message = "enter valid 10 digit phone number")
    private String phoneNumber;

    @ManyToOne
    private User user;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getUser() {
        return user.getId();
    }

    public void setUser(User user) {
        this.user = user;
    }
}
