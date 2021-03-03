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
public class TableBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String phone;

    private int personCount;

    private String date;

    private String time;

    private String name;

    private String email;

    public  String toText(){
        return "\t\tБронирование" + "\n" +
                "Email: " + this.getEmail() + "\n" +
                "Имя: " + this.getName() + "\n" +
                "Телефон: " + this.getPhone() + "\n" +
                "Время: " + this.getTime() + "\n" +
                "Дата: " + this.getDate() + "\n" +
                "Количество человек: " + this.getPersonCount() + "\n";
    }
}
