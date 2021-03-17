package ru.koryakin.dacha2.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.koryakin.dacha2.domain.PostTag;
import ru.koryakin.dacha2.dto.PostTagDto;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface PostTagMapper {

    PostTagDto toPostTagDto(PostTag postTag);

    PostTag toPostTag(PostTagDto postTagDto);

    List<PostTagDto> toPostTagDtos(List<PostTag> postTags);
}
