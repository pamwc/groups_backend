package edu.groups.server.service;

import edu.groups.server.entity.BaseTest;
import edu.groups.server.repository.BaseTestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BaseTestService {
    private final BaseTestRepository repository;

    public void save(BaseTest baseTest) {
        repository.save(baseTest);
    }

    public List<BaseTest> getAll() {
        return repository.findAll();
    }
}
