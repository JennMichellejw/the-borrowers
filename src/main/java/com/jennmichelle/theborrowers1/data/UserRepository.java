package com.jennmichelle.theborrowers1.data;

import com.jennmichelle.theborrowers1.models.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String username);

}
