package com.chengp.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Email;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

/**
 * Created by pc on 3/2/16.
 */
@Data @NoArgsConstructor @RequiredArgsConstructor(staticName = "of")
@EqualsAndHashCode(exclude = "userRole")
@ToString(exclude = "userRole")
@Entity
@Table(name = "users")
public class User {

    @Id @GeneratedValue(generator = "uuid") @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;

    @NonNull
    @Size(min = 5, max = 25)
    @Column(name = "username", nullable = false, length = 45, unique = true)
    private String username;

    @NonNull
    @Email
    private String email;

    @NonNull
    @Size(min = 6)
    @Column(name = "password", nullable = false, length = 60)
    private String password;

    @NonNull
    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<UserRole> userRole = new HashSet<UserRole>(0);

}
