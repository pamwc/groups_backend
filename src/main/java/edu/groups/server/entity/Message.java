package edu.groups.server.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * Created by Dawid on 11.11.2017 at 15:13.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@MappedSuperclass
@NoArgsConstructor
public abstract class Message extends BaseEntity {
    private String authorUserName;
    private Date creationTime = new Date();
    private String content;

    public Message(String authorUserName, String content) {
        this.authorUserName = authorUserName;
        this.content = content;
    }
}
