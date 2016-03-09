package com.chengp.repository;

import com.chengp.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by pc on 3/8/16.
 */
@Repository
public interface BlogRepository extends JpaRepository<Blog,Integer> {

    Optional<Blog> findByTitle(String title);
}
