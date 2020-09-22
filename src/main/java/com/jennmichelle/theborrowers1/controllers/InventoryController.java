package com.jennmichelle.theborrowers1.controllers;

import com.jennmichelle.theborrowers1.ItemCondition;
import com.jennmichelle.theborrowers1.ItemType;
import com.jennmichelle.theborrowers1.data.InventoryRepository;
import com.jennmichelle.theborrowers1.dto.UserDTO;
import com.jennmichelle.theborrowers1.models.InventoryItem;
import com.jennmichelle.theborrowers1.models.User;
import com.jennmichelle.theborrowers1.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("inventory")
public class InventoryController extends HandlerInterceptorAdapter {

    @Autowired
    InventoryRepository inventoryRepository;


    @Autowired
    UserServices userServices;

    @Autowired
    AuthenticationController authenticationController;


    @GetMapping
    public String displayAllInventory(HttpSession session, Model model){


        UserDTO user = userServices.userToDto(session);

        model.addAttribute("title", "Inventory");
        model.addAttribute("inventory", user.getUserInventoryList());

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

        UserDTO user = userServices.userToDto(session);

        if(errors.hasErrors()) {
            model.addAttribute("title", "Add Inventory");
            model.addAttribute("types", ItemType.values());
            model.addAttribute("conditions", ItemCondition.values());
            return "inventory/add";
        }

        item.setUser(userServices.getUser(user));
        userServices.addItemToUserInventory(item, user);
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
