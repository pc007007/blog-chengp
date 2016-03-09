package com.chengp.entity;
import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

import java.net.URL;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by pc on 3/8/16.
 */
public class RssRead {

    public static void main(String[] args) {
        try {
            URL feedUrl = new URL("http://blog.dota2.com/feed/");

            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(feedUrl));

            SyndEntryImpl syndEntry = (SyndEntryImpl)feed.getEntries().get(0);
            syndEntry.getTitle();

            SyndContentImpl content = (SyndContentImpl) syndEntry.getContents().get(0);

            System.out.println(syndEntry.getTitle());
            System.out.println(syndEntry.getLink());
            System.out.println(syndEntry.getPublishedDate());
            System.out.println(syndEntry.getDescription().getValue());

            System.out.println(content.getValue());
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("ERROR: " + ex.getMessage());
        }
    }
}


