package ru.koryakin.dacha2.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Entity
public class UserMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private String phone;

    private String email;

    private String message;

    public  String toText(){
        return "Email: " + this.getEmail() + "\n" +
                "Имя: " + this.getName() + "\n" +
                "Телефон: " + this.getPhone() + "\n" +
                "Сообщение: " + this.getMessage() + "\n";
    }
}
