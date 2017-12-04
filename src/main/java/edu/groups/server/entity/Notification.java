package edu.groups.server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.groups.server.dto.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Dawid on 01.11.2017 at 13:23.
 */
@AllArgsConstructor
@Data
@Builder
@Entity
public class Notification extends BaseEntity {
    private String title;
    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;
    private String author;
    private String content;
    private Long groupId;
    private Long postId;
    private Long commentId;
    @ElementCollection
    @JsonIgnore
    private Set<String> destinationUsername = new HashSet<>();
}
