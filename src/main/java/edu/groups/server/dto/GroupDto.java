package edu.groups.server.dto;

import edu.groups.server.entity.GroupEntity;
import edu.groups.server.utils.CollectionsUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Dawid on 17.11.2017 at 14:15.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GroupDto {
    private Long id;
    private String name;
    private Date creationTime;
    private Set<String> adminsUserNames = new HashSet<>();
    private Set<String> membersUserNames = new HashSet<>();
    private List<PostDto> posts = new ArrayList<>();

    public static GroupDto valueOf(GroupEntity group) {
        GroupDto groupDto = GroupDto.builder()
                .id(group.getId())
                .name(group.getName())
                .creationTime(group.getCreationTime())
                .adminsUserNames(group.getAdminsUserNames())
                .membersUserNames(group.getMembersUserNames())
                .build();
        CollectionsUtil.mapToVisibleValue(group.getPosts(), PostDto::valueOf).ifPresent(groupDto::setPosts);
        return groupDto;
    }
}
