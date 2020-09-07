package com.jennmichelle.theborrowers1.dto;

import com.jennmichelle.theborrowers1.models.Borrower;
import com.jennmichelle.theborrowers1.models.InventoryItem;
import com.jennmichelle.theborrowers1.models.Loan;
import com.jennmichelle.theborrowers1.models.User;

import java.util.List;

public class LoanDTO {

    private User user;
    private Borrower borrower;
    private InventoryItem item;
    private List<Loan> userLoanList;

//    public LoanDTO(Borrower borrower, InventoryItem inventoryItem) {
//        this.borrower = borrower;
//        this.item = inventoryItem;
//    }

    public Borrower getBorrower() {
        return borrower;
    }

    public void setBorrower(Borrower borrower) {
        this.borrower = borrower;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Loan> getUserLoanList() {
        return userLoanList;
    }

    public void setUserLoanList(List<Loan> userLoanList) {
        this.userLoanList = userLoanList;
    }
}
