package ru.koryakin.dacha2.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.koryakin.dacha2.domain.DachaUser;
import ru.koryakin.dacha2.repositories.DachaUserRepository;

@Service
@Slf4j
public class DachaUserDetailsServiceImpl implements UserDetailsService {

    private DachaUserRepository dachaUserRepository;

    private PasswordEncoder encoder;

    @Autowired
    public void setEncoder(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Autowired
    public void setDachaUserRepository(DachaUserRepository dachaUserRepository) {
        this.dachaUserRepository = dachaUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        DachaUser dachaUser = dachaUserRepository.findByUsername(username);
        if (dachaUser == null) {
            throw new UsernameNotFoundException(username);
        }
        return User.builder()
                .username(dachaUser.getUsername())
                .password(encoder.encode(dachaUser.getPassword()))
                .roles(dachaUser.getRole())
                .build();
    }
}