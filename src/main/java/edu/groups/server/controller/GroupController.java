package edu.groups.server.controller;

import edu.groups.server.dto.CreateGroupRequestDto;
import edu.groups.server.dto.GroupDto;
import edu.groups.server.dto.JoinGroupRequestDto;
import edu.groups.server.dto.SimpleGroupDto;
import edu.groups.server.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * Created by Dawid on 11.11.2017 at 19:53.
 */
@RestController
@RequestMapping("/groups")
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;

    @GetMapping("/my")
    public Set<SimpleGroupDto> getCurrentUserSimpleGroup() {
        return groupService.getCurrentUserGroups();
    }

    @PostMapping("/join")
    public SimpleGroupDto joinCurrentUserToGroup(@RequestBody JoinGroupRequestDto request) {
        return groupService.joinToGroupByCode(request.getCode());
    }

    @PostMapping
    @PreAuthorize("hasRole(T(edu.groups.server.configuration.security.UserRole).ADMIN.name)")
    public SimpleGroupDto create(@RequestBody CreateGroupRequestDto request) {
        return groupService.createGroup(request.getGroupName());
    }

    @PostMapping("/{groupId}/leave")
    public void leaveGroup(@PathVariable Long groupId) {
        groupService.removeCurrentUserFromGroup(groupId);
    }

    @PostMapping("/{groupId}/remove/{userNames}")
    @PreAuthorize("hasRole(T(edu.groups.server.configuration.security.UserRole).ADMIN.name)")
    public void removeUserFromGroup(@PathVariable Long groupId, @PathVariable Set<String> userNames) {
        groupService.removeUserFromGroup(groupId, userNames);
    }

    @PostMapping("/{groupId}/resetJoinCode")
    @PreAuthorize("hasRole(T(edu.groups.server.configuration.security.UserRole).ADMIN.name)")
    public String resetJoinCode(@PathVariable Long groupId) {
        return groupService.resetJoinCode(groupId);
    }

    @GetMapping("/{groupId}")
    public GroupDto getGroup(@PathVariable Long groupId) {
        return groupService.getGroup(groupId);
    }

    @DeleteMapping("/{groupId}")
    @PreAuthorize("hasRole(T(edu.groups.server.configuration.security.UserRole).ADMIN.name)")
    public void deleteGroup(@PathVariable Long groupId) {
        groupService.removeGroup(groupId);
    }

    @PutMapping("/{groupId}")
    public void editGroupName(@PathVariable Long groupId, @RequestBody String groupName) {
        groupService.modifyGroupName(groupId, groupName);
    }

    @GetMapping("/{groupId}/joinCode")
    @PreAuthorize("hasRole(T(edu.groups.server.configuration.security.UserRole).ADMIN.name)")
    public String getJoinCode(@PathVariable Long groupId) {
        return groupService.getJoinCode(groupId);
    }

    @GetMapping("/{groupId}/isUserMember")
    private boolean isUserMemberOfGroup(@PathVariable Long groupId) {
        return groupService.isUserMemberOfGroup(groupId);
    }
}
