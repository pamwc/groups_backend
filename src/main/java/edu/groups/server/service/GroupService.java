package edu.groups.server.service;

import edu.groups.server.configuration.security.UserRole;
import edu.groups.server.dto.CreatedGroupDto;
import edu.groups.server.dto.SimpleGroupDto;
import edu.groups.server.entity.GroupEntity;
import edu.groups.server.exception.InvalidJoinCodeException;
import edu.groups.server.exception.PermissionDeniedException;
import edu.groups.server.repository.GroupRepository;
import edu.groups.server.utils.GroupCodeGenerator;
import edu.groups.server.utils.UserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * Created by Dawid on 11.11.2017 at 19:57.
 */
@Service
@RequiredArgsConstructor
@Transactional
public class GroupService {
    private final GroupRepository groupRepository;

    public List<SimpleGroupDto> getCurrentUserGroups() {
        return ofNullable(groupRepository.findAllByMembersUserNamesOrAdminsUserNamesAndVisibleTrue(UserContext
                .getUsername(), UserContext.getUsername()))
                .map(groups -> groups.stream()
                        .map(SimpleGroupDto::valueOf)
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }

    public SimpleGroupDto joinToGroupByCode(String code) {
        GroupEntity group = ofNullable(groupRepository.findByJoinCodeAndVisibleIsTrue(code))
                .orElseThrow(InvalidJoinCodeException::new);
        joinToGroup(group);
        return SimpleGroupDto.valueOf(group);
    }

    private void joinToGroup(GroupEntity groupEntity) {
        List<String> currentUserAuthorities = UserContext.getAuthorities();
        String username = UserContext.getUsername();
        if (currentUserAuthorities.contains(UserRole.ADMIN.name)) {
            groupEntity.getAdminsUserNames().add(username);
        } else {
            groupEntity.getMembersUserNames().add(username);
        }
        groupRepository.save(groupEntity);
    }

    public CreatedGroupDto createGroup(String groupName) {
        GroupEntity groupEntity = new GroupEntity(groupName);
        groupEntity.getAdminsUserNames().add(UserContext.getUsername());
        groupRepository.save(groupEntity);
        return new CreatedGroupDto(groupEntity.getId(), newJoinCode(groupEntity));
    }

    public String resetJoinCode(Long groupId) {
        GroupEntity groupEntity = getGroupOrThrow(groupId);
        throwExceptionIfUserIsNotGroupAdmin(groupEntity);
        return newJoinCode(groupEntity);
    }

    private String newJoinCode(GroupEntity groupEntity) {
        groupEntity.setJoinCode(GroupCodeGenerator.generate(groupEntity.getJoinCode(), groupEntity.getId()));
        groupRepository.save(groupEntity);
        return groupEntity.getJoinCode();
    }

    public void removeCurrentUserFromGroup(Long groupId) {
        removeUserFromGroup(Collections.singleton(UserContext.getUsername()),
                groupRepository.findByIdAndVisibleIsTrue(groupId));
    }

    public void removeUserFromGroup(Long groupId, Set<String> userName) {
        GroupEntity groupEntity = getGroupOrThrow(groupId);
        throwExceptionIfUserIsNotGroupAdmin(groupEntity);
        removeUserFromGroup(userName, groupEntity);
    }

    private void throwExceptionIfUserIsNotGroupAdmin(GroupEntity groupEntity) {
        if (!groupEntity.getAdminsUserNames().contains(UserContext.getUsername())) {
            throw new PermissionDeniedException("User is not group admin");
        }
    }

    private void removeUserFromGroup(Set<String> userName, GroupEntity groupEntity) {
        userName.forEach(user -> {
            groupEntity.getMembersUserNames().remove(user);
            groupEntity.getAdminsUserNames().remove(user);
        });
        groupRepository.save(groupEntity);
    }

    private GroupEntity getGroupOrThrow(Long groupId) {
        return groupRepository.findByIdAndVisibleIsTrue(groupId);
    }

    public GroupEntity getGroup(Long groupId) {
        GroupEntity groupEntity = groupRepository.findByIdAndVisibleIsTrue(groupId);
        throwExceptionIfUserIsNotMemberOfGroup(groupEntity);
        return groupEntity;
    }

    private void throwExceptionIfUserIsNotMemberOfGroup(GroupEntity groupEntity) {
        String currentUserLogin = UserContext.getUsername();
        if (!isUserMemberOfGroup(groupEntity, currentUserLogin)) {
            throw new PermissionDeniedException("User is not member of group");
        }
    }

    private boolean isUserMemberOfGroup(GroupEntity groupEntity, String currentUserLogin) {
        return groupEntity.getAdminsUserNames().contains(currentUserLogin) ||
                groupEntity.getMembersUserNames().contains(currentUserLogin);
    }

    public void removeGroup(Long groupId) {
        GroupEntity groupEntity = getGroupOrThrow(groupId);
        throwExceptionIfUserIsNotGroupAdmin(groupEntity);
        groupEntity.setVisible(false);
        groupRepository.save(groupEntity);
    }

    public void modifyGroupName(Long groupId, String groupName) {
        GroupEntity groupEntity = getGroupOrThrow(groupId);
        throwExceptionIfUserIsNotGroupAdmin(groupEntity);
        groupEntity.setName(groupName);
        groupRepository.save(groupEntity);
    }
}
