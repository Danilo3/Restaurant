package ru.koryakin.dacha2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.koryakin.dacha2.domain.BlogPost;
import ru.koryakin.dacha2.repositories.BlogPostRepository;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class BlogPostService {

    @Autowired
    BlogPostRepository blogPostRepository;

    public Page<BlogPost> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<BlogPost> list;
        Iterable<BlogPost> posts = blogPostRepository.findAll();
        ArrayList<BlogPost> postArrayList = new ArrayList<>();
        for (BlogPost post : posts)
            postArrayList.add(post);

        if (postArrayList.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, postArrayList.size());
            list = postArrayList.subList(startItem, toIndex);
        }

        Page<BlogPost> blogPage = new PageImpl<>(list, PageRequest.of(currentPage, pageSize),
                                                        postArrayList.size());

        return blogPage;
    }

    public ArrayList<BlogPost> getNLatestPostsFromRepository(Integer num) {
        if (num == null || num == 0)
            return new ArrayList<>();
        return new ArrayList<>(blogPostRepository.findAll(PageRequest.of(0, (int) Math.min(num, blogPostRepository.count()))).getContent());
    }
}
