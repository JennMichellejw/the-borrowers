package com.jennmichelle.theborrowers1.services;

import com.jennmichelle.theborrowers1.data.BorrowerRepository;
import com.jennmichelle.theborrowers1.models.AbstractEntity;
import com.jennmichelle.theborrowers1.models.Borrower;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Component
public class BorrowerService {

    @Autowired
    BorrowerRepository borrowerRepository;


    public enum BorrowerStatus {
        GREAT("Great"),
        GOOD("Good"),
        UNKNOWN("Unknown"),
        BAD("Bad"),
        RED("Red");

        private final String displayName;


        BorrowerStatus(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }

//        //use points from returned loan to update borrower status
//        private static void updateBorrowerStatus(int i, LoanDTO dto){
//            dto.getBorrower().setPoints(dto.getBorrower().getPoints() + i);
//        }
    }

    public List<Borrower> searchBorrower(String searchTerm, List<Borrower> searchList){

        List<Borrower> results = new ArrayList<>();

        //loop over list param
        for(Borrower entity : searchList){
                if(entity.getFirstName().contains(searchTerm) || entity.getLastName().contains(searchTerm)){
                    results.add(entity);
                }
            }
        return results;
    }

//    public void generateSearchedList(String searchTerm,List<>){





}

