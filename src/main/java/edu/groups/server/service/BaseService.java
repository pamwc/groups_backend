package edu.groups.server.service;

import edu.groups.server.entity.GroupEntity;
import edu.groups.server.exception.PermissionDeniedException;
import edu.groups.server.exception.ResourceNotFoundException;
import edu.groups.server.repository.GroupRepository;
import edu.groups.server.utils.UserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static java.util.Optional.ofNullable;

/**
 * Created by Dawid on 17.11.2017 at 13:21.
 */
public class BaseService {
    @Autowired
    protected GroupRepository groupRepository;

    protected void throwExceptionIfUserIsNotGroupAdmin(GroupEntity groupEntity) {
        if (!groupEntity.getAdminsUserNames().contains(UserContext.getUsername())) {
            throw new PermissionDeniedException("User is not group admin");
        }
    }

    protected void throwExceptionIfUserIsNotMemberOfGroup(GroupEntity groupEntity) {
        String currentUserLogin = UserContext.getUsername();
        if (!isUserMemberOfGroup(groupEntity, currentUserLogin)) {
            throw new PermissionDeniedException("User is not member of group");
        }
    }

    private boolean isUserMemberOfGroup(GroupEntity groupEntity, String currentUserLogin) {
        return groupEntity.getAdminsUserNames().contains(currentUserLogin) ||
                groupEntity.getMembersUserNames().contains(currentUserLogin);
    }

    protected GroupEntity getGroupOrThrow(Long groupId) {
        return ofNullable(groupRepository.findByIdAndVisibleIsTrue(groupId)).orElseThrow(ResourceNotFoundException::new);
    }
}
