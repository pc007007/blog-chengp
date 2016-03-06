package com.chengp.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by pc on 3/4/16.
 */
@Data
@Entity
@Table(name = "user_roles")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_role_id", unique = true, nullable = false)
    private Integer userRoleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user", nullable = false)
    private User user;

    @Column(name = "role", nullable = false, length = 45)
    private String role;

}
