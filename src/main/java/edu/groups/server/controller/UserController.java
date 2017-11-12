package edu.groups.server.controller;

import edu.groups.server.dto.AuthUser;
import edu.groups.server.dto.UserDto;
import edu.groups.server.exception.ResourceNotFoundException;
import edu.groups.server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.Set;

/**
 * Created by Dawid on 29.10.2017 at 14:34.
 */
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @GetMapping("/{userNames}")
    public Set<UserDto> allUser(@PathVariable Set<String> userNames) {
        return service.getByUserNames(userNames);
    }

    @GetMapping("/me")
    public AuthUser me() {
        Optional<AuthUser> currentUser = service.getCurrentUser();

        if (currentUser.isPresent()) {
            return currentUser.get();
        }

        throw new ResourceNotFoundException("User does not exist");
    }
}
