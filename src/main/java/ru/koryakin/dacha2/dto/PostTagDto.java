package ru.koryakin.dacha2.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
public class PostTagDto {

    private Integer id;
    private String tagName;
    private String urlTitle;
}
