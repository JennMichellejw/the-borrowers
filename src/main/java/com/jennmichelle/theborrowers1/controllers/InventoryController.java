package com.jennmichelle.theborrowers1.controllers;

import com.jennmichelle.theborrowers1.data.InventoryRepository;
import com.jennmichelle.theborrowers1.data.UserRepository;
import com.jennmichelle.theborrowers1.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("inventory")
public class InventoryController {

    @Autowired
    InventoryRepository inventoryRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationController authenticationController;

    @GetMapping
    public String displayAllInventory(HttpSession session, Model model){

        User user = authenticationController.getUserFromSession(session);

        model.addAttribute("title", "Inventory");
        model.addAttribute("inventory", user.getUserInventoryList());

        return "inventory/index";
    }


}
