package edu.groups.server.utils;

import java.util.UUID;

/**
 * Created by Dawid on 12.11.2017 at 02:07.
 */
public class GroupCodeGenerator {

    public static final int SUFFIX_LENGTH = 5;

    public static String generate(String oldCode, Long id) {
        String code = id.toString() + UUID.randomUUID().toString().substring(0, SUFFIX_LENGTH);
        if (oldCode != null && code.equals(oldCode)) {
            return generate(oldCode, id);
        } else {
            return code;
        }
    }
}
