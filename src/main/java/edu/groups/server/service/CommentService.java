package edu.groups.server.service;

import edu.groups.server.entity.Comment;
import edu.groups.server.entity.Post;
import edu.groups.server.exception.CommentDisabledException;
import edu.groups.server.exception.ResourceNotFoundException;
import edu.groups.server.repository.CommentRepository;
import edu.groups.server.utils.UserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static java.util.Optional.ofNullable;

/**
 * Created by Dawid on 24.11.2017 at 19:21.
 */
@RequiredArgsConstructor
@Service
@Transactional
public class CommentService extends MessageService {
    private final CommentRepository commentRepository;

    public void editComment(Long commentId, String commentContent) {
        Comment comment = getCommentOrThrow(commentId);
        throwExceptionIfUserIsNotAuthor(comment);
        comment.setContent(commentContent);
        commentRepository.save(comment);
    }

    public void deleteComment(Long commentId) {
        Comment comment = getCommentOrThrow(commentId);
        throwExceptionIfUserIsNotAuthor(comment);
        comment.setVisible(false);
        commentRepository.save(comment);
        notificationService.removeNotificationByCommentId(commentId);
    }

    public Comment createComment(Long postId, String commentContent) {
        Post post = getPostOrThrow(postId);
        throwExceptionIfCommentsAreDisabled(post);
        Comment comment = new Comment(UserContext.getUsername(), commentContent);
        comment.setPost(post);
        return commentRepository.save(comment);
    }

    private void throwExceptionIfCommentsAreDisabled(Post post) {
        if (!post.isCommentEnabled()) {
            throw new CommentDisabledException("For post id = " + post.getId() + " comments are disabled");
        }
    }

    public Comment getCommentOrThrow(Long id) {
        return ofNullable(commentRepository.findByIdAndVisibleIsTrue(id)).orElseThrow(ResourceNotFoundException::new);
    }
}
