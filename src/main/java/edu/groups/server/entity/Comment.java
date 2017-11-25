package edu.groups.server.entity;

import edu.groups.server.dto.Notification;
import edu.groups.server.dto.NotificationType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

/**
 * Created by Dawid on 11.11.2017 at 16:29.
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
public class Comment extends Message {
    @Version
    private int version;

    @ManyToOne
    private Post post;

    public Comment(String authorUserName, String content) {
        super(authorUserName, content);
    }

    public Notification toNotification() {
        GroupEntity group = post.getGroupEntity();
        return Notification.builder()
                .title(group.getName())
                .notificationType(NotificationType.COMMENT)
                .content(getContent())
                .groupId(group.getId())
                .postId(getPost().getId())
                .commentId(getId())
                .build();
    }
}
