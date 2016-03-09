package com.chengp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by pc on 3/6/16.
 */
@Controller
public class ProxyController {

/*    private HttpURLConnection httpURLConnection;

    @RequestMapping("/IEconDOTA2_570/GetHeroes/V001")
    public
    @ResponseBody String proxy() {

        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL("https://api.steampowered.com/IEconDOTA2_570/GetHeroes/V001/?key=E5A0A27C28A1F857062866825AF10520");
            httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(httpURLConnection.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            httpURLConnection.disconnect();
        }

        return result.toString();
    }*/
}
