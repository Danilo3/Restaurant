package ru.koryakin.dacha2.controllers;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.koryakin.dacha2.domain.GalleryItem;
import ru.koryakin.dacha2.repositories.GalleryItemRepository;
import ru.koryakin.dacha2.services.GalleryService;
import ru.koryakin.dacha2.services.impl.GalleryServiceImpl;
import ru.koryakin.dacha2.services.UtilService;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@Slf4j
public class GalleryController {

    private final GalleryService galleryService;

    @Autowired
    public GalleryController(GalleryService galleryService) {
        this.galleryService = galleryService;
    }

    @GetMapping(value = {"/gallery.html", "/gallery"})
    public String gallery(@RequestParam("page") Optional<Integer> page,
                          @RequestParam("size") Optional<Integer> size,
                          @RequestParam("tag") Optional<String> tag,
                          Model model) {
        Page<GalleryItem> galleryPage = galleryService.getGalleryPage(page.orElse(1), size.orElse(5), tag.orElse("empty"));
        model.addAttribute("imagePage", galleryPage);
        int totalPages = galleryPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "gallery";
    }
}
