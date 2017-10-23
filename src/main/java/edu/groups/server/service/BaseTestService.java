package edu.groups.server.service;

import edu.groups.server.entity.BaseTest;
import edu.groups.server.repository.BaseTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaseTestService {
    @Autowired
    private BaseTestRepository repository;

    public void save(BaseTest baseTest) {
        repository.save(baseTest);
    }

    public List<BaseTest> getAll() {
        return repository.findAll();
    }
}
