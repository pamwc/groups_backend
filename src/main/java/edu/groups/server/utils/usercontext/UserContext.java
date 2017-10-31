package edu.groups.server.utils.usercontext;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Dawid on 31.10.2017 at 22:14.
 */
public abstract class UserContext {
    public static String getUsername() {
        return getAuthentication().getName();
    }

    public static List<String> getAuthorities() {
        return getAuthentication()
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
    }

    private static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
