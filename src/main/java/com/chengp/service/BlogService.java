package com.chengp.service;

import com.chengp.entity.Blog;
import com.chengp.entity.Item;
import com.chengp.entity.User;
import com.chengp.repository.BlogRepository;
import com.chengp.repository.ItemRepository;
import com.chengp.repository.UserRepository;
import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by pc on 3/8/16.
 */
@Service
@Transactional
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private UserRepository userRepository;

    public void save(Blog blog) {
        blogRepository.save(blog);
    }

    public List<Blog> findAll(String username) {

        Optional<User> user = userRepository.findByUsername(username);

        return blogRepository.findAllByUser(user.get());
    }

    public void delete(Integer urlNumber, User user){

        blogRepository.deleteByUrlAndUser(urlNumber,user);
    }

    public Optional<Blog> findFirstOne(String username) {

        return blogRepository.findFirstByUser_Username(username);
    }


    public Optional<Blog> findOne(String username, Integer id) {

        return blogRepository.findByIdAndUser_Username(id, username);
    }
}
