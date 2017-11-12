package edu.groups.server.configuration.security;

/**
 * Created by Dawid on 11.11.2017 at 21:10.
 */
public enum UserRole {
    ADMIN("ROLE_TUTOR"),
    STUDENT("ROLE_STUDENT");

    UserRole(String roleName) {
        this.name = roleName;
    }

    public final String name;
}
