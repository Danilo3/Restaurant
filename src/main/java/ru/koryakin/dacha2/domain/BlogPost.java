package ru.koryakin.dacha2.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CollectionType;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
public class BlogPost {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "title")
    private String title;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "content")
    private String content;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "create_date")
    private LocalDate createDate;

    @Column(name = "preview_text")
    private String previewText;

    @Column(name = "url_title")
    private String urlTitle;

    @Column(name = "author")
    private String author;

    @Column(name = "modify_date")
    private LocalDate modifyDate;

    @Column(name = "event_date_time")
    private String eventDateTime;

    @Column(name = "event_background_image_url")
    private String eventBackgroundImageUrl;

    @Column(name = "views_count", columnDefinition = "integer default '0'")
    private Integer viewsCount = 0;

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private PostCategory category;

    @ManyToMany
    private List<PostTag> tags;

}
