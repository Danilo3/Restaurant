package ru.koryakin.dacha2.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import ru.koryakin.dacha2.domain.GalleryItem;
import ru.koryakin.dacha2.dto.GalleryItemDto;

import java.util.List;

public interface GalleryService {

    List<GalleryItemDto> findAll();

    void deleteById(Integer id);

    void save(GalleryItem galleryItem);

    Page<GalleryItem> getPaginatedItemsByTag(Pageable pageRequest, String tag);

    Page<GalleryItem> getAllGalleryPictures(Pageable pageRequest);

    Page<GalleryItem> getGalleryPage(Integer page, Integer size, String tag);

    void save(GalleryItemDto galleryItemDto);

    GalleryItemDto findById(Integer id);

    void update(Integer id, GalleryItemDto galleryItemDto);

}
