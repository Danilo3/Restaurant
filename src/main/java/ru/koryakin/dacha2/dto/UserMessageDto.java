package ru.koryakin.dacha2.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
public class UserMessageDto {

    private Integer id;
    private String name;
    private String phone;
    private String email;
    private String message;

}
