package ru.koryakin.dacha2.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import ru.koryakin.dacha2.domain.BlogPost;
import ru.koryakin.dacha2.dto.BlogPostDto;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface BlogPostService {

    Page<BlogPost> findPaginatedByDate(Pageable pageRequest, LocalDate localDate);

    Page<BlogPost> findPaginatedByTag(Pageable pageRequest, String tag);

    Page<BlogPost> findPaginated(Pageable pageRequest);

    Page<BlogPost> findSearchedAndPaginated(Pageable pageRequest, String search);

    List<BlogPostDto> findPopular();

    HashMap<LocalDate, Integer> findDates();

    BlogPostDto findByUrlTitle(String urlTitle);

    int updateViewsCountById(Integer id);

    List<BlogPostDto> getNLatestPostsFromRepository(Integer num);

    List<BlogPostDto> findAll();

    void save(BlogPost blogPost);

    void save(BlogPostDto blogPostDto);

    void deleteById(Integer id);

    Optional<BlogPostDto> findById(Integer id);

    List<String> findAllTitle();

    BlogPostDto findByTitle(String title);

    Page<BlogPostDto> getBlogPage(Optional<Integer> page, Optional<Integer> size, Optional<String> find, Optional<String> tag, Optional<String> date);

    BlogPostDto findPostByTitle(String urlTitle);

    void update(Integer id, BlogPostDto blogPostDto);

    boolean editPost(Map<String, String> params);
}
