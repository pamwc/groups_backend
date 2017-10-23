package edu.groups.server.controller;

import edu.groups.server.entity.BaseTest;
import edu.groups.server.service.BaseTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController(value = "/baseTest")
public class TestController {
    @Autowired
    private BaseTestService service;

    @GetMapping
    public List<BaseTest> getAll() {
        return service.getAll();
    }

    @PostMapping
    public void create(@RequestBody BaseTest baseTest) {
        service.save(baseTest);
    }
}
