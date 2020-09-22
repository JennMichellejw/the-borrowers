package com.jennmichelle.theborrowers1.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "borrower")
    public List<Loan> borrowerLoanList = new ArrayList<>();

    public String getPhoneNumber() {
        return phoneNumber;
    }


    private int points = 50;
    private String status;


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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
