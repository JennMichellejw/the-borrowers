package com.jennmichelle.theborrowers1.services;

import com.jennmichelle.theborrowers1.controllers.AuthenticationController;
import com.jennmichelle.theborrowers1.dto.UserDTO;
import com.jennmichelle.theborrowers1.models.Borrower;
import com.jennmichelle.theborrowers1.models.InventoryItem;
import com.jennmichelle.theborrowers1.models.Loan;
import com.jennmichelle.theborrowers1.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;


@Component
public class UserServices {


    @Autowired
    AuthenticationController authenticationController;


    // send user and item data to DTO
    public UserDTO userToDto(HttpSession session){

        User user = authenticationController.getUserFromSession(session);
        UserDTO dto = new UserDTO();
        dto.setUser(user);
        dto.setUserBorrowerList(user.getUserBorrowerList());
        dto.setUserInventoryList(user.getUserInventoryList());
        dto.setUserLoanList(user.getUserLoanList());

        return dto;
    }


    public User getUser(UserDTO dto){
        return dto.getUser();
    }

    public void addItemToUserInventory(InventoryItem item, UserDTO user){
        User aUser = user.getUser();
        aUser.addItemToUserInventory(item);
    }

    public void addBorrowerToBorrowerList(Borrower borrower, UserDTO user){
        User aUser = user.getUser();
        aUser.addBorrowerToUserBorrowerList(borrower);
    }

    public void addLoanToLoanList(Loan loan, UserDTO user){
        User aUser = user.getUser();
        aUser.addLoantoLoanList(loan);
    }
}
