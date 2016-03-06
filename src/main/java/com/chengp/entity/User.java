package com.chengp.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Email;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by pc on 3/2/16.
 */
@Data
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "users")
public class User {

    @Id @GeneratedValue(generator = "uuid")
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
}
