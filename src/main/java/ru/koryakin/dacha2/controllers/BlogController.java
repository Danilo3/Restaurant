package ru.koryakin.dacha2.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.koryakin.dacha2.dto.BlogPostDto;
import ru.koryakin.dacha2.services.BlogPostService;
import ru.koryakin.dacha2.services.PostTagService;

import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Controller
public class BlogController {

    private final BlogPostService blogPostService;

    private final PostTagService postTagService;

    @Autowired
    public BlogController(BlogPostService blogPostService, PostTagService postTagService) {
        this.blogPostService = blogPostService;
        this.postTagService = postTagService;
    }

    @GetMapping(value = {"/blog.html", "/blog"})
    public String blog( Model model, @RequestParam("page") Optional<Integer> page,
                                    @RequestParam("size") Optional<Integer> size,
                                    @RequestParam("find") Optional<String>  find,
                                    @RequestParam("tag")  Optional<String>  tag,
                                    @RequestParam("date") Optional<String>  date) {
        Page<BlogPostDto> blogPage = blogPostService.getBlogPage(page, size, find, tag,date);
        if (blogPage == null)
            return "404";
        model.addAttribute("blog", blogPage);
        addAttributesToModel(model, blogPage.getTotalPages());
        return "blog";
    }

    @GetMapping(value = "/blog-detail/")
    public String blogDetail(@RequestParam(name = "name", required = false) String urlTitle, Model model)
    {
        BlogPostDto blogPost = blogPostService.findPostByTitle(urlTitle);
        if (blogPost == null) {
            return "404";
        }
        else {
            model.addAttribute("post", blogPost);
            model.addAttribute("date", Date.from(blogPost.getCreateDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            addAttributesToModel(model, 0);
            blogPostService.updateViewsCountById(blogPost.getId());
        }
        return "blog-detail";
    }

    private void addAttributesToModel(Model model, int totalPages) {
        model.addAttribute("tags",  postTagService.findAll());
        model.addAttribute("popular", blogPostService.findPopular());
        model.addAttribute("archive", blogPostService.findDates());
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
    }
}
