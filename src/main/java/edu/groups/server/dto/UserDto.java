package edu.groups.server.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * Created by Dawid on 31.10.2017 at 22:02.
 */
@Data
@Builder
public class UserDto {
    private String username;
    private String firstName;
    private String surname;
}
