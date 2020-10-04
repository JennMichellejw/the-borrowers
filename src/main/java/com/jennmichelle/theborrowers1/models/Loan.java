package com.jennmichelle.theborrowers1.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class Loan extends AbstractEntity{

    @ManyToOne
    private Borrower borrower;

    @ManyToOne
    private InventoryItem item;

    @ManyToOne
    private User user;

//    @Pattern(regexp = "^((19|20)\\d\\d)-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])$" , message = "Enter valid date")
    private String dueDate;
    private String returnDate;
    private String costForUnreturnedItem;
    private String returnCondition;
    private String returnDetails;
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

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
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

    public String getReturnDetails() {
        return returnDetails;
    }

    public void setReturnDetails(String returnDetails) {
        this.returnDetails = returnDetails;
    }
}
