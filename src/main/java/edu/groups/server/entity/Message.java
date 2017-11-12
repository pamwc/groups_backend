package edu.groups.server.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * Created by Dawid on 11.11.2017 at 15:13.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@MappedSuperclass
public abstract class Message extends BaseEntity {
    private String authorUserName;
    private LocalDateTime creationTime = LocalDateTime.now();
    private String content;
}
