package ru.koryakin.dacha2.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private String price;

    @Column(name = "category")
    private String category;

    @Length(max = 1024)
    @Column(name = "description")
    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "is_available_for_order")
    private boolean isAvailableForOrder = true;


    public MenuItem() {

    }

    public MenuItem(String name, String price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public MenuItem(String line, String category) {
        String[] arr = line.split(":");
        this.name = arr[0];
        this.price = arr[1];
        this.category = category;
    }

}
