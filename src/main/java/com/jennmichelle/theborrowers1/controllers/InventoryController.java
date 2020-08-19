package com.jennmichelle.theborrowers1.controllers;

import com.jennmichelle.theborrowers1.ItemCondition;
import com.jennmichelle.theborrowers1.ItemType;
import com.jennmichelle.theborrowers1.data.InventoryRepository;
import com.jennmichelle.theborrowers1.data.UserRepository;
import com.jennmichelle.theborrowers1.models.InventoryItem;
import com.jennmichelle.theborrowers1.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

        int user = authenticationController.getUserFromSession(session).getId();

        model.addAttribute("title", "Inventory");
        model.addAttribute("inventory", userRepository.findById(user).get().getUserInventoryList());

        return "inventory/index";
    }

    @GetMapping("add")
    public String displayAddInventoyForm(Model model){
        model.addAttribute("title", "Add Inventory");
        model.addAttribute("item", new InventoryItem());
        model.addAttribute("types", ItemType.values());
        model.addAttribute("conditions", ItemCondition.values());
        return "inventory/add";
    }

    @PostMapping("add")
    public String processCreateInventoryForm(HttpSession session, @ModelAttribute @Valid InventoryItem item,
                                             Errors errors, Model model) {

        int user = authenticationController.getUserFromSession(session).getId();

        if(errors.hasErrors()) {
            model.addAttribute("title", "Add Inventory");
            model.addAttribute("types", ItemType.values());
            model.addAttribute("conditions", ItemCondition.values());
            return "inventory/add";
        }

        item.setUser(userRepository.findById(user).get());
        userRepository.findById(user).get().addItemToUserInventory(item);
        inventoryRepository.save(item);

        return "redirect:";
    }

    @GetMapping("detail")
    public String displayItemDetails(@RequestParam Integer itemId, Model model) {

        Optional<InventoryItem> result = inventoryRepository.findById(itemId);

        if (result.isEmpty()) {
            model.addAttribute("title", "Invalid Item ID: " + itemId);
        } else {
            InventoryItem item = result.get();
            model.addAttribute("title", item.getName() + " Details");
            model.addAttribute("item", item);
        }

        return "inventory/itemDetail";
    }

}
