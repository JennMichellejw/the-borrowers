package com.jennmichelle.theborrowers1.data;


import com.jennmichelle.theborrowers1.models.Borrower;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowerRepository extends CrudRepository<Borrower,Integer> {
}
