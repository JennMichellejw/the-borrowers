package com.jennmichelle.theborrowers1.models;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Loan extends AbstractEntity{

    @ManyToOne
    private Borrower borrower;

    @ManyToOne
    private InventoryItem item;

    @ManyToOne
    private User user;

    private int dueDate;
    private int returnDate;
    private String costForUnreturnedItem;
    private String returnCondition;
    private Boolean isActive = true;

    public Loan() {
    }


    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getReturnCondition() {
        return returnCondition;
    }

    public void setReturnCondition(String returnCondition) {
        this.returnCondition = returnCondition;
    }

    public String getCostForUnreturnedItem() {
        return costForUnreturnedItem;
    }

    public void setCostForUnreturnedItem(String costForUnreturnedItem) {
        this.costForUnreturnedItem = costForUnreturnedItem;
    }

    public int getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(int returnDate) {
        this.returnDate = returnDate;
    }

    public int getDueDate() {
        return dueDate;
    }

    public void setDueDate(int dueDate) {
        this.dueDate = dueDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public InventoryItem getItem() {
        return item;
    }

    public void setItem(InventoryItem item) {
        this.item = item;
    }

    public Borrower getBorrower() {
        return borrower;
    }

    public void setBorrower(Borrower borrower) {
        this.borrower = borrower;
    }
}
