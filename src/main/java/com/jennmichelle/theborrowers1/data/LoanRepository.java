package com.jennmichelle.theborrowers1.data;

import com.jennmichelle.theborrowers1.models.Loan;
import org.springframework.data.repository.CrudRepository;

public interface LoanRepository extends CrudRepository<Loan, Integer>{
}
