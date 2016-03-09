package com.chengp.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by pc on 3/6/16.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SteamResult {

    private Integer count;

    private Integer status;

    private List<Hero> heroes;
}
