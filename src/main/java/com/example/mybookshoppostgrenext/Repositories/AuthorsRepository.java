package com.example.mybookshoppostgrenext.Repositories;

import com.example.mybookshoppostgrenext.data.Authors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuthorsRepository extends JpaRepository<Authors, Integer> {
    @Query("from Authors")
    List<Authors> customAuthorsAll();
}
