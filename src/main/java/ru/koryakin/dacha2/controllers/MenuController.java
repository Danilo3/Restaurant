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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.koryakin.dacha2.domain.MenuCategory;
import ru.koryakin.dacha2.domain.MenuItem;
import ru.koryakin.dacha2.repositories.MenuItemRepository;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Enumeration;
import java.util.Locale;

@Controller
@Slf4j
public class MenuController {


    @Value("${menu.path}")
    private String menuFileName;

    @Value("${wine.path}")
    private String wineMenuFileName;

    @Autowired
    private MenuItemRepository menuItemRepository;


    @GetMapping(value = {"/menu.html", "/menu"})
    public String menu(Model model) {
        addMenuItemsToPageModel(model);
        return "menu";
    }

    private void addMenuItemsToPageModel(Model model) {
        for (MenuCategory category : MenuCategory.values()) {
            if (category.isShow()) {
                Iterable<MenuItem> allByCategory = menuItemRepository.findAllByCategory(category.toString());
                model.addAttribute(category.name().toLowerCase(Locale.ROOT), allByCategory);
            }
        }

    }

    @GetMapping(value = "/menu/download/")
    public ResponseEntity<byte[]> download(@RequestParam(value = "name") String file) {
        ResponseEntity<byte[]> response;
        try {
            String filename = null;
            if (file.equals("menu")) {
                filename = menuFileName;
            } else if (file.equals("wine")) {
                filename = wineMenuFileName;
            } else  {
                HttpHeaders headers = new HttpHeaders();
                headers.setLocation(URI.create("/strange"));
                response = new ResponseEntity<>(null, headers, HttpStatus.PERMANENT_REDIRECT);
                return response;
            }
            InputStream is = FileUtils.openInputStream(new File(filename));
            byte[] contents = IOUtils.toByteArray(is);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            filename = file + ".pdf";
            headers.setContentDispositionFormData(filename, filename);
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
            response = new ResponseEntity<>(contents, headers, HttpStatus.OK);
        } catch (IOException ex) {
            log.warn("Error writing file to output stream");
            throw new RuntimeException("IOError writing file to output stream");
        }
        return response;
    }

    @GetMapping(value = "/strange")
    private String showSomeMeme() {
        return "strange";
    }


}
