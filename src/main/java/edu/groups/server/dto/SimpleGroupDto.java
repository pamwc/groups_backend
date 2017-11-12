package edu.groups.server.dto;

import edu.groups.server.entity.GroupEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * Created by Dawid on 11.11.2017 at 20:00.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleGroupDto {
    private Long id;
    private Set<String> adminsUserNames;
    private String name;
    private LocalDateTime creationTime;

    public static SimpleGroupDto valueOf(GroupEntity groupEntity) {
        return new SimpleGroupDto(groupEntity.getId(), groupEntity.getAdminsUserNames(), groupEntity.getName(),
                groupEntity.getCreationTime());
    }
}
