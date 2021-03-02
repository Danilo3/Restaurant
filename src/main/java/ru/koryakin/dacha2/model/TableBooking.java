package ru.koryakin.dacha2.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
public class TableBooking {

    private String phone;

    private int personCount;

    private String date;

    private String time;

    private String name;

    private String email;
}
