package ru.koryakin.dacha2.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class DeliveryOrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double price;

    @Column(name="quantity")
    private Integer quantity;

    @Column(name = "menu_item_id")
    private Integer menuItemId;

    @Override
    public String toString() {
        return
                "\t{название=" + name  +
                " , цена=" + price +
                " , количество=" + quantity +
                "}\n";
    }
}
