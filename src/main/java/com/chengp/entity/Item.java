package com.chengp.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by pc on 3/8/16.
 */
@Data
@EqualsAndHashCode(of = "title")
@Entity
@Table(name = "item")
public class Item {

    @Id
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "link")
    private String link;

    @Column(name = "publish_date")
    private Date pubDate;

    @Lob
    @Column(name = "description")
    private String description;

    @Lob
    @Column(name = "content")
    private String content;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "blog_id",nullable = false)
    private Blog blog;
}
