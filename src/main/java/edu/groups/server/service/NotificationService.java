package edu.groups.server.service;

import edu.groups.server.entity.GroupEntity;
import edu.groups.server.entity.Notification;
import edu.groups.server.repository.GroupRepository;
import edu.groups.server.repository.NotificationRepository;
import edu.groups.server.utils.UserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.springframework.util.CollectionUtils.isEmpty;

/**
 * Created by Dawid on 04.12.2017 at 19:30.
 */
@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository repository;
    private final GroupRepository groupRepository;

    public void saveNotification(Notification notification) {
        Optional<GroupEntity> groupEntity = Optional.ofNullable(groupRepository.findByIdAndVisibleIsTrue(notification.getGroupId()));
        groupEntity.ifPresent(group -> saveNotification(notification, group));
    }

    private void saveNotification(Notification notification, GroupEntity groupEntity) {
        Set<String> userNames = Stream.concat(groupEntity.getAdminsUserNames().stream(), groupEntity.getMembersUserNames().stream())
                .filter(username -> !username.equals(notification.getAuthor())).collect(Collectors.toSet());
        notification.getDestinationUsername().addAll(userNames);
        repository.save(notification);
    }

    public Set<Notification> currentUserNotification() {
        return repository.findByDestinationUsername(UserContext.getUsername());
    }

    public void removeNotificationByPostId(Long postId) {
        Set<Notification> notifications = repository.findByPostId(postId);
        remove(notifications);
    }

    public void removeNotificationByCommentId(Long commentId) {
        Set<Notification> notifications = repository.findByCommentId(commentId);
        remove(notifications);
    }

    public void removeNotificationByGroupId(Long groupId) {
        Set<Notification> notifications = repository.findByGroupId(groupId);
        remove(notifications);
    }

    public void removeUsernameFromNotification(Long groupId, Collection<String> username) {
        Set<Notification> notifications = repository.findByGroupId(groupId);

        if (!isEmpty(notifications)) {
            Set<Notification> notifi = notifications.stream()
                    .peek(notification -> notification.getDestinationUsername().removeAll(username))
                    .collect(Collectors.toSet());
            repository.save(notifi);
        }
    }

    private void remove(Set<Notification> notifications) {
        if (!isEmpty(notifications)) {
            repository.delete(notifications);
        }
    }


}
