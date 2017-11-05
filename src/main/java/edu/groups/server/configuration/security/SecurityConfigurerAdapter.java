package edu.groups.server.configuration.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.LdapShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by Dawid on 29.10.2017 at 13:45.
 */
@Configuration
public class SecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
    @Value("${ldap.connection.url:a}")
    private String url;
    @Value("${ldap.connection.base}")
    private String base;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .anyRequest().fullyAuthenticated()
                .and()
                .httpBasic();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .ldapAuthentication()
                .userSearchFilter("uid={0}")
                .userDnPatterns("ou=Users," + base)
                .groupSearchBase("ou=Groups")
                .groupSearchFilter("member={0}")
                .contextSource()
                .url(url + base)
                .and()
                .passwordCompare()
                .passwordEncoder(new LdapShaPasswordEncoder())
                .passwordAttribute("userPassword");
    }
}
