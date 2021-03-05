package ru.koryakin.dacha2.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class BlogPost {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String title;

    private String content;

    private String imageUrl;

    private LocalDate createDate;

    private String previewText;

    private String urlTitle;

    private String author;

    private LocalDate modifyDate;

    @ElementCollection
    private List<String> categories;
}
