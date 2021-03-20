package ru.koryakin.dacha2.mappers;

import org.mapstruct.*;
import ru.koryakin.dacha2.domain.PostTag;
import ru.koryakin.dacha2.domain.UserEmail;
import ru.koryakin.dacha2.dto.PostTagDto;
import ru.koryakin.dacha2.dto.UserEmailDto;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface PostTagMapper {

    PostTagDto toPostTagDto(PostTag postTag);

    PostTag toPostTag(PostTagDto postTagDto);

    List<PostTagDto> toPostTagDtos(List<PostTag> postTags);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updatePostTagFromDto(PostTagDto dto, @MappingTarget PostTag postTag);
}
