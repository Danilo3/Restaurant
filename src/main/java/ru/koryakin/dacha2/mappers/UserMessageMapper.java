package ru.koryakin.dacha2.mappers;

import org.mapstruct.*;
import ru.koryakin.dacha2.domain.UserEmail;
import ru.koryakin.dacha2.domain.UserMessage;
import ru.koryakin.dacha2.dto.UserEmailDto;
import ru.koryakin.dacha2.dto.UserMessageDto;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface UserMessageMapper {

    UserMessageDto toUserMessageDto(UserMessage userMessage);

    UserMessage toUserMessage(UserMessageDto userMessageDto);

    List<UserMessageDto> toUserMessageDtos(List<UserMessage> userMessageList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserMessageFromDto(UserMessageDto dto, @MappingTarget UserMessage userMessage);
}
