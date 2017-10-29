package edu.groups.server.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;

/**
 * Created by Dawid on 29.10.2017 at 14:15.
 */
@Configuration
public class LdapConfig {
    @Value("${ldap.connection.url}")
    public String url;
    @Value("${ldap.connection.base}")
    public String base;

    @Bean
    public ContextSource contextSource() {
        LdapContextSource context = new LdapContextSource();
        context.setUrl(url);
        context.setBase(base);
        context.setAnonymousReadOnly(true);
        return context;
    }

    @Bean
    public LdapTemplate ldapTemplate() {
        return new LdapTemplate(contextSource());
    }
}
