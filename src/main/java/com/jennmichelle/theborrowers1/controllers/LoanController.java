package com.jennmichelle.theborrowers1.controllers;


import com.jennmichelle.theborrowers1.ItemCondition;
import com.jennmichelle.theborrowers1.ItemType;
import com.jennmichelle.theborrowers1.data.BorrowerRepository;
import com.jennmichelle.theborrowers1.data.InventoryRepository;
import com.jennmichelle.theborrowers1.data.LoanRepository;
import com.jennmichelle.theborrowers1.dto.UserDTO;
import com.jennmichelle.theborrowers1.models.Borrower;
import com.jennmichelle.theborrowers1.models.InventoryItem;
import com.jennmichelle.theborrowers1.models.Loan;
import com.jennmichelle.theborrowers1.services.BorrowerService;
import com.jennmichelle.theborrowers1.services.ItemServices;
import com.jennmichelle.theborrowers1.services.LoanServices;
import com.jennmichelle.theborrowers1.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.swing.text.DateFormatter;
import javax.validation.Valid;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
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

    @Autowired
    ItemServices itemServices;

    @GetMapping
    public String displayAllLoans(HttpSession session, Model model){

        UserDTO user = userServices.userToDto(session);


        model.addAttribute("title", "Loans");
        model.addAttribute("loans" , user.getUserLoanList());

        return "loan/index";
    }


    @GetMapping("add")
    public String displayCreateLoanForm(Model model, HttpSession session){

        List<Borrower> borrowers = userServices.userToDto(session).getUserBorrowerList();

        model.addAttribute("title", "Create Loan");
        model.addAttribute("loan", new Loan());
        model.addAttribute("borrowers", borrowers);
        return "loan/add";

    }

    @GetMapping("addLoan")
    public String addLoanNav(Model model, HttpSession session){

        UserDTO user = userServices.userToDto(session);
        model.addAttribute("title", "Which item would you like to loan");
        model.addAttribute("inventory", itemServices.filterOnLoan(user.getUserInventoryList()));

        return "inventory/index";
    }

    @PostMapping("add")
    public String processCreateLoanForm(HttpSession session, @ModelAttribute @Valid Loan loan,
                                        Errors errors, Model model, @RequestParam int itemId,
                                        @RequestParam int borrowerId) {

        UserDTO user = userServices.userToDto(session);


        if(errors.hasErrors()) {
            List<Borrower> borrowers = userServices.userToDto(session).getUserBorrowerList();
            model.addAttribute("title", "Create Loan");
            model.addAttribute("borrowers", borrowers);
            return "loan/add";
        }

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

        inventoryRepository.findById(itemId).get().setOnLoan(true);
        loan.setUser(userServices.getUser(user));
        userServices.addLoanToLoanList(loan, user);
        loanRepository.save(loan);

        return "redirect:";
    }


    @GetMapping("detail")
    public String displayLoanDetails(@RequestParam Integer loanId, Model model) {

        Optional<Loan> result = loanRepository.findById(loanId);

        if (result.isEmpty()) {
            model.addAttribute("title", "Invalid Item ID: " + loanId);
        } else {
            Loan loan = result.get();
            model.addAttribute("title", loan.getItem() + " Details");
            model.addAttribute("loan", loan);
        }

        return "loan/loanDetail";
    }

    @GetMapping("return")
    public String returnLoanForm(Model model, @RequestParam int itemId) {

        Optional<InventoryItem> result = inventoryRepository.findById(itemId);

        if (result.isEmpty()) {
            model.addAttribute("title", "Invalid Item ID: " + itemId);
        } else {
            InventoryItem item = result.get();
            model.addAttribute("item", item);
            model.addAttribute("conditions", ItemCondition.values());
            model.addAttribute("loan", loanServices.getLoanFromItem(item));
            model.addAttribute("title", "return loan for" + item);
            return "loan/return";
        }

        return "redirect:";
    }

    @PostMapping("return")
    public String processLoanReturn(Model model, @RequestParam int itemId, @RequestParam String returnDate, @RequestParam String loanReturnCondition){

        Loan loan = null;
        InventoryItem item = null;

        Optional<InventoryItem> result = inventoryRepository.findById(itemId);

        if (result.isEmpty()) {
            model.addAttribute("title", "Invalid Item ID: " + itemId);
        } else {
            item = result.get();
            loan = loanServices.getLoanFromItem(item);
            loan.setReturnDate(returnDate);
            loan.setReturnCondition(loanReturnCondition);
            loan.setActive(false);
            loan.getItem().setOnLoan(false);
//            if(!loan.getReturnCondition().equals(loan.getItem().getItemCondition())){
//                loan.getItem().setItemCondition(loan.getReturnCondition());
//            }
            return "index";
        }

        return "redirect:";

    }
}
