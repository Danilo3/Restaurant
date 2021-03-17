package ru.koryakin.dacha2.controllers;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.koryakin.dacha2.domain.MenuCategory;
import ru.koryakin.dacha2.domain.MenuItem;
import ru.koryakin.dacha2.dto.MenuItemDto;
import ru.koryakin.dacha2.services.MenuService;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.List;
import java.util.Locale;

@Slf4j
@Controller
public class MenuController {

    private final MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping(value = {"/menu.html", "/menu"})
    public String menu(Model model) {
        addMenuItemsToPageModel(model);
        return "menu";
    }

    @GetMapping(value = "/menu/download/")
    public ResponseEntity<byte[]> download(@RequestParam(value = "name") String file) {
        return menuService.downloadFile(file);
    }

    @GetMapping(value = {"/strange", "/strange.html"})
    private String showSomeMeme() {
        return "strange";
    }

    private void addMenuItemsToPageModel(Model model) {
        for (MenuCategory category : MenuCategory.values()) {
            if (category.isShow()) {
                List<MenuItemDto> allByCategory = menuService.findAllByCategory(category.toString());
                model.addAttribute(category.name().toLowerCase(Locale.ROOT), allByCategory);
            }
        }
    }
}
