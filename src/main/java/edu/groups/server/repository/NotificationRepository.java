package edu.groups.server.repository;

import edu.groups.server.entity.Notification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Created by Dawid on 04.12.2017 at 18:23.
 */
@Repository
public interface NotificationRepository extends CrudRepository<Notification, Long> {
    Set<Notification> findByDestinationUsername(String username);
    Set<Notification> findByGroupId(Long groupId);
    Set<Notification> findByPostId(Long postId);
    Set<Notification> findByCommentId(Long commentId);
}
