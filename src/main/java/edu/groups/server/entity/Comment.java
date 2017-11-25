package edu.groups.server.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

/**
 * Created by Dawid on 11.11.2017 at 16:29.
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Comment extends Message {
}
