package com.jennmichelle.theborrowers1.services;


import com.jennmichelle.theborrowers1.models.InventoryItem;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ItemServices {

    public List<InventoryItem> filterOnLoan(List<InventoryItem> list){

        List<InventoryItem> notOnLoan = new ArrayList<>();

        List<InventoryItem> onLoan = new ArrayList<>();

        for(InventoryItem item : list){
            if(!item.getOnLoan()){
                notOnLoan.add(item);
            }
        }
        return notOnLoan;
    }
}
