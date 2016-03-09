package com.chengp;

import com.chengp.entity.User;
import com.chengp.entity.UserRole;
import com.chengp.repository.UserRepository;
import com.chengp.repository.UserRoleRepository;
import com.chengp.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class BlogChengApplication {

/*    @Bean
    CommandLineRunner init(UserRepository userRepository, UserRoleRepository userRoleRepository,UserService userService) {
        return (evt) -> Arrays.asList(
                "pc123,pc456,pc789".split(","))
                .forEach(
                        a -> {
                            User user = userRepository.save(new User().of(a,"pc@gmail.com","123456",true));
                            userRoleRepository.save(new UserRole().of(user,"USER"));
                        });
    }*/

	public static void main(String[] args) {
		SpringApplication.run(BlogChengApplication.class, args);
	}

}