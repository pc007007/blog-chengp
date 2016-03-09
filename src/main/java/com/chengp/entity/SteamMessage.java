package com.chengp.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Created by pc on 3/6/16.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SteamMessage {

    private SteamResult result;
}
