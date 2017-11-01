package edu.groups.server.repository.mapper;

import edu.groups.server.dto.UserDto;
import edu.groups.server.utils.usercontext.UserContext;
import org.springframework.ldap.core.AttributesMapper;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;

/**
 * Created by Dawid on 31.10.2017 at 22:39.
 */
public class UserDtoMapper implements AttributesMapper<UserDto> {
    @Override
    public UserDto mapFromAttributes(Attributes attributes) throws NamingException {
        return UserDto.builder()
                .firstName((String) attributes.get("cn").get())
                .username((String) attributes.get("uid").get())
                .surname((String) attributes.get("sn").get())
                .build();
    }
}
