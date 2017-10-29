package edu.groups.server.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

/**
 * Created by Dawid on 29.10.2017 at 14:30.
 */
@Repository
@RequiredArgsConstructor
public class PersonRepository {
    private final LdapTemplate ldapTemplate;

    public List<String> allUsers() {
        return ldapTemplate.search(
                query().where("objectClass").
                        is("person"), (AttributesMapper<String>) attributes -> (String) attributes.get("cn").get()
        );
    }
}
