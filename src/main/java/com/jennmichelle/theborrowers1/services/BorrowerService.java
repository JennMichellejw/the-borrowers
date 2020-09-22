package com.jennmichelle.theborrowers1.services;

import com.jennmichelle.theborrowers1.data.BorrowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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


}

