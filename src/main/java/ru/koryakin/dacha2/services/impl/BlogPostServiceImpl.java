package ru.koryakin.dacha2.services.impl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import ru.koryakin.dacha2.domain.*;
import ru.koryakin.dacha2.dto.BlogPostDto;
import ru.koryakin.dacha2.mappers.BlogPostMapper;
import ru.koryakin.dacha2.repositories.BlogPostRepository;
import ru.koryakin.dacha2.services.BlogPostService;
import ru.koryakin.dacha2.services.PostTagService;
import ru.koryakin.dacha2.services.UtilService;


import javax.persistence.EntityManager;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;



@Slf4j
@Service
public class BlogPostServiceImpl implements BlogPostService {

    private final UtilService utilService;

    private final PostTagService postTagService;

    private final BlogPostRepository blogPostRepository;

    private  final EntityManager em;

    private final BlogPostMapper mapper;


    @Autowired
    public BlogPostServiceImpl(UtilService utilService, PostTagService postTagService, BlogPostRepository blogPostRepository, EntityManager em, BlogPostMapper mapper) {
        this.utilService = utilService;
        this.blogPostRepository = blogPostRepository;
        this.em = em;
        this.mapper = mapper;
        this.postTagService = postTagService;
    }

    @Transactional
    public Page<BlogPost> findPaginated(Pageable pageable) {
        return findAllByCategory(PostCategory.BLOG, pageable);
    }


    @Transactional
    public Page<BlogPost> findSearchedAndPaginated(Pageable pageable, String search) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<BlogPost> cq = cb.createQuery(BlogPost.class);
        Root<BlogPost> post = cq.from(BlogPost.class);
        Predicate titlePredicate = cb.like(cb.lower(post.get("title")), cb.lower(cb.literal("%" + search +"%")));
        cq.where(titlePredicate);
        TypedQuery<BlogPost> query = em.createQuery(cq);
        ArrayList<BlogPost> postArrayList = new ArrayList<>(query.getResultList());
        return utilService.makePageImpl(postArrayList, pageable);
    }


    @Transactional
    public List<BlogPostDto> getNLatestPostsFromRepository(Integer num) {
        if (num == null || num == 0)
            return new ArrayList<>();
        return mapper.toBlogPostDtos(blogPostRepository.findAllByCategory(PostCategory.BLOG, PageRequest.of(0,
                (int) Math.min(num, blogPostRepository.count()))).getContent());
    }

    @Transactional
    public List<BlogPostDto> findAll(){
        return mapper.toBlogPostDtos(blogPostRepository.findAll());
    }

    @Override
    @Transactional
    public void save(BlogPost blogPost) {
        blogPost = setupPost(blogPost);
        blogPostRepository.save(blogPost);
    }

    @Override
    public void save(BlogPostDto blogPostDto) {
        save(mapper.toBlogPost(blogPostDto));
    }

    @Override
    public void deleteById(Integer id) {
        blogPostRepository.deleteById(id);
    }

    @Override
    @Transactional
    public  BlogPostDto findByUrlTitle(String urlTitle) {
        return  mapper.toBlogPostDto(blogPostRepository.findBlogPostByUrlTitle(urlTitle));
    }

    @Transactional
    public  BlogPostDto findByTitle(String title) {
        return  mapper.toBlogPostDto(blogPostRepository.findBlogPostByTitle(title));
    }


    public List<String> findAllTitle() {
        return blogPostRepository.findAllTitle();
    }

    @Override
    @Transactional
    public Optional<BlogPostDto> findById(Integer id) {
        return Optional.of(mapper.toBlogPostDto(blogPostRepository.findById(id).orElse(new BlogPost())));
    }

    public List<IntroPost> findAllIntro() {
        return blogPostRepository.findAllIntroByCategory(PostCategory.ABOUT);
    }

    @Transactional
    public Page<BlogPost> findAllByCategory(PostCategory category, Pageable pageable) {
        return blogPostRepository.findAllByCategoryOrderByCreateDateDesc(category, pageable);
    }

    public List<EventPost> findAllEvents() {
        return blogPostRepository.findAllEventsByCategoryOrderByCreateDateDesc(PostCategory.EVENT);
    }

    @Transactional
    public int updateViewsCountById(Integer id) {
        return blogPostRepository.updateViewsCountById(id);
    }


    @Transactional
    public Page<BlogPost> findPaginatedByTag(Pageable pageable, String tagUrlTitle) {
        List<Integer> idList = blogPostRepository.getBlogPostIdByTagUrlTitle(tagUrlTitle);
        ArrayList<BlogPost>  postArrayList = new ArrayList<>(blogPostRepository.findAllById(idList));
        return utilService.makePageImpl(postArrayList, pageable);
    }

    @Transactional
    @Override
    public List<BlogPostDto> findPopular() {
        return mapper.toBlogPostDtos(blogPostRepository.findTop5OrderByViewsCount());
    }

    @Override
    public HashMap<LocalDate, Integer> findDates() {
        HashMap<LocalDate, Integer> archive = new HashMap<>();
        List<Date> dates = blogPostRepository.getLocalDates();
        for (Date date:
             dates) {
            LocalDate localDate = utilService.convertToLocalDateViaMillisecond(date);
            archive.put(localDate, blogPostRepository.countPostByDate(localDate));
        }
        return archive;
    }

    @Override
    public Page<BlogPost> findPaginatedByDate(Pageable pageable, LocalDate date) {
        PageRequest pageRequest = PageRequest.of(0, 100);
        return blogPostRepository.findAllByCreateDate(date, pageRequest);
    }



    @Override
    public Page<BlogPostDto> getBlogPage(Optional<Integer> page, Optional<Integer> size, Optional<String> find, Optional<String> tag, Optional<String> date) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
        PageRequest pageRequest = PageRequest.of(currentPage - 1, pageSize);
        Page<BlogPost> blogPage;
        if (date.isPresent()) {
            try {
                LocalDate localDate = LocalDate.parse(date.get());
                blogPage = findPaginatedByDate(pageRequest, localDate);
            } catch (DateTimeParseException exception) {
                log.warn("Wrong date format in blog page: " + exception.getMessage());
                return null;
            }
        } else {
            if (!find.isPresent()) {
                if (tag.isPresent()) {
                    if (!postTagService.findByTagUrlTitle(tag.get()).isEmpty())
                        blogPage = findPaginatedByTag(pageRequest, tag.get());
                    else {
                        return null;
                    }
                } else {
                    blogPage = findPaginated(pageRequest);
                }

            } else {
                if (find.get().length() > 10) {
                    return null;
                }
                blogPage = findSearchedAndPaginated(pageRequest, find.get());
            }
        }
        if (blogPage == null)
            return null;
        return blogPage.map(mapper::toBlogPostDto);
    }

    @Override
    public BlogPostDto findPostByTitle(String urlTitle) {
        if (urlTitle == null || urlTitle.isEmpty())
            return null;
        else {
            log.info("Need blog with name: " + urlTitle);
            BlogPostDto blogPost = mapper.toBlogPostDto(blogPostRepository.findBlogPostByUrlTitle(urlTitle));
            if (blogPost != null && blogPost.getCategory().equals(PostCategory.EVENT)) {
                String newImageUrl = blogPost.getImageUrl().replace("background-image: url(", "").replace(")", "");
                blogPost.setImageUrl(newImageUrl);
            }
            return blogPost;
        }
    }

    private BlogPost setupPost(BlogPost blogPost) {
        blogPost.setCreateDate(LocalDate.now());
        if (blogPost.getCategory().equals(PostCategory.EVENT)) {
            String backgroundImageUrl = blogPost.getEventBackgroundImageUrl();
            backgroundImageUrl = "background-image: url(" + addImagePath(backgroundImageUrl) + ")";
            blogPost.setEventBackgroundImageUrl(backgroundImageUrl);
            String imageUrl = blogPost.getImageUrl();
            imageUrl = "background-image: url(" + addImagePath(imageUrl) + ")";
            blogPost.setImageUrl(imageUrl);
        } else {
            blogPost.setImageUrl(addImagePath(blogPost.getImageUrl()));
        }
        return blogPost;
    }


    private String addImagePath(String imageName) {
        if (!imageName.startsWith("/"))
            return imageName.replace("files/images", "/blog-images");
        return imageName;
    }
}
