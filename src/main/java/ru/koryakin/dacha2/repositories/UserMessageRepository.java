package ru.koryakin.dacha2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.koryakin.dacha2.domain.UserMessage;

public interface UserMessageRepository extends JpaRepository<UserMessage, Integer> {

}
