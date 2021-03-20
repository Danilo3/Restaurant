package ru.koryakin.dacha2.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.koryakin.dacha2.domain.BlogPost;
import ru.koryakin.dacha2.dto.BlogPostDto;
import ru.koryakin.dacha2.services.BlogPostService;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/blog")
public class BlogApiController {

    private final BlogPostService blogPostService;

    @Autowired
    public BlogApiController(BlogPostService blogPostService) {
        this.blogPostService = blogPostService;
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BlogPostDto> getLatestPostsFromBlog(@RequestParam(value = "num", required = false) Integer num) {
        return blogPostService.getNLatestPostsFromRepository(num);
    }

    @GetMapping(value = "/all/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BlogPostDto> getAllPostsFromBlog() {
        return blogPostService.findAll();
    }

    @GetMapping(value = "/{id}")
    public BlogPostDto getOneById(@PathVariable("id") Integer id) {
        return blogPostService.findById(id).orElse(new BlogPostDto());
    }

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BlogPostDto> createPost(@RequestBody BlogPostDto blogPostDto) {
        blogPostService.save(blogPostDto);
        log.info("Saved new post with content: " + blogPostDto.toString());
        return blogPostService.findAll();
    }

    @Transactional
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String deletePostFromBlogById(@PathVariable Integer id) {
        blogPostService.deleteById(id);
        return "{\"HttpStatus\": \"ok\"}";
    }

    @PatchMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String updatePost(@PathVariable Integer id, @RequestBody BlogPostDto blogPostDto) {
        blogPostDto.setModifyDate(LocalDate.now());
        blogPostService.update(id, blogPostDto);
        log.info("Post changed: " + blogPostDto);
        return "{\"httpStatus\": \"ok\"}";
    }


    @PostMapping(value = "/edit/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String editPost(@RequestParam Map<String, String> map, HttpServletResponse response) {
        if (blogPostService.editPost(map)) {
            log.info("Post edited successfully");
            response.setStatus(HttpStatus.OK.value());
            return "{\"HttpStatus\": \"OK\"}";
        } else {
            log.warn("Edit post failed: map is bad");
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return "{\"HttpStatus\": \"403\"}";
        }
    }

    @PostMapping(value = "/new/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BlogPostDto> createNewPost(BlogPost blogPost) {
        blogPostService.save(blogPost);
        log.info("Saved new post with content: " + blogPost.toString());
        return blogPostService.findAll();
    }
}
