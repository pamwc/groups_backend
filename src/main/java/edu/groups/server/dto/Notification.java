package edu.groups.server.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Created by Dawid on 01.11.2017 at 13:23.
 */
@AllArgsConstructor
@Data
@Builder
public class Notification {
    private String title;
    private NotificationType notificationType;
    private String content;
    private Long groupId;
    private Long postId;
    private Long commentId;
}
