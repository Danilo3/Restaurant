package ru.koryakin.dacha2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.koryakin.dacha2.domain.PostCategory;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogPostDto {

    private Integer id;
    private String title;
    private String content;
    private String imageUrl;
    private LocalDate createDate;
    private String previewText;
    private String urlTitle;
    private String author;
    private LocalDate modifyDate;
    private String eventDateTime;
    private String eventBackgroundImageUrl;
    private Integer viewsCount = 0;
    private PostCategory category;
    private List<PostTagDto> tags;
}
