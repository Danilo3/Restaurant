package ru.koryakin.dacha2.mappers;

import org.mapstruct.*;
import ru.koryakin.dacha2.domain.UserEmail;
import ru.koryakin.dacha2.dto.UserEmailDto;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface UserEmailMapper {

    UserEmail toUserEmail(UserEmailDto userEmailDto);

    UserEmailDto toUserEmailDto(UserEmail userEmail);

    List<UserEmailDto> toUserEmailDtos(List<UserEmail> userEmailList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserEmailFromDto(UserEmailDto dto, @MappingTarget UserEmail userEmail);

}
