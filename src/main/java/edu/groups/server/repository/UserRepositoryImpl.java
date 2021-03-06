package edu.groups.server.repository;

import edu.groups.server.dto.UserDto;
import edu.groups.server.repository.mapper.UserDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

/**
 * Created by Dawid on 29.10.2017 at 14:30.
 */
@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final LdapTemplate ldapTemplate;

    @Override
    public List<UserDto> allUsers() {
        return ldapTemplate.search(
                query().where("objectClass").
                        is("person"), new UserDtoMapper());
    }

    @Override
    public Optional<UserDto> userByUsername(String username) {
        return ldapTemplate.search(
                query().where("objectClass")
                        .is("person").and("uid").is(username),
                new UserDtoMapper())
                .stream()
                .findFirst();
    }

    @Override
    public Set<UserDto> userByUsernames(Set<String> username) {
        return username.stream()
                .map(this::userByUsername)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());
    }

    @Override
    public Optional<byte[]> getUserPhoto(String username) {
        return ldapTemplate.search(
                query().where("objectClass")
                        .is("person")
                        .and("uid").is(username), (AttributesMapper<byte[]>) attributes ->
                        (byte[]) attributes.get("jpegPhoto").get()).stream().findFirst();
    }


}
