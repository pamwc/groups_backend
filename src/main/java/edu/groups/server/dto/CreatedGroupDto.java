package edu.groups.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Dawid on 12.11.2017 at 01:22.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatedGroupDto {
    private Long id;
    private String joinCode;
}
