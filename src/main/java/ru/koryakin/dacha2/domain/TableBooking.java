package ru.koryakin.dacha2.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class TableBooking implements TextView {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "phone")
    private String phone;

    @Column(name = "person_count")
    private int personCount;

    @Column(name = "date")
    private String date; // TODO: change type to date with validation of field

    @Column(name = "time")
    private String time; // TODO: change type to time with validation of field

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Override
    public  String toText(){
        return "\t\tБронирование" + "\n" +
                "Email: " + this.email + "\n" +
                "Имя: " + this.name + "\n" +
                "Телефон: " + this.phone + "\n" +
                "Время: " + this.time + "\n" +
                "Дата: " + this.date + "\n" +
                "Количество человек: " + this.personCount + "\n";
    }
}
