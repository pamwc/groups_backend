package edu.groups.server.service;

import edu.groups.server.annotation.AppService;
import edu.groups.server.configuration.security.UserRole;
import edu.groups.server.dto.GroupDto;
import edu.groups.server.dto.SimpleGroupDto;
import edu.groups.server.entity.GroupEntity;
import edu.groups.server.exception.InvalidJoinCodeException;
import edu.groups.server.utils.GroupCodeGenerator;
import edu.groups.server.utils.UserContext;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * Created by Dawid on 11.11.2017 at 19:57.
 */
@RequiredArgsConstructor
@AppService
@Transactional
public class GroupService extends BaseService {

    public Set<SimpleGroupDto> getCurrentUserGroups() {
        return ofNullable(groupRepository.findAllByMembersUserNamesOrAdminsUserNamesAndVisibleTrue(UserContext
                .getUsername(), UserContext.getUsername()))
                .map(groups -> groups.stream()
                        .map(SimpleGroupDto::valueOf)
                        .collect(Collectors.toSet()))
                .orElse(Collections.emptySet());
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

    public SimpleGroupDto createGroup(String groupName) {
        GroupEntity groupEntity = new GroupEntity(groupName);
        groupEntity.getAdminsUserNames().add(UserContext.getUsername());
        groupRepository.save(groupEntity);
        return SimpleGroupDto.valueOf(groupRepository.save(groupEntity));
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

    private void removeUserFromGroup(Set<String> userName, GroupEntity groupEntity) {
        userName.forEach(user -> {
            groupEntity.getMembersUserNames().remove(user);
            groupEntity.getAdminsUserNames().remove(user);
        });
        groupRepository.save(groupEntity);
    }

    public GroupDto getGroup(Long groupId) {
        GroupEntity groupEntity = getGroupOrThrow(groupId);
        throwExceptionIfUserIsNotMemberOfGroup(groupEntity);
        return GroupDto.valueOf(groupEntity);
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

    public String getJoinCode(Long groupId) {
        GroupEntity groupEntity = getGroupOrThrow(groupId);
        throwExceptionIfUserIsNotGroupAdmin(groupEntity);
        return groupEntity.getJoinCode();
    }

    public boolean isUserMemberOfGroup(Long groupId) {
        GroupEntity group = groupRepository.findByIdAndVisibleIsTrue(groupId);
        return group != null && super.isUserMemberOfGroup(group, UserContext.getUsername());
    }
}
