package com.chengp.entity;

import javax.persistence.*;

/**
 * Created by pc on 3/4/16.
 */
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

    public UserRole() {
    }

    public UserRole(User user, String role) {
        this.user = user;
        this.role = role;
    }

    public Integer getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(Integer userRoleId) {
        this.userRoleId = userRoleId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
