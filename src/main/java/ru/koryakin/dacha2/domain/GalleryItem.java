package ru.koryakin.dacha2.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class GalleryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "is_published")
    private boolean isPublished = false;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "category")
    private String category;
}
