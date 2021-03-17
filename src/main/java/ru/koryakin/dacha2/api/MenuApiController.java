package ru.koryakin.dacha2.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.koryakin.dacha2.domain.MenuItem;
import ru.koryakin.dacha2.dto.MenuItemDto;
import ru.koryakin.dacha2.services.MenuService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/menu")
public class MenuApiController {

    private final MenuService menuService;

    @Autowired
    public MenuApiController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping(value = "/all/")
    public List<MenuItemDto> getAllMenu(){
        return menuService.findAll();
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteMenuItemById(@PathVariable(name = "id") Integer id){
        menuService.deleteById(id);
        log.info("menu item was deleted");
        return "{\"HttpStatus\": \"ok\"}";
    }

    @PatchMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String updateMenuItem(@PathVariable(name = "id") Integer id, @RequestBody MenuItem menuItem) {
        menuService.save(menuItem);
        log.info(" menu item was updated");
        return "{\"HttpStatus\": \"ok\"}";
    }

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MenuItemDto> createMenuItem(@RequestBody MenuItem menuItem) {
        menuService.save(menuItem);
        log.info("menu item was created");
        return menuService.findAll();
    }

}
