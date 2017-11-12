package edu.groups.server.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.List;

/**
 * Created by Dawid on 11.11.2017 at 14:58.
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
public class Post extends Message {
    private String title;

    @ElementCollection
    private List<Comment> comments;
    private boolean commentEnabled = true;
}
