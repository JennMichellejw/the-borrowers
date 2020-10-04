package com.jennmichelle.theborrowers1.services;

import com.jennmichelle.theborrowers1.data.InventoryRepository;
import com.jennmichelle.theborrowers1.data.LoanRepository;
import com.jennmichelle.theborrowers1.models.InventoryItem;
import com.jennmichelle.theborrowers1.models.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.List;

@Component
public class LoanServices {


    @Autowired
    InventoryRepository inventoryRepository;

    @Autowired
    LoanRepository loanRepository;


    public void returnLoan(Loan loan){

//        Loan loan = getLoanFromItem(item);
        loan.setActive(false);
        loan.getItem().setOnLoan(false);
        if(!loan.getReturnCondition().equals(loan.getItem().getItemCondition())){
            loan.getItem().setItemCondition(loan.getReturnCondition());
        }
    }

    public Loan getLoanFromItem(InventoryItem item){

        Loan result = null;
        for(Loan loan : loanRepository.findAll()) {
            if (loan.getItem().equals(item) && item.getOnLoan()) {
                result = loan;
            }

        }
        return result;
    }

    public int getLoanFromItemId(int itemId){

        int result = 0;
        for(Loan loan : loanRepository.findAll()) {
            if (loan.getItem().equals(inventoryRepository.findById(itemId)) && loan.getItem().getOnLoan()) {
                result = loan.getId();
            }

        }
        return result;
    }

}

