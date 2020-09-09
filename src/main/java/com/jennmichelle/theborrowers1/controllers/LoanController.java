package com.jennmichelle.theborrowers1.controllers;


import com.jennmichelle.theborrowers1.ItemCondition;
import com.jennmichelle.theborrowers1.ItemType;
import com.jennmichelle.theborrowers1.data.LoanRepository;
import com.jennmichelle.theborrowers1.dto.BorrowerDTO;
import com.jennmichelle.theborrowers1.dto.LoanDTO;
import com.jennmichelle.theborrowers1.dto.UserItemDTO;
import com.jennmichelle.theborrowers1.models.InventoryItem;
import com.jennmichelle.theborrowers1.models.Loan;
import com.jennmichelle.theborrowers1.models.User;
import com.jennmichelle.theborrowers1.services.LoanServices;
import com.jennmichelle.theborrowers1.services.userToItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("loan")
public class LoanController{

    @Autowired
    LoanRepository loanRepository;

    @Autowired
    LoanServices loanServices;

    @Autowired
    AuthenticationController authenticationController;

    @GetMapping
    public String displayAllLoans(HttpSession session, Model model){

        LoanDTO user = loanServices.loanToDTO(session);
        User aUser = authenticationController.getUserFromSession(session);

        model.addAttribute("title", "Loans");
        model.addAttribute("loans" , aUser.getUserLoanList());

        return "loan/index";
    }

    @GetMapping("add")
    public String displayCreateLoanForm(Model model, HttpSession session){

        LoanDTO user = loanServices.loanToDTO(session);

        model.addAttribute("title", "Create Loan");
        model.addAttribute("loan", new Loan());
        model.addAttribute("items", loanServices.getUser(user).getUserInventoryList());
        model.addAttribute("borrowers", loanServices.getUser(user).getUserBorrowerList());
        return "loan/add";
    }

    @PostMapping("add")
    public String processCreateLoanForm(HttpSession session, @ModelAttribute @Valid Loan loan,
                                             Errors errors, Model model) {

        LoanDTO user = loanServices.loanToDTO(session);

//        if(errors.hasErrors()) {
//            model.addAttribute("title", "Create Loan");
//            model.addAttribute("items", loanServices.getUser(user).getUserInventoryList());
//            model.addAttribute("borrowers", loanServices.getUser(user).getUserBorrowerList());
//            return "loan/add";
//        }

        loan.setUser(loanServices.getUser(user));
        loanServices.addLoanToUsersList(loan, user);
        loanRepository.save(loan);

        return "redirect:";
    }


}
