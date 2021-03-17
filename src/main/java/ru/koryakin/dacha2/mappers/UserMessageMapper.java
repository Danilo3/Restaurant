package ru.koryakin.dacha2.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.koryakin.dacha2.domain.UserMessage;
import ru.koryakin.dacha2.dto.UserMessageDto;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface UserMessageMapper {

    UserMessageDto toUserMessageDto(UserMessage userMessage);
    UserMessage toUserMessage(UserMessageDto userMessageDto);
    List<UserMessageDto> toUserMessageDtos(List<UserMessage> userMessageList);

}
