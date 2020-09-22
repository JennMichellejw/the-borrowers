package com.jennmichelle.theborrowers1.controllers;


import com.jennmichelle.theborrowers1.data.LoanRepository;
import com.jennmichelle.theborrowers1.dto.UserDTO;
import com.jennmichelle.theborrowers1.models.Borrower;
import com.jennmichelle.theborrowers1.models.InventoryItem;
import com.jennmichelle.theborrowers1.models.Loan;
import com.jennmichelle.theborrowers1.services.LoanServices;
import com.jennmichelle.theborrowers1.services.UserServices;
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
import java.util.List;

@Controller
@RequestMapping("loan")
public class LoanController{

    @Autowired
    LoanRepository loanRepository;

    @Autowired
    LoanServices loanServices;

    @Autowired
    AuthenticationController authenticationController;

    @Autowired
    UserServices userServices;

    @GetMapping
    public String displayAllLoans(HttpSession session, Model model){

        UserDTO user = userServices.userToDto(session);


        model.addAttribute("title", "Loans");
        model.addAttribute("loans" , user.getUserLoanList());

        return "loan/index";
    }

    @GetMapping("add")
    public String displayCreateLoanForm(Model model, HttpSession session){

        List<InventoryItem> items = userServices.userToDto(session).getUserInventoryList();
        List<Borrower> borrowers = userServices.userToDto(session).getUserBorrowerList();

        model.addAttribute("title", "Create Loan");
        model.addAttribute("loan", new Loan());
        model.addAttribute("items", items);
        model.addAttribute("borrowers", borrowers);
        return "loan/add";

    }

    @PostMapping("add")
    public String processCreateLoanForm(HttpSession session, @ModelAttribute @Valid Loan loan,
                                             Errors errors, Model model) {

        UserDTO user = userServices.userToDto(session);

//        if(errors.hasErrors()) {
//            model.addAttribute("title", "Create Loan");
//            model.addAttribute("items", loanServices.getUser(session).getUserInventoryList());
//            model.addAttribute("borrowers", loanServices.getUser(session).getUserBorrowerList());
//            return "loan/add";
//        }

        loan.setUser(userServices.getUser(user));
        userServices.addLoanToLoanList(loan, user);
        loanRepository.save(loan);

        return "redirect:";
    }


}
