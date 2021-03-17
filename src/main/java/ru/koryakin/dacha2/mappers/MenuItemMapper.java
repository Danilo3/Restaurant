package ru.koryakin.dacha2.mappers;


import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import ru.koryakin.dacha2.domain.MenuItem;
import ru.koryakin.dacha2.dto.MenuItemDto;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface MenuItemMapper {

    MenuItem toMenuItem(MenuItemDto menuItemDto);
    MenuItemDto toMenuItemDto(MenuItem menuItem);
    List<MenuItemDto> toMenuItemDtos(List<MenuItem> menuItemList);
}
