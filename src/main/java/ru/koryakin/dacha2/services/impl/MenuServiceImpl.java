package ru.koryakin.dacha2.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.koryakin.dacha2.domain.MenuItem;
import ru.koryakin.dacha2.dto.MenuItemDto;
import ru.koryakin.dacha2.mappers.MenuItemMapper;
import ru.koryakin.dacha2.repositories.MenuItemRepository;
import ru.koryakin.dacha2.services.MenuService;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class MenuServiceImpl implements MenuService {

    @Value("${menu.path}")
    private String menuFileName;

    @Value("${wine.path}")
    private String wineMenuFileName;

    @Value("${lunch.path}")
    private String lunchMenuFileName;

    private final MenuItemRepository menuItemRepository;

    private final MenuItemMapper mapper;

    @Autowired
    public MenuServiceImpl(MenuItemRepository menuItemRepository, MenuItemMapper mapper) {
        this.menuItemRepository = menuItemRepository;
        this.mapper = mapper;
    }

    @Override
    public List<MenuItemDto> findAll() {
        return mapper.toMenuItemDtos(menuItemRepository.findAll());
    }

    @Override
    public void deleteById(Integer id) {
        menuItemRepository.deleteById(id);
    }

    @Override
    public void save(MenuItem menuItem) {
        menuItemRepository.save(menuItem);
    }

    @Override
    public List<MenuItemDto> findAllByCategory(String category) {
        return mapper.toMenuItemDtos(menuItemRepository.findAllByCategory(category));
    }

    @Override
    public ResponseEntity<byte[]> downloadFile(String file) {
        ResponseEntity<byte[]> response;
        try {
            String filename = null;
            if (file!= null && file.equals("menu")) {
                filename = menuFileName;
            } else if (file != null && file.equals("wine")) {
                filename = wineMenuFileName;
            } else if (file != null && file.equals("lunch")) {
                filename = lunchMenuFileName;
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

    @Override
    public List<MenuItemDto> findAllByCategoryAndIsAvailableForOrder(String category, boolean isAvailable) {
        return mapper.toMenuItemDtos(menuItemRepository.findAllByCategoryAndIsAvailableForOrder(category, isAvailable));
    }

    @Override
    public Optional<MenuItemDto> findById(Integer id) {
        return Optional.of(mapper.toMenuItemDto(menuItemRepository.findById(id).orElse(new MenuItem())));
    }
}
