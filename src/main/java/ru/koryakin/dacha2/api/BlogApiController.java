package ru.koryakin.dacha2.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.koryakin.dacha2.domain.BlogPost;
import ru.koryakin.dacha2.dto.BlogPostDto;
import ru.koryakin.dacha2.repositories.BlogPostRepository;
import ru.koryakin.dacha2.services.BlogPostService;
import ru.koryakin.dacha2.services.impl.BlogPostServiceImpl;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BlogPostDto> createPost(BlogPost blogPost, HttpServletResponse response) {
        blogPostService.save(blogPost);
        log.info("Saved new post with content: " + blogPost.toString());
        response.setHeader("Location", "/");
        return blogPostService.findAll();
    }

    @Transactional
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String deletePostFromBlogById(@PathVariable Integer id){
        blogPostService.deleteById(id);
        return "{\"HttpStatus\": \"ok\"}";
    }

    @PatchMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String updatePost(@PathVariable Integer id, @RequestBody BlogPost blogPost){
        blogPost.setModifyDate(LocalDate.now());
        blogPostService.save(blogPost);
        log.info("Post changed: " + blogPost);
        return "{\"httpStatus\": \"ok\"}";
    }

    //TODO: make short edit method

    @PostMapping(value = "/edit/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String editPost(@RequestParam Map<String, String> map){
        if (map != null && !map.isEmpty()) {
            String id = map.get("id");
            if (id != null && !id.isEmpty()) {
                Optional<BlogPostDto> blogPost = blogPostService.findById(Integer.valueOf(id));
                blogPost.ifPresent(post -> {
                    blogPost.get().setContent(map.get("content"));
                    blogPost.get().setModifyDate(LocalDate.now());
                    blogPostService.save(post);
                });
                log.info("Post edited: " + blogPost);
            } else {
                log.warn("Editing failed empty id");
                return "{\"httpStatus\": \"403\"}";
            }
        } else {
            log.warn("Editing failed empty request map");
            return "{\"httpStatus\": \"403\"}";
        }
        return "{\"httpStatus\": \"ok\"}";
    }
}
