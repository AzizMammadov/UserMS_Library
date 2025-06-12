package com.users.library.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.Set;
import java.util.HashSet;

@ToString
@Getter
@Setter
@Entity
@Table(name = "role_group")
public class RoleGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_grp_seq")
    @SequenceGenerator(name = "role_grp_seq", sequenceName = "role_grp_sequence", allocationSize = 1)
    private Long id;

    @Column(name = "name", nullable = false, unique = true, length = 100)
    private String name;

    @Column(name = "description", length = 255)
    private String description;

    @ManyToMany(mappedBy = "roleGroups", fetch = FetchType.LAZY)
    private Set<Role> roles = new HashSet<>();

    @ManyToMany(mappedBy = "roleGroups", fetch = FetchType.LAZY)
    private Set<User> users = new HashSet<>();


}
