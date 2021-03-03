package ru.koryakin.dacha2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class VkService {

    private final RestTemplate restTemplate;

    public VkService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public String getWallPlainJSON() {
        String url = "PATH_TO_VK_API"; // ERROR THERE!
        return this.restTemplate.getForObject(url, String.class);
    }

}
