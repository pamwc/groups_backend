package edu.groups.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by Dawid on 01.11.2017 at 13:23.
 */
@AllArgsConstructor
@Data
public class Notification {
    private String title;
    private String content;
}
