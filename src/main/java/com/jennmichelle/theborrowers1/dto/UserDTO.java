package com.jennmichelle.theborrowers1.dto;

import com.jennmichelle.theborrowers1.models.Borrower;
import com.jennmichelle.theborrowers1.models.InventoryItem;
import com.jennmichelle.theborrowers1.models.Loan;
import com.jennmichelle.theborrowers1.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserDTO {

    private String username;

    private User user;

    private List<InventoryItem> userInventoryList = new ArrayList<>();

    private List<Borrower> userBorrowerList = new ArrayList<>();

    private List<Loan> userLoanList = new ArrayList<>();

    public List<Loan> getUserLoanList() {
        return userLoanList;
    }

    public void setUserLoanList(List<Loan> userLoanList) {
        this.userLoanList = userLoanList;
    }

    public List<Borrower> getUserBorrowerList() {
        return userBorrowerList;
    }

    public void setUserBorrowerList(List<Borrower> userBorrowerList) {
        this.userBorrowerList = userBorrowerList;
    }

    public List<InventoryItem> getUserInventoryList() {
        return userInventoryList;
    }

    public void setUserInventoryList(List<InventoryItem> userInventoryList) {
        this.userInventoryList = userInventoryList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
