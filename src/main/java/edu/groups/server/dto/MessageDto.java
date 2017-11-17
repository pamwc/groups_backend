package edu.groups.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by Dawid on 17.11.2017 at 14:20.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {
    private Long id;
    private String authorUserName;
    private Date creationTime;
    private String content;
}
