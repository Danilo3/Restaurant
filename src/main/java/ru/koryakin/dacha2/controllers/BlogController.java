package ru.koryakin.dacha2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.koryakin.dacha2.domain.BlogPost;
import ru.koryakin.dacha2.repositories.BlogPostRepository;
import ru.koryakin.dacha2.services.BlogPostService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class BlogController {

    @Autowired
    private BlogPostService blogPostService;

    @RequestMapping(value = "/blog.html", method = RequestMethod.GET)
    public String blog(
            Model model,
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Page<BlogPost> blogPage = blogPostService.findPaginated(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("blogPage", blogPage);

        int totalPages = blogPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "blog";
    }

    @GetMapping(value = "/blog-detail.html")
    public String blogDetail(){
        return "blog-detail";
    }

    @GetMapping(value = "/api/blog/", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<BlogPost> getLatestPostsFromBlog(@RequestParam(value = "num",required = false) Integer num) {
       return blogPostService.getNLatestPostsFromRepository(num);
    }

    @Autowired
    BlogPostRepository blogPostRepository;

    @RequestMapping(value = "/api/blog/all/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<BlogPost> getAllPostsFromBlog() {
        return new ArrayList<>(blogPostRepository.findAll());
    }


    @RequestMapping(value = "/api/blog/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<BlogPost> createPost(@RequestBody BlogPost blogPost) {
        blogPost.setCreateDate(LocalDate.now());
        blogPostRepository.save(blogPost);
        return blogPostRepository.findAll();
    }


    @RequestMapping(value = "/api/blog/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String deletePostFromBlogById(@RequestParam() Integer id){
        blogPostRepository.deleteById(id);
        return "{\"httpStatus\": \"ok\"}";
    }

    @RequestMapping(value = "/api/blog/{id}", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String updatePost(@RequestParam() Integer id, @RequestBody BlogPost blogPost){
        blogPost.setModifyDate(LocalDate.now());
        blogPostRepository.save(blogPost);
        return "{\"httpStatus\": \"ok\"}";
    }

}
