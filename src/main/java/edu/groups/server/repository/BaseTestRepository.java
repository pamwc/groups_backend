package edu.groups.server.repository;

import edu.groups.server.entity.BaseTest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BaseTestRepository extends CrudRepository<BaseTest, Long> {
    List<BaseTest> findAll();
}
