package ru.koryakin.dacha2.mappers;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import ru.koryakin.dacha2.domain.GalleryItem;
import ru.koryakin.dacha2.domain.UserEmail;
import ru.koryakin.dacha2.dto.GalleryItemDto;
import ru.koryakin.dacha2.dto.UserEmailDto;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface GalleryItemMapper {

    GalleryItemDto toGalleryItemDto(GalleryItem galleryItem);

    GalleryItem toGalleryItem(GalleryItemDto galleryItemDto);

    List<GalleryItemDto> toGalleryItemDtos(List<GalleryItem> galleryItemList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateGalleryItemFromDto(GalleryItemDto dto, @MappingTarget GalleryItem galleryItem);
}
