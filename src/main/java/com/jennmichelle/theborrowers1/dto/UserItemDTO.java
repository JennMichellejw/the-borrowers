package com.jennmichelle.theborrowers1.dto;

import com.jennmichelle.theborrowers1.controllers.AuthenticationController;
import com.jennmichelle.theborrowers1.data.UserRepository;
import com.jennmichelle.theborrowers1.models.Borrower;
import com.jennmichelle.theborrowers1.models.InventoryItem;
import com.jennmichelle.theborrowers1.models.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Access;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class UserItemDTO {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationController authenticationController;

    private int id;
    private String password;
    private String userName;
    private List<InventoryItem> inventoryItemList = new ArrayList<>();
    private User user;
    private List<Borrower> borrowerList = new ArrayList<>();

    public User getUser() {
        return user;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = user.getUsername();
    }

    public int getId() {
        return getId();
    }

    public void setInventoryItemList(List<InventoryItem> inventoryItemList) {
        this.inventoryItemList = user.getUserInventoryList();
    }


    public List<InventoryItem> getInventoryItemList() {
        return inventoryItemList;
    }
//    public void addItemToUserInventory(InventoryItem item){
//        addItemToUserInventory(item);
//    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setId(int id) {
        this.id = id;
    }

}
