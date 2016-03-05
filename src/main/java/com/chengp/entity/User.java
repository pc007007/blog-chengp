package com.chengp.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Email;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by pc on 3/2/16.
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;

    @Size(min = 5, max = 25)
    @Column(name = "username", nullable = false, length = 45, unique = true)
    private String username;

    @Email
    private String email;

    @Size(min = 6)
    @Column(name = "password", nullable = false, length = 60)
    private String password;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user",cascade = CascadeType.PERSIST)
    private Set<UserRole> userRole = new HashSet<UserRole>(0);

    public User() {
    }

    public User(Set<UserRole> userRole, String username, String password, boolean enabled) {
        this.userRole = userRole;
        this.password = password;
        this.enabled = enabled;
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<UserRole> getUserRole() {
        return userRole;
    }

    public void setUserRole(Set<UserRole> userRole) {
        this.userRole = userRole;
    }
}
