package ru.koryakin.dacha2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.koryakin.dacha2.domain.PostTag;

import java.util.List;


public interface PostTagRepository extends JpaRepository<PostTag, Integer> {
    List<PostTag> findByUrlTitle(String tagUrlTitle);
}
