package ru.koryakin.dacha2.services.impl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.koryakin.dacha2.annotations.EmailSend;
import ru.koryakin.dacha2.domain.UserMessage;
import ru.koryakin.dacha2.dto.UserMessageDto;
import ru.koryakin.dacha2.mappers.UserMessageMapper;
import ru.koryakin.dacha2.repositories.UserMessageRepository;
import ru.koryakin.dacha2.services.UserMessageService;

import java.util.List;
import java.util.Optional;


@Slf4j
@Service
public class UserMessageServiceImpl implements UserMessageService {


    private final UserMessageRepository userMessageRepository;

    private final UserMessageMapper userMessageMapper;

    @Autowired
    public UserMessageServiceImpl(UserMessageRepository userMessageRepository, UserMessageMapper userMessageMapper) {
        this.userMessageRepository = userMessageRepository;
        this.userMessageMapper = userMessageMapper;
    }

    UserMessageDto findUserMessageById(Integer id) {
        Optional<UserMessage> userMessage = userMessageRepository.findById(id);
        if (userMessage.isPresent()) {
            return userMessageMapper.toUserMessageDto(userMessage.get());
        } else {
            log.warn("no user find with id: " + id);
            return null;
        }
    }

    @Override
    public List<UserMessageDto> findAll() {
        return userMessageMapper.toUserMessageDtos(userMessageRepository.findAll());
    }

    @Override
    public void deleteById(Integer id) {
        userMessageRepository.deleteById(id);
    }

    @Override
    @EmailSend(subject = "New message on site")
    public void save(UserMessage userMessage) {
        userMessageRepository.save(userMessage);
    }

    @Override
    public void save(UserMessageDto userMessageDto) {
        save(userMessageMapper.toUserMessage(userMessageDto));
    }

    @Override
    public UserMessageDto findById(Integer id) {
        return userMessageMapper.toUserMessageDto(userMessageRepository.findById(id).orElse(new UserMessage()));
    }

    @Override
    public void update(Integer id, UserMessageDto userMessageDto) {
        UserMessage userMessage = userMessageRepository.findById(id).orElseThrow();
        userMessageMapper.updateUserMessageFromDto(userMessageDto, userMessage);
        userMessageRepository.save(userMessage);

    }
}
