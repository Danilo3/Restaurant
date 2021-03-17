package ru.koryakin.dacha2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.koryakin.dacha2.domain.UserEmail;

public interface UserEmailRepository extends JpaRepository<UserEmail, Integer> {
}
