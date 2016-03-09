package com.chengp.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by pc on 3/6/16.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "hero")
public class Hero {

    @Id
    private Integer id;

    private String name;

    private String localized_name;

}
