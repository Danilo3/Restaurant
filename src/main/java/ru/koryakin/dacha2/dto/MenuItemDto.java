package ru.koryakin.dacha2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuItemDto {

    private Integer id;
    private String name;
    private String price;
    private String category;
    private String description;
    private String imageUrl;
    private boolean isAvailableForOrder;


}
