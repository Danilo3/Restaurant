package ru.koryakin.dacha2.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.koryakin.dacha2.dto.PostTagDto;
import ru.koryakin.dacha2.mappers.PostTagMapper;
import ru.koryakin.dacha2.repositories.PostTagRepository;
import ru.koryakin.dacha2.services.PostTagService;

import java.util.List;

@Slf4j
@Service
public class PostTagServiceImpl implements PostTagService {

    private final PostTagRepository postTagRepository;

    private final PostTagMapper mapper;

    public PostTagServiceImpl(PostTagRepository postTagRepository, PostTagMapper mapper) {
        this.postTagRepository = postTagRepository;
        this.mapper = mapper;
    }

    @Override
    public List<PostTagDto> findByTagUrlTitle(String tag) {
        return mapper.toPostTagDtos(postTagRepository.findByUrlTitle(tag));
    }

    @Override
    public List<PostTagDto> findAll() {
        return mapper.toPostTagDtos(postTagRepository.findAll());
    }
}
