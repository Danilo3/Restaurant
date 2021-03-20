package ru.koryakin.dacha2.mappers;


import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import ru.koryakin.dacha2.domain.MenuItem;
import ru.koryakin.dacha2.domain.UserEmail;
import ru.koryakin.dacha2.dto.MenuItemDto;
import ru.koryakin.dacha2.dto.UserEmailDto;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface MenuItemMapper {

    MenuItem toMenuItem(MenuItemDto menuItemDto);

    MenuItemDto toMenuItemDto(MenuItem menuItem);

    List<MenuItemDto> toMenuItemDtos(List<MenuItem> menuItemList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateMenuItemFromDto(MenuItemDto dto, @MappingTarget MenuItem menuItem);
}
