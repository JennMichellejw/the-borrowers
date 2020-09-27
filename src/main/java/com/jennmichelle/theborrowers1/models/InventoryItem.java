package com.jennmichelle.theborrowers1.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class InventoryItem extends AbstractEntity{

    @NotBlank(message = "item name required")
    @Size(min = 3, max = 255)
    private String name;

    private String description;

    private String itemCondition;

    private String type;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "item")
    private List<Loan> itemLoanList = new ArrayList<>();

    public InventoryItem(){}

    public int getUser() {
        return user.getId();
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getItemCondition() {
        return itemCondition;
    }

    public void setItemCondition(String itemCondition) {
        this.itemCondition = itemCondition;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return name;
    }
}
