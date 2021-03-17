package ru.koryakin.dacha2.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import ru.koryakin.dacha2.domain.GalleryItem;
import ru.koryakin.dacha2.dto.GalleryItemDto;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface GalleryItemMapper {

    GalleryItemDto toGalleryItemDto(GalleryItem galleryItem);
    GalleryItem toGalleryItem(GalleryItemDto galleryItemDto);
    List<GalleryItemDto> toGalleryItemDtos(List<GalleryItem> galleryItemList);
}
