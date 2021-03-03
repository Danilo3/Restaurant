package ru.koryakin.dacha2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.koryakin.dacha2.domain.DachaUser;

public interface DachaUserRepository extends JpaRepository<DachaUser, Integer> {

    DachaUser findByUsername(String username);

}
