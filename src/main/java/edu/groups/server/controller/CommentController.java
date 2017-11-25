package edu.groups.server.controller;

import edu.groups.server.entity.Comment;
import edu.groups.server.service.AndroidPushNotificationService;
import edu.groups.server.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Dawid on 25.11.2017 at 13:21.
 */
@RestController
@RequestMapping("/groups/posts")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final AndroidPushNotificationService pushNotificationService;

    @PostMapping("/{postId}/comments")
    public Long createNewComment(@PathVariable Long postId, @RequestBody String commentContent) {
        Comment comment = commentService.createComment(postId, commentContent);
        pushNotificationService.send(comment.getPost().getGroupEntity().getId(), comment.toNotification());
        return comment.getId();
    }

    @PutMapping("/comments/{commentId}")
    public void editComment(@PathVariable Long commentId, @RequestBody String comment) {
        commentService.editComment(commentId, comment);
    }

    @DeleteMapping("/comments/{commentId")
    public void deleteComment(Long commentId) {
        commentService.deleteComment(commentId);
    }
}
