package ru.koryakin.dacha2.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.koryakin.dacha2.domain.BlogPost;

public interface BlogPostRepository extends CrudRepository<BlogPost, Integer> {
}
