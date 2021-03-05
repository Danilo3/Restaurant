package ru.koryakin.dacha2.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@NoArgsConstructor
public class UserEmail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    @Email
    @NotBlank
    private String email;

    private boolean isSubscribed;
}
