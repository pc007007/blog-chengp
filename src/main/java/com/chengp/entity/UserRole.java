package com.chengp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

/**
 * Created by pc on 3/4/16.
 */
@Data @NoArgsConstructor @RequiredArgsConstructor(staticName = "of")
@Entity
@Table(name = "user_roles")
public class UserRole {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_role_id", unique = true, nullable = false)
    private Integer userRoleId;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user", nullable = false)
    @JsonBackReference
    private User user;

    @NonNull
    @Column(name = "role", nullable = false, length = 45)
    private String role;

}
