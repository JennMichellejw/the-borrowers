package com.jennmichelle.theborrowers1.services;

import com.jennmichelle.theborrowers1.controllers.AuthenticationController;
import com.jennmichelle.theborrowers1.data.BorrowerRepository;
import com.jennmichelle.theborrowers1.dto.BorrowerDTO;
import com.jennmichelle.theborrowers1.dto.UserItemDTO;
import com.jennmichelle.theborrowers1.models.Borrower;
import com.jennmichelle.theborrowers1.models.InventoryItem;
import com.jennmichelle.theborrowers1.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component
public class BorrowerService {

    @Autowired
    BorrowerRepository borrowerRepository;

    @Autowired
    AuthenticationController authenticationController;

    //send borrower/userinfo to DTO
    public BorrowerDTO borrowerToDTO(HttpSession session){

        User user = authenticationController.getUserFromSession(session);
        BorrowerDTO dto = new BorrowerDTO();
        dto.setUser(user);
        dto.setUserBorrowerList(user.getUserBorrowerList());

        return dto;
    }

    public void addBorrowerToBorrowerList(Borrower borrower, BorrowerDTO user){
        User aUser = user.getUser();
        aUser.addBorrowerToUserBorrowerList(borrower);
    }

    public User getUser(UserItemDTO dto){
        return dto.getUser();
    }




}

