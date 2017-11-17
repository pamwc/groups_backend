package edu.groups.server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.groups.server.dto.AddEditPostDto;
import javafx.geometry.Pos;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import java.util.List;

/**
 * Created by Dawid on 11.11.2017 at 14:58.
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class Post extends Message {
    private String title;
    @Version
    private int version;

    @ElementCollection
    private List<Comment> comments;
    private boolean commentEnabled = true;

    @ManyToOne
    @JsonIgnore
    private GroupEntity groupEntity;

    public Post(String authorUserName, String content, String title, boolean commentEnabled, GroupEntity groupEntity) {
        super(authorUserName, content);
        this.title = title;
        this.commentEnabled = commentEnabled;
        this.groupEntity = groupEntity;
    }
}
