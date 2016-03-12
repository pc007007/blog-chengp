package com.chengp.repository;

import com.chengp.entity.Blog;
import com.chengp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by pc on 3/8/16.
 */
@Repository
public interface BlogRepository extends JpaRepository<Blog,Integer> {

    List<Blog> findAllByUser(User user);

    Optional<Blog> findFirstByUser_Username(String username);

    Optional<Blog> findByIdAndUser_Username(Integer id, String username);

    void deleteByUrlAndUser(Integer urlNumber, User user);
}
