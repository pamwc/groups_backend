package edu.groups.server.service;

import edu.groups.server.entity.Message;
import edu.groups.server.entity.Post;
import edu.groups.server.exception.PermissionDeniedException;
import edu.groups.server.exception.ResourceNotFoundException;
import edu.groups.server.repository.PostRepository;
import edu.groups.server.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;

import static java.util.Optional.ofNullable;

/**
 * Created by Dawid on 25.11.2017 at 13:57.
 */
public abstract class MessageService extends BaseService {
    @Autowired
    protected PostRepository postRepository;
    @Autowired
    protected NotificationService notificationService;

    protected void throwExceptionIfUserIsNotAuthor(Message message) {
        String username = UserContext.getUsername();
        if (!message.getAuthorUserName().equals(username)) {
            throw new PermissionDeniedException(username + " is not an author of this post");
        }
    }

    protected Post getPostOrThrow(Long postId) {
        return ofNullable(postRepository.findByIdAndVisibleIsTrue(postId)).orElseThrow(ResourceNotFoundException::new);
    }
}
