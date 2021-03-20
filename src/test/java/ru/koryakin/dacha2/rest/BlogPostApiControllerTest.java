package ru.koryakin.dacha2.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.util.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ru.koryakin.dacha2.domain.PostCategory;
import ru.koryakin.dacha2.dto.BlogPostDto;
import ru.koryakin.dacha2.dto.PostTagDto;
import ru.koryakin.dacha2.util.TestUtilService;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@ActiveProfiles(profiles = "api-tests")
public class BlogPostApiControllerTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    TestUtilService testUtilService;

    @BeforeEach
    public void setup() {
        restTemplate = restTemplate.withBasicAuth("user", "user");
    }

    @Test
    public void createBlogPost() throws JsonProcessingException, JSONException {
        Pair<ObjectNode, Integer> result = testUtilService.makeCreateOperation(restTemplate, "/api/blog/", this::initBlogPost, BlogPostDto.class,
                (o1, o2) -> o1.getAuthor().equals(o2.getAuthor()) && o1.getTitle().equals(o2.getTitle()));
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("/api/blog/" + result.getSecond(), String.class);
        JSONAssert.assertEquals(result.getFirst().toString(), responseEntity.getBody(), false);
    }

    private BlogPostDto initBlogPost() {
        BlogPostDto blogPostDto = new BlogPostDto();
        blogPostDto.setTitle("title");
        blogPostDto.setUrlTitle("url-title");
        blogPostDto.setContent("simple content");
        blogPostDto.setAuthor("author");
        blogPostDto.setCategory(PostCategory.BLOG);
        blogPostDto.setCreateDate(LocalDate.now());
        blogPostDto.setPreviewText("preview");
        PostTagDto postTagDto1 = new PostTagDto();
        PostTagDto postTagDto2 = new PostTagDto();
        postTagDto2.setTagName("kitchen");
        postTagDto1.setTagName("design");
        postTagDto1.setUrlTitle("url1");
        postTagDto2.setUrlTitle("url2");
        blogPostDto.setTags(List.of(postTagDto1, postTagDto2));
        return blogPostDto;
    }
}
