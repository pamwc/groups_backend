package edu.groups.server.controller;

import edu.groups.server.dto.Notification;
import edu.groups.server.dto.UserDto;
import edu.groups.server.repository.PersonRepository;
import edu.groups.server.service.AndroidPushNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Dawid on 29.10.2017 at 14:34.
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final PersonRepository repository;
    private final AndroidPushNotificationService pushNotificationService;

    @GetMapping()
    public List<UserDto> allUser() {
        return repository.allUsers();
    }

    @GetMapping("/me")
    public UserDetails me() {
        pushNotificationService.send("foo-bar", new Notification("test", "test"));
        return (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
