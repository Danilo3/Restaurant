package ru.koryakin.dacha2.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.koryakin.dacha2.domain.UserEmail;

public interface UserEmailRepository extends CrudRepository<UserEmail, Integer> {
}
