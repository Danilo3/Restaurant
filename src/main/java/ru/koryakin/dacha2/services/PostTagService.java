package ru.koryakin.dacha2.services;

import ru.koryakin.dacha2.dto.PostTagDto;

import java.util.Collection;
import java.util.List;

public interface PostTagService {
    List<PostTagDto> findByTagUrlTitle(String s);

    List<PostTagDto> findAll();
}
