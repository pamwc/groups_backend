package edu.groups.server.entity;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class BaseTest extends BaseEntity {
    String test;
}
