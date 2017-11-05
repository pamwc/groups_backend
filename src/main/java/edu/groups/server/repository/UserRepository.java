package edu.groups.server.repository;

import edu.groups.server.dto.UserDto;

import java.util.List;
import java.util.Optional;

/**
 * Created by Dawid on 05.11.2017 at 12:08.
 */
public interface UserRepository {
    List<UserDto> allUsers();
    Optional<UserDto> userByUsername(String username);
}
