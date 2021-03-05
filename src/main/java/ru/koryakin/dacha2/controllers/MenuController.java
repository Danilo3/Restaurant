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
import ru.koryakin.dacha2.domain.TableBooking;
import ru.koryakin.dacha2.repositories.MenuItemRepository;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
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


    @RequestMapping(value = "/api/menu/all/", method = RequestMethod.GET)
    public @ResponseBody List<MenuItem> getAllMenu(){
        return new ArrayList<>(menuItemRepository.findAll());
    }

    @RequestMapping(value = "/api/menu/{id}", method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String deleteMenuItemById(@PathVariable(name = "id") Integer id){
        menuItemRepository.deleteById(id);
        log.info("menu item was deleted");
        return "{\"httpStatus\": \"ok\"}";
    }

    @RequestMapping(value = "/api/menu/{id}", method = RequestMethod.PATCH,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String updateMenuItemById(@PathVariable(name = "id") Integer id,
                                                  @RequestBody MenuItem menuItem) {
        menuItemRepository.save(menuItem);
        log.info(" menu item was updated");
        return "{\"httpStatus\": \"ok\"}";
    }

    @RequestMapping(value = "/api/menu/", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<MenuItem> createMenuItem(@RequestBody MenuItem menuItem) {
        menuItemRepository.save(menuItem);
        log.info("menu item was created");
        return new ArrayList<>(menuItemRepository.findAll());
    }

}
