package com.chengp.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by pc on 3/8/16.
 */
@Data
@Entity
@Table(name = "blog")
@EqualsAndHashCode(exclude = "items")
@ToString(exclude = "items")
public class Blog {

    @Id
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "link")
    private String link;

    @Column(name = "publish_date")
    private Date pubDate;

    @OneToMany(mappedBy = "blog")
    private List<Item> items;
}
