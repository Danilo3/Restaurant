package ru.koryakin.dacha2.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class BlogPost {

    private Integer id;

    private String title;

    private String content;

    private String imageUrl;

    private Date createDate;

    private Date modifyDate;
}
