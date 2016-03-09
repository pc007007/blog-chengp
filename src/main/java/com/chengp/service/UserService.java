package com.chengp.service;

import com.chengp.entity.User;
import com.chengp.entity.UserRole;
import com.chengp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Created by pc on 3/3/16.
 */
@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    public void save(User user) {

        userRepository.save(user);
    }

    public void setUserRoleAndEnabled(User user,boolean enabled, String... roles) {

        Set<UserRole> userRoles = new HashSet<UserRole>();
        for (String role:roles) {
            UserRole userRole = new UserRole();
            userRole.setUser(user);
            userRole.setRole(role);
            userRoles.add(userRole);
        }
        user.setUserRole(userRoles);
        user.setEnabled(enabled);
    }

    public void setAuth(User user, HttpServletRequest request) {

        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());

        request.getSession();

        token.setDetails(new WebAuthenticationDetails(request));

        Authentication authentication = authenticationManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public Optional<User> findOne(String username) {

        return userRepository.findByUsername(username);
    }

    public boolean findOne(User user){
        return userRepository.findByUsername(user.getUsername()).isPresent();
    }

    public List<User> findAll() {

        return userRepository.findAll();
    }

    public void delete(User user) {
        userRepository.delete(user);
    }
}
