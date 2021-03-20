package ru.koryakin.dacha2.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.koryakin.dacha2.domain.UserEmail;
import ru.koryakin.dacha2.dto.UserEmailDto;
import ru.koryakin.dacha2.mappers.UserEmailMapper;
import ru.koryakin.dacha2.repositories.UserEmailRepository;
import ru.koryakin.dacha2.services.UserEmailService;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserEmailServiceImpl implements UserEmailService {

    private final UserEmailRepository userEmailRepository;

    private final UserEmailMapper mapper;

    @Autowired
    public UserEmailServiceImpl(UserEmailRepository userEmailRepository, UserEmailMapper mapper) {
        this.userEmailRepository = userEmailRepository;
        this.mapper = mapper;
    }

    @Override
    public void save(UserEmail userEmail) {
        userEmail.setStatus("подписан");
        userEmailRepository.save(userEmail);
    }

    @Override
    public List<UserEmailDto> findAll() {
        return mapper.toUserEmailDtos(userEmailRepository.findAll());
    }

    @Override
    public void deleteById(Integer id) {
        userEmailRepository.deleteById(id);
    }

    @Override
    public void save(UserEmailDto userEmailDto) {
        save(mapper.toUserEmail(userEmailDto));
    }

    @Override
    public UserEmailDto findById(Integer id) {
        return mapper.toUserEmailDto(userEmailRepository.findById(id).orElse(new UserEmail()));
    }

    @Override
    public void update(Integer id, UserEmailDto userEmailDto) {
        UserEmail userEmail = userEmailRepository.findById(id).orElseThrow();
        mapper.updateUserEmailFromDto(userEmailDto, userEmail);
        userEmailRepository.save(userEmail);
    }
}
