package com.example.mybookshoppostgrenext.Repositories;

import com.example.mybookshoppostgrenext.data.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TegsRepository extends JpaRepository<Tag, Integer> {
    public List<Tag> findAll();
    @Query("from Tag where idteg=:id")
    public Tag selectTag(@Param("id") Integer id);
}
