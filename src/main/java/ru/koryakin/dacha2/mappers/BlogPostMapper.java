package ru.koryakin.dacha2.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.koryakin.dacha2.domain.BlogPost;
import ru.koryakin.dacha2.dto.BlogPostDto;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface BlogPostMapper {

    BlogPost toBlogPost(BlogPostDto blogPostDto);
    BlogPostDto toBlogPostDto(BlogPost blogPost);
    List<BlogPostDto> toBlogPostDtos(List<BlogPost> blogPostList);
}
