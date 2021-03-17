package ru.koryakin.dacha2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryOrderItemDto {

    private Integer id;
    private String name;
    private Double price;
    private Integer quantity;
    private Integer menuItemId;

}
