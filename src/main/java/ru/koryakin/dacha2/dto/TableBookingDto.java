package ru.koryakin.dacha2.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
public class TableBookingDto {

    private Integer id;

    private String phone;

    private int personCount;

    private String date; // TODO: change type to date with validation of field

    private String time; // TODO: change type to time with validation of field

    private String name;

    private String email;

}
