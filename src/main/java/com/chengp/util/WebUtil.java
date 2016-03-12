package com.chengp.util;

/**
 * Created by pc on 3/10/16.
 */
public class WebUtil {

    private static String url;

    public static String findURL(Integer urlNumber){

        switch(urlNumber)
        {
            case 1 :
                url = "http://blog.dota2.com/feed";
                break;
            case 2 :
                url = "http://www.gosugamers.net/dota2/news/rss";
                break;
            case 3 :
                url = "http://www.gosugamers.net/dota/news/rss";
                break;
            case 4 :
                url ="http://www.gosugamers.net/news/rss";
                break;
        }

        return url;
    }
}
