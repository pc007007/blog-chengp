package com.chengp.repository;

import com.chengp.entity.Blog;
import com.chengp.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by pc on 3/8/16.
 */
@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

    List<Item> findByBlog(Blog blog);
}
