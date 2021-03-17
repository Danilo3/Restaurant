package ru.koryakin.dacha2.services;

import org.springframework.stereotype.Service;
import ru.koryakin.dacha2.domain.UserMessage;
import ru.koryakin.dacha2.dto.UserMessageDto;

import java.util.List;

public interface UserMessageService {

    List<UserMessageDto> findAll();

    void deleteById(Integer id);

    void save(UserMessage userMessage);
}
