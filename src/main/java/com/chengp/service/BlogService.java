package com.chengp.service;

import com.chengp.entity.Blog;
import com.chengp.entity.Item;
import com.chengp.repository.BlogRepository;
import com.chengp.repository.ItemRepository;
import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 3/8/16.
 */
@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private ItemRepository itemRepository;

    public Blog loadFeedByURL(String url) {

        Blog blog = new Blog();
        try {
            URL feedUrl = new URL(url);
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(feedUrl));

            blog.setTitle(feed.getTitle());
            blog.setPubDate(feed.getPublishedDate());
            blog.setLink(feed.getLink());

            List<Item> items = new ArrayList<Item>(0);
            for (int i = 0; i < 10; i++) {
                SyndEntryImpl syndEntry = (SyndEntryImpl) feed.getEntries().get(i);

                Item item = new Item();
                item.setId(i+1);
                item.setTitle(syndEntry.getTitle());
                item.setLink(syndEntry.getLink());
                item.setPubDate(syndEntry.getPublishedDate());
                item.setDescription(syndEntry.getDescription().getValue());
                item.setContent(((SyndContentImpl) syndEntry.getContents().get(0)).getValue());
                item.setBlog(blog);

                items.add(item);
/*            List<Item> items = new ArrayList<Item>();
            items.add(item);
            blog.setItems(items);*/

            }

            blog.setItems(items);

            blogRepository.save(blog);
        }catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("ERROR: " + ex.getMessage());
        }

        return blog;
    }


    public Blog findFirstOneByTitle(String title) {
        return blogRepository.findFirstByTitle(title).orElseThrow(()->new RuntimeException("No such blog"));
    }

    public Blog findOne(Integer id) {

        return blogRepository.findOne(id);
    }
}
