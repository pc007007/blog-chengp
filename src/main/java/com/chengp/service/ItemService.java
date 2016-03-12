package com.chengp.service;

import com.chengp.entity.Blog;
import com.chengp.entity.Item;
import com.chengp.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by pc on 3/8/16.
 */
@Service
@Transactional
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public List<Item> findItems(Blog blog){

        return itemRepository.findByBlog(blog);
    }
}
