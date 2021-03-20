package ru.koryakin.dacha2.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.koryakin.dacha2.domain.GalleryItem;
import ru.koryakin.dacha2.dto.GalleryItemDto;
import ru.koryakin.dacha2.mappers.GalleryItemMapper;
import ru.koryakin.dacha2.repositories.GalleryItemRepository;
import ru.koryakin.dacha2.services.GalleryService;
import ru.koryakin.dacha2.services.UtilService;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class GalleryServiceImpl implements GalleryService {


    private final GalleryItemRepository galleryItemRepository;

    private final GalleryItemMapper mapper;

    @Autowired
    public GalleryServiceImpl(GalleryItemRepository galleryItemRepository, GalleryItemMapper mapper) {
        this.galleryItemRepository = galleryItemRepository;
        this.mapper = mapper;
    }

    @Override
    public Page<GalleryItem> getAllGalleryPictures(Pageable pageable) {
        return galleryItemRepository.findAll(pageable);
    }

    @Override
    public Page<GalleryItem> getGalleryPage(Integer page, Integer size, String tag) {
        ArrayList<String> categories = new ArrayList<>(Arrays.asList("events", "guests", "food", "interior"));
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        if (!tag.equals("empty") && categories.contains(tag)) {
            return getPaginatedItemsByTag(pageRequest, tag);
        } else {
            return getAllGalleryPictures(pageRequest);
        }
    }

    @Override
    public void save(GalleryItemDto galleryItemDto) {
        save(mapper.toGalleryItem(galleryItemDto));
    }

    @Override
    public GalleryItemDto findById(Integer id) {
        return mapper.toGalleryItemDto(galleryItemRepository.findById(id).orElse(new GalleryItem()));
    }

    @Override
    public void update(Integer id, GalleryItemDto galleryItemDto) {
        GalleryItem galleryItem = galleryItemRepository.findById(id).orElseThrow();
        mapper.updateGalleryItemFromDto(galleryItemDto, galleryItem);
        galleryItemRepository.save(galleryItem);
    }

    @Override
    public Page<GalleryItem> getPaginatedItemsByTag(Pageable pageable, String category) {
        return galleryItemRepository.findAllByCategory(category, pageable);
    }

    @Override
    public List<GalleryItemDto> findAll() {
        return mapper.toGalleryItemDtos(galleryItemRepository.findAll());
    }

    @Override
    public void deleteById(Integer id) {
        galleryItemRepository.deleteById(id);
    }

    @Override
    public void save(GalleryItem galleryItem) {
        galleryItem.setImageUrl(addImagePath(galleryItem.getImageUrl()));
        galleryItemRepository.save(galleryItem);
    }

    private String addImagePath(String imageName) {
        if (imageName.startsWith("files"))
            return imageName.replace("files/images", "/gallery-images");
        else
            return imageName;
    }
}
