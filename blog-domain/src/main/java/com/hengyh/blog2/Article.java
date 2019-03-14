package com.hengyh.blog2;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Article implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;// 文章标题

    @Lob
    @Column(columnDefinition = "CLOB")
    private String content;// 文章内容

    @Lob
    @Column(columnDefinition = "CLOB")
    private String contentMD;// 文章内容 markdown

    @ManyToMany
    @JoinTable(
            name = "Article_Category",
            joinColumns = {@JoinColumn(name = "fk_article")},
            inverseJoinColumns = {@JoinColumn(name = "fk_category")}
    )
    private Set<Category> category;// 文章类别

    @ManyToOne
    @JoinColumn(name = "fk_author")
    private User author;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;// 创建日期
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateAt;// 更新日期
}
