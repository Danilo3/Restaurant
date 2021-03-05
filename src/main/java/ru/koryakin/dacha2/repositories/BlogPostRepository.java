package ru.koryakin.dacha2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.koryakin.dacha2.domain.BlogPost;


public interface BlogPostRepository extends JpaRepository<BlogPost, Integer> {

}
