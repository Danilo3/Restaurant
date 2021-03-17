package ru.koryakin.dacha2.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.koryakin.dacha2.domain.GalleryItem;

import java.util.List;

public interface GalleryItemRepository extends JpaRepository<GalleryItem, Integer> {

    Page<GalleryItem> findAllByCategory(String category, Pageable pageable);
}
