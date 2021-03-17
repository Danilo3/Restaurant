package ru.koryakin.dacha2.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
public class DachaUserDto {

    private Integer id;
    private String username;
    private String password;
    private String avatarUrl;
    private String role;
}