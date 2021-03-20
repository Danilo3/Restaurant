package ru.koryakin.dacha2.services;

import ru.koryakin.dacha2.domain.UserEmail;
import ru.koryakin.dacha2.dto.UserEmailDto;

import java.util.List;

public interface UserEmailService {

    void save(UserEmail userEmail);

    List<UserEmailDto> findAll();

    void deleteById(Integer id);

    void save(UserEmailDto userEmailDto);

    UserEmailDto findById(Integer id);

    void update(Integer id, UserEmailDto userEmailDto);
}
