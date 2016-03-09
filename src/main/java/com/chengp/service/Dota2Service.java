package com.chengp.service;

import com.chengp.entity.Hero;
import com.chengp.entity.SteamMessage;
import com.chengp.repository.HeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by pc on 3/6/16.
 */
@Service
@Transactional
public class Dota2Service {

    private final String key = "E5A0A27C28A1F857062866825AF10520";
    private final String language = "en_us";
    private RestTemplate restTemplate;

    @Autowired
    private HeroRepository heroRepository;

    public List<Hero> getHeroesBySteamAPI() {

        restTemplate = new RestTemplate();
        String url = String
                .format("https://api.steampowered.com/IEconDOTA2_570/GetHeroes/V001/?key=%s&language=%s"
                , key, language);

        SteamMessage steamMessage = restTemplate.getForObject(url, SteamMessage.class);

        List<Hero> heroes = steamMessage.getResult().getHeroes();

        heroes.forEach(h -> h.setName(h.getName().substring(14)));
/*
        heroes.forEach(h -> h.setPic("http://cdn.dota2.com/apps/dota2/images/heroes/"+h.getName()+"_sb.png"));*/
        return heroes;
    }

    public void updateHeroes2DB() {
        this.getHeroesBySteamAPI().forEach(hero -> heroRepository.save(hero));
    }

    public List<Hero> getHeroes() {

        return heroRepository.findAll();
    }
}
