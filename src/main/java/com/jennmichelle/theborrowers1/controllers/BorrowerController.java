package com.jennmichelle.theborrowers1.controllers;

import com.jennmichelle.theborrowers1.ItemCondition;
import com.jennmichelle.theborrowers1.ItemType;
import com.jennmichelle.theborrowers1.data.BorrowerRepository;
import com.jennmichelle.theborrowers1.data.UserRepository;
import com.jennmichelle.theborrowers1.models.Borrower;
import com.jennmichelle.theborrowers1.models.InventoryItem;
import com.jennmichelle.theborrowers1.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("borrower")
public class BorrowerController {

    @Autowired
    BorrowerRepository borrowerRepository;

    @Autowired
    AuthenticationController authenticationController;

    @Autowired
    UserRepository userRepository;

    @GetMapping
    public String displayAllBorrowers(HttpSession session, Model model){


        int user = authenticationController.getUserFromSession(session).getId();

        model.addAttribute("title", "Borrowers");
        model.addAttribute("borrowers" , userRepository.findById(user).get().getUserBorrowerList());

        return "borrower/index";
    }

    @GetMapping("add")
    public String displayAddBorrowerForm(Model model){
        model.addAttribute("title", "Add Borrower");
        model.addAttribute("borrower", new Borrower());
        return "borrower/add";
    }

    @PostMapping("add")
    public String processCreateBorrowerForm(HttpSession session, @ModelAttribute @Valid Borrower borrower,
                                             Errors errors, Model model) {

        int user = authenticationController.getUserFromSession(session).getId();

        if(errors.hasErrors()) {
            model.addAttribute("title", "Add Borrower");
            return "borrower/add";
        }

        borrower.setUser(userRepository.findById(user).get());
        userRepository.findById(user).get().addBorrowerToUserBorrowerList(borrower);
        borrowerRepository.save(borrower);

        return "redirect:";
    }

    @GetMapping("detail")
    public String displayBorrowerDetails(@RequestParam Integer borrowerId, Model model) {

        Optional<Borrower> result = borrowerRepository.findById(borrowerId);

        if (result.isEmpty()) {
            model.addAttribute("title", "Invalid Item ID: " + borrowerId);
        } else {
            Borrower borrower = result.get();
            model.addAttribute("title", borrower.getFirstName() + "'s Details");
            model.addAttribute("borrower", borrower);
        }

        return "borrower/borrowerDetail";
    }

}
