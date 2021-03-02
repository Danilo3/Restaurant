package ru.koryakin.dacha2.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.koryakin.dacha2.model.UserMessage;

public interface UserMessageRepository extends CrudRepository<UserMessage, Integer> {
}
