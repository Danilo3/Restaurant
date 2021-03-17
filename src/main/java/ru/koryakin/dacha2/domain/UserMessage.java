package ru.koryakin.dacha2.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class UserMessage implements TextView {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "message")
    private String message;

    @Override
    public  String toText(){
        return "Email: " + this.email + "\n" +
                "Имя: " + this.name + "\n" +
                "Телефон: " + this.phone + "\n" +
                "Сообщение: " + this.message + "\n";
    }
}
