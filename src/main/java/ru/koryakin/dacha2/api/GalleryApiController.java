package ru.koryakin.dacha2.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.koryakin.dacha2.domain.GalleryItem;
import ru.koryakin.dacha2.dto.GalleryItemDto;
import ru.koryakin.dacha2.services.GalleryService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/gallery")
public class GalleryApiController {

    private final GalleryService galleryService;

    @Autowired
    public GalleryApiController(GalleryService galleryService) {
        this.galleryService = galleryService;
    }

    @GetMapping(value = "/all/")
    public List<GalleryItemDto> getAllImages() {
        return galleryService.findAll();
    }

    @GetMapping(value = "/{id}")
    public GalleryItemDto getOneById(@PathVariable("id") Integer id) {
        return galleryService.findById(id);
    }


    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteGalleryItemById(@PathVariable(name = "id") Integer id) {
        galleryService.deleteById(id);
        log.info("gallery item was deleted");
        return "{\"HttpStatus\": \"ok\"}";
    }

    @PatchMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String updateGalleryItem(@PathVariable(name = "id") Integer id, @RequestBody GalleryItemDto galleryItemDto) {
        galleryService.update(id, galleryItemDto);
        log.info("gallery item was updated");
        return "{\"HttpStatus\": \"ok\"}";
    }

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GalleryItemDto> createGalleryItem(@RequestBody GalleryItemDto galleryItemDto) {
        galleryService.save(galleryItemDto);
        log.info("new gallery item was created");
        return galleryService.findAll();
    }

}
