package ru.koryakin.dacha2.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;
    private String price;
    private String category;
    private String description;


    public MenuItem (String line, String category) {
        String[] arr = line.split(":");
        this.name = arr[0];
        this.price = arr[1];
        this.category = category;
    }

}
