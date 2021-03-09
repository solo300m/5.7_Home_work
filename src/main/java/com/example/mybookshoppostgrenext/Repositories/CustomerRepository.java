package com.example.mybookshoppostgrenext.Repositories;

import com.example.mybookshoppostgrenext.data.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<User,Integer> {
    @Query("from User")
    List<User> selectAllCustomers();
}
