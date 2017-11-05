package edu.groups.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by Dawid on 05.11.2017 at 12:58.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class AuthUser extends UserDto {
    private List<String> roles;

    public AuthUser(UserDto userDto, List<String> roles) {
        super.setUsername(userDto.getUsername());
        super.setSurname(userDto.getSurname());
        super.setFirstName(userDto.getFirstName());
        this.roles = roles;
    }
}
