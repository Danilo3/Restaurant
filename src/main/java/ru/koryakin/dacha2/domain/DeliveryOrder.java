package ru.koryakin.dacha2.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Entity
public class DeliveryOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Length(max = 1024)
    @Column(name = "comment")
    private String comment;

    @Length(max = 512)
    @Column(name = "address")
    private String address;

    @Column(name = "username")
    private String username;

    @Email(message = "не валидный email")
    @Column(name = "email")
    private String email;

    @Size(min = 2, max = 255)
    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "price")
    private Double price;

    @Column(name = "timeToDeliver")
    private String timeToDeliver;

    @ManyToMany
    List<DeliveryOrderItem> orderItems;

}
