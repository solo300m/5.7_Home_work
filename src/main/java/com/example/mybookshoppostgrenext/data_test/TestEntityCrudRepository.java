package com.example.mybookshoppostgrenext.data_test;

import com.example.mybookshoppostgrenext.data_test.TestEntity;
import org.springframework.data.repository.CrudRepository;

public interface TestEntityCrudRepository extends CrudRepository<TestEntity, Long> {
}
