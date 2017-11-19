package edu.groups.server.service;

import edu.groups.server.dto.AuthUser;
import edu.groups.server.dto.UserDto;
import edu.groups.server.repository.UserRepository;
import edu.groups.server.utils.UserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Created by Dawid on 05.11.2017 at 12:54.
 */
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public List<UserDto> getAll() {
        return repository.allUsers();
    }

    public Optional<UserDto> getByUsername(String username) {
        return repository.userByUsername(username);
    }

    public Optional<AuthUser> getCurrentUser() {
        Optional<UserDto> user = repository.userByUsername(UserContext.getUsername());
        return user.map(u -> new AuthUser(u, UserContext.getAuthorities()));
    }

    public Set<UserDto> getByUserNames(Set<String> userNames) {
        return repository.userByUsernames(userNames);
    }

    public Optional<byte[]> getUserPhoto(String username) {
        return repository.getUserPhoto(username);
    }
}
