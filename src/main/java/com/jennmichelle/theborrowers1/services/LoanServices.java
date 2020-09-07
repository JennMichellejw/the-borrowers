package com.jennmichelle.theborrowers1.services;

import com.jennmichelle.theborrowers1.controllers.AuthenticationController;
import com.jennmichelle.theborrowers1.dto.BorrowerDTO;
import com.jennmichelle.theborrowers1.dto.LoanDTO;
import com.jennmichelle.theborrowers1.dto.UserItemDTO;
import com.jennmichelle.theborrowers1.models.Borrower;
import com.jennmichelle.theborrowers1.models.Loan;
import com.jennmichelle.theborrowers1.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Component
public class LoanServices {

    @Autowired
    AuthenticationController authenticationController;

    public LoanDTO loanToDTO(HttpSession session){

        User user = authenticationController.getUserFromSession(session);
        LoanDTO dto = new LoanDTO();
        dto.setUser(user);
        dto.setUserLoanList(user.getUserLoanList());

        return dto;
    }

    public User getUser(LoanDTO dto){
        return dto.getUser();
    }

    public void addLoanToUsersList(Loan loan, LoanDTO user){
        User aUser = user.getUser();
        aUser.addLoantoLoanList(loan);
    }
}
