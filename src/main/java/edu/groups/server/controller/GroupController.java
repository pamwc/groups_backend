package edu.groups.server.controller;

import edu.groups.server.dto.CreatedGroupDto;
import edu.groups.server.dto.SimpleGroupDto;
import edu.groups.server.entity.GroupEntity;
import edu.groups.server.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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
    public List<SimpleGroupDto> getCurrentUserSimpleGroup() {
        return groupService.getCurrentUserGroups();
    }

    @PostMapping("/join")
    public SimpleGroupDto joinCurrentUserToGroup(@RequestBody String code) {
        return groupService.joinToGroupByCode(code);
    }

    @PostMapping
    @PreAuthorize("hasRole(T(edu.groups.server.configuration.security.UserRole).ADMIN.name)")
    public CreatedGroupDto create(@RequestBody String groupName) {
        return groupService.createGroup(groupName);
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
    public String resetPassword(@PathVariable Long groupId) {
        return groupService.resetJoinCode(groupId);
    }

    @GetMapping("/{groupId}")
    public GroupEntity getGroup(@PathVariable Long groupId) {
        return groupService.getGroup(groupId);
    }

    @DeleteMapping("/{groupId}")
    public void deleteGroup(@PathVariable Long groupId) {
        groupService.removeGroup(groupId);
    }
}
