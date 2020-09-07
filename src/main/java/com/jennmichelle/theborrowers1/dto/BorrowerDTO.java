package com.jennmichelle.theborrowers1.dto;

import com.jennmichelle.theborrowers1.models.Borrower;
import com.jennmichelle.theborrowers1.models.User;

import java.util.ArrayList;
import java.util.List;

public class BorrowerDTO {

    private List<Borrower> userBorrowerList = new ArrayList<>();
    private User user;

    public List<Borrower> getUserBorrowerList() {
        return userBorrowerList;
    }

    public void setUserBorrowerList(List<Borrower> userBorrowerList) {
        this.userBorrowerList = user.getUserBorrowerList();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
