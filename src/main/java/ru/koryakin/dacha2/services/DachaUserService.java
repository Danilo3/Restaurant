package ru.koryakin.dacha2.services;

import ru.koryakin.dacha2.domain.DachaUser;

public interface DachaUserService {

    DachaUser findByUsername(String username);
}
