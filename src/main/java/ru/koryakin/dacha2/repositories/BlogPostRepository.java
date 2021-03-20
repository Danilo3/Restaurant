package ru.koryakin.dacha2.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.koryakin.dacha2.domain.*;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public interface BlogPostRepository extends JpaRepository<BlogPost, Integer> {

    BlogPost findBlogPostByUrlTitle(String title);

    BlogPost findBlogPostByTitle(String title);

    Page<BlogPost> findAllByCategory(PostCategory category, Pageable request);

    List<IntroPost> findAllIntroByCategory(PostCategory category);

    List<EventPost> findAllEventsByCategoryOrderByCreateDateDesc(PostCategory category);

    Page<BlogPost> findAllByCategoryOrderByCreateDateDesc(PostCategory category, Pageable request);

    @Query(value = "SELECT * FROM blog_post  WHERE blog_post.category='BLOG' ORDER  BY blog_post.views_count DESC LIMIT 5", nativeQuery = true)
    List<BlogPost> findTop5OrderByViewsCount();

    @Query(value = "select title from blog_post", nativeQuery = true)
    List<String> findAllTitle();

    @Modifying
    @Query(value = "update blog_post set views_count = views_count + 1 where id=?1", nativeQuery = true)
    int updateViewsCountById(Integer id);

    @Query(value = "SELECT bpt.blog_post_id  FROM  blog_post_tags bpt INNER JOIN post_tag pt on pt.id = bpt.tags_id WHERE pt.url_title=?1", nativeQuery = true)
    List<Integer> getBlogPostIdByTagUrlTitle(String tagUrlTitle);

    @Query(value = "SELECT DISTINCT ON (date_part('month', create_date)) create_date FROM blog_post WHERE category = 'BLOG'", nativeQuery = true)
    List<Date> getLocalDates();

    @Query(value = "SELECT count(*) FROM blog_post WHERE category='BLOG' AND date_part('month', create_date)=date_part('month', CAST (?1 AS DATE))", nativeQuery = true)
    Integer countPostByDate(LocalDate date);

    @Query(value = "SELECT * FROM blog_post WHERE category='BLOG' AND date_part('month', create_date)=date_part('month', CAST (?1 AS DATE))", nativeQuery = true)
    Page<BlogPost> findAllByCreateDate(LocalDate date, Pageable pageable);
}
