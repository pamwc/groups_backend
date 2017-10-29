package edu.groups.server.controller;

import edu.groups.server.entity.BaseTest;
import edu.groups.server.service.BaseTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/baseTest")
@RequiredArgsConstructor
public class TestController {
    private final BaseTestService service;

    @GetMapping
    public List<BaseTest> getAll() {
        return service.getAll();
    }

    @PostMapping
    public void create(@RequestBody BaseTest baseTest) {
        service.save(baseTest);
    }
}
