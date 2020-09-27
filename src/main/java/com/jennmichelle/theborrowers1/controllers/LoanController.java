package com.jennmichelle.theborrowers1.controllers;


import com.jennmichelle.theborrowers1.data.BorrowerRepository;
import com.jennmichelle.theborrowers1.data.InventoryRepository;
import com.jennmichelle.theborrowers1.data.LoanRepository;
import com.jennmichelle.theborrowers1.dto.UserDTO;
import com.jennmichelle.theborrowers1.models.AbstractEntity;
import com.jennmichelle.theborrowers1.models.Borrower;
import com.jennmichelle.theborrowers1.models.InventoryItem;
import com.jennmichelle.theborrowers1.models.Loan;
import com.jennmichelle.theborrowers1.services.BorrowerService;
import com.jennmichelle.theborrowers1.services.LoanServices;
import com.jennmichelle.theborrowers1.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("loan")
public class LoanController{

    @Autowired
    LoanRepository loanRepository;

    @Autowired
    LoanServices loanServices;

    @Autowired
    BorrowerService borrowerService;

    @Autowired
    UserServices userServices;

    @Autowired
    InventoryRepository inventoryRepository;

    @Autowired
    BorrowerRepository borrowerRepository;

    @GetMapping
    public String displayAllLoans(HttpSession session, Model model){

        UserDTO user = userServices.userToDto(session);


        model.addAttribute("title", "Loans");
        model.addAttribute("loans" , user.getUserLoanList());

        return "loan/index";
    }

    @GetMapping("add")
    public String displayCreateLoanForm(Model model, HttpSession session){

//        List<InventoryItem> items = userServices.userToDto(session).getUserInventoryList();
        List<Borrower> borrowers = userServices.userToDto(session).getUserBorrowerList();

        model.addAttribute("title", "Create Loan");
        model.addAttribute("loan", new Loan());
//        model.addAttribute("items", items);
        model.addAttribute("borrowers", borrowers);
        return "loan/add";

    }

    @PostMapping("add")
    public String processCreateLoanForm(HttpSession session, @ModelAttribute @Valid Loan loan,
                                             Errors errors, Model model, @RequestParam int itemId, @RequestParam int borrowerId) {

        UserDTO user = userServices.userToDto(session);

        Optional<InventoryItem> itemResult = inventoryRepository.findById(itemId);

        if (itemResult.isEmpty()) {
            model.addAttribute("title", "Invalid Item ID: " + itemId);
        } else {
            InventoryItem item = itemResult.get();
            loan.setItem(item);
        }

        Optional<Borrower> borrowerResult = borrowerRepository.findById(borrowerId);
        if (borrowerResult.isEmpty()) {
            model.addAttribute("title", "Invalid Borrower ID: " + borrowerId);
        } else {
            Borrower borrower = borrowerResult.get();
            loan.setBorrower(borrower);
        }

        loan.setUser(userServices.getUser(user));
        userServices.addLoanToLoanList(loan, user);
        loanRepository.save(loan);

        return "redirect:";
    }


    @RequestMapping(value="/confirmBorrower")
    public String confirmBorrower(Model model, @RequestParam String searchTerm, HttpSession session) {

        model.addAttribute("borrowers", borrowerService.searchBorrower(searchTerm, userServices.userToDto(session).getUserBorrowerList()));
        return "loan/add";
    }


}
