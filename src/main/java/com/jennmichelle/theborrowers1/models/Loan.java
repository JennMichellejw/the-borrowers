package com.jennmichelle.theborrowers1.models;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Loan extends AbstractEntity{

    @OneToOne
    private Borrower borrower;
    @OneToOne
    private InventoryItem item;
    @ManyToOne
    private User user;
    private long dueDate;
    private long returnDate;
    private String costForUnreturnedItem;
    private String returnCondition;
    private Boolean isActive = true;



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

    public long getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(long returnDate) {
        this.returnDate = returnDate;
    }

    public long getDueDate() {
        return dueDate;
    }

    public void setDueDate(long dueDate) {
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
