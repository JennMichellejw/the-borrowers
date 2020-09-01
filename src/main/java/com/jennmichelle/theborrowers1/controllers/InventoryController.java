package com.jennmichelle.theborrowers1.controllers;

import com.jennmichelle.theborrowers1.ItemCondition;
import com.jennmichelle.theborrowers1.ItemType;
import com.jennmichelle.theborrowers1.data.InventoryRepository;
import com.jennmichelle.theborrowers1.data.UserRepository;
import com.jennmichelle.theborrowers1.dto.UserItemDTO;
import com.jennmichelle.theborrowers1.models.InventoryItem;
import com.jennmichelle.theborrowers1.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.jennmichelle.theborrowers1.services.userToItemService;
import com.jennmichelle.theborrowers1.services.userToItemService;

@Controller
@RequestMapping("inventory")
public class InventoryController extends HandlerInterceptorAdapter {

    @Autowired
    InventoryRepository inventoryRepository;

    @Autowired
    userToItemService userToItemService;



    @GetMapping
    public String displayAllInventory(HttpSession session, Model model){


        UserItemDTO user = userToItemService.userToDto(session);

        model.addAttribute("title", "Inventory");
        model.addAttribute("inventory", user.getInventoryItemList());

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

        UserItemDTO user = userToItemService.userToDto(session);

        if(errors.hasErrors()) {
            model.addAttribute("title", "Add Inventory");
            model.addAttribute("types", ItemType.values());
            model.addAttribute("conditions", ItemCondition.values());
            return "inventory/add";
        }

        item.setUser(userToItemService.getUser(user));
        userToItemService.addItemToUserInventory(item, user);
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
