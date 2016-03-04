package com.chengp.service;

import com.chengp.entity.User;
import com.chengp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by pc on 3/3/16.
 */
@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void save(User user) {

        userRepository.save(user);
    }

}
