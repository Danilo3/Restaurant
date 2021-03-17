package ru.koryakin.dacha2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GalleryItemDto {

    private Integer id;
    private boolean isPublished;
    private String imageUrl;
    private String category;
}
