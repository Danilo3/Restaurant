package ru.koryakin.dacha2.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class PostTag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "tag_name")
    private String tagName;

    @Column(name = "url_title")
    private String urlTitle;
}
