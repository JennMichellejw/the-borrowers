package com.jennmichelle.theborrowers1.controllers;

import com.jennmichelle.theborrowers1.data.BorrowerRepository;
import com.jennmichelle.theborrowers1.dto.UserDTO;
import com.jennmichelle.theborrowers1.models.Borrower;
import com.jennmichelle.theborrowers1.services.BorrowerService;
import com.jennmichelle.theborrowers1.services.UserServices;
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
    BorrowerService borrowerService;

    @Autowired
    UserServices userServices;



    @GetMapping
    public String displayAllBorrowers(HttpSession session, Model model){

        UserDTO user = userServices.userToDto(session);

        model.addAttribute("title", "Borrowers");
        model.addAttribute("borrowers" , user.getUserBorrowerList());

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

        UserDTO  user = userServices.userToDto(session);

        if(errors.hasErrors()) {
            model.addAttribute("title", "Add Borrower");
            return "borrower/add";
        }

        borrower.setUser(user.getUser());
        userServices.addBorrowerToBorrowerList(borrower, user);
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
