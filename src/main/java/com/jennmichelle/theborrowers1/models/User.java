package com.jennmichelle.theborrowers1.models;

import com.sun.istack.NotNull;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User extends AbstractEntity {

    @NotNull
    private String username;

    @NotNull
    private String pwHash;

    @OneToMany(mappedBy = "user")
    private List<InventoryItem> userInventoryList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Borrower> userBorrowerList = new ArrayList<>();

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public User() {}


    public User(String username, String password) {
        this.username = username;
        this.pwHash = encoder.encode(password);
    }

    public boolean isMatchingPassword(String password) {
        return encoder.matches(password, pwHash);
    }

    public String getUsername() {
        return username;
    }

    public void setUserInventoryList(List<InventoryItem> userInventoryList) {
        this.userInventoryList = userInventoryList;
    }

    public List<InventoryItem> getUserInventoryList() {
        return userInventoryList;
    }

    public void addItemToUserInventory(InventoryItem item){
        userInventoryList.add(item);
    }

    public List<Borrower> getUserBorrowerList() {
        return userBorrowerList;
    }

    public void addBorrowerToUserBorrowerList(Borrower borrower) {
        userBorrowerList.add(borrower);
    }
}

