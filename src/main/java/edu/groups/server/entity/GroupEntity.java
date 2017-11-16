package edu.groups.server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Dawid on 11.11.2017 at 14:11.
 */
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class GroupEntity extends BaseEntity {
    @Version
    @JsonIgnore
    private int version;

    private String name;
    private String joinCode;
    private LocalDateTime creationTime = LocalDateTime.now();

    @ElementCollection
    private Set<String> adminsUserNames = new HashSet<>();

    @ElementCollection
    private Set<String> membersUserNames = new HashSet<>();

    @OneToMany()
    private List<Post> posts = new ArrayList<>();

    public GroupEntity(String name) {
        this.name = name;
    }
}
