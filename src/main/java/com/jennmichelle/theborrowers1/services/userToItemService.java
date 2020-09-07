package com.jennmichelle.theborrowers1.services;

import com.jennmichelle.theborrowers1.controllers.AuthenticationController;
import com.jennmichelle.theborrowers1.dto.UserItemDTO;
import com.jennmichelle.theborrowers1.models.Borrower;
import com.jennmichelle.theborrowers1.models.InventoryItem;
import com.jennmichelle.theborrowers1.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component
public class userToItemService {


    @Autowired
    AuthenticationController authenticationController;


    // send user and item data to DTO
    public UserItemDTO userToDto(HttpSession session){

        User user = authenticationController.getUserFromSession(session);
        UserItemDTO dto = new UserItemDTO();
        dto.setUser(user);
        dto.setInventoryItemList(user.getUserInventoryList());

        return dto;
    }


    public User getUser(UserItemDTO dto){
        return dto.getUser();
    }

    public void addItemToUserInventory(InventoryItem item, UserItemDTO user){
        User aUser = user.getUser();
        aUser.addItemToUserInventory(item);
    }




}
