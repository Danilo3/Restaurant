package ru.koryakin.dacha2.services;

import org.springframework.http.ResponseEntity;
import ru.koryakin.dacha2.domain.MenuItem;
import ru.koryakin.dacha2.dto.MenuItemDto;

import java.util.List;
import java.util.Optional;

public interface MenuService {

    List<MenuItemDto> findAll();

    void deleteById(Integer id);

    void save(MenuItem menuItem);

    List<MenuItemDto> findAllByCategory(String category);

    ResponseEntity<byte[]> downloadFile(String file);

    List<MenuItemDto> findAllByCategoryAndIsAvailableForOrder(String category, boolean isAvailable);

    Optional<MenuItemDto> findById(Integer id);
}
