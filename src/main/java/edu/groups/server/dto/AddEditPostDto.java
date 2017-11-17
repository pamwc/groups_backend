package edu.groups.server.dto;

import lombok.Getter;

/**
 * Created by Dawid on 16.11.2017 at 23:23.
 */
@Getter
public class AddEditPostDto {
    private String title;
    private String content;
    private boolean commentEnabled;
}
