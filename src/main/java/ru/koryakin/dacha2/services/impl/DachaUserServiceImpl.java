package ru.koryakin.dacha2.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.koryakin.dacha2.domain.DachaUser;
import ru.koryakin.dacha2.repositories.DachaUserRepository;
import ru.koryakin.dacha2.services.DachaUserService;

@Slf4j
@Service
public class DachaUserServiceImpl implements DachaUserService {

    private final DachaUserRepository dachaUserRepository;

    @Autowired
    public DachaUserServiceImpl(DachaUserRepository dachaUserRepository) {
        this.dachaUserRepository = dachaUserRepository;
    }

    @Override
    public DachaUser findByUsername(String username) {
        return dachaUserRepository.findByUsername(username);
    }
}
