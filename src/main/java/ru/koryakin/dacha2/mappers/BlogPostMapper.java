package ru.koryakin.dacha2.mappers;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import ru.koryakin.dacha2.domain.BlogPost;
import ru.koryakin.dacha2.domain.UserEmail;
import ru.koryakin.dacha2.dto.BlogPostDto;
import ru.koryakin.dacha2.dto.UserEmailDto;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface BlogPostMapper {

    BlogPost toBlogPost(BlogPostDto blogPostDto);

    BlogPostDto toBlogPostDto(BlogPost blogPost);

    List<BlogPostDto> toBlogPostDtos(List<BlogPost> blogPostList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateBlogPostFromDto(BlogPostDto dto, @MappingTarget BlogPost blogPost);
}
