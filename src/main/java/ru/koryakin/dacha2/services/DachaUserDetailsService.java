package ru.koryakin.dacha2.services;

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
public class DachaUserDetailsService implements UserDetailsService {

    @Autowired
    private DachaUserRepository dachaUserRepository;

    @Autowired
    PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) {
        DachaUser dachaUser = dachaUserRepository.findByUsername(username);
        if (dachaUser == null) {
            throw new UsernameNotFoundException(username);
        }
        UserDetails user = User.builder()
                .username(dachaUser.getUsername())
                .password(encoder.encode(dachaUser.getPassword()))
                .roles(dachaUser.getRole())
                .build();
        return user;
    }
}