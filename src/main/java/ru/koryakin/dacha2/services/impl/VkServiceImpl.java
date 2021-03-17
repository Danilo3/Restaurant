package ru.koryakin.dacha2.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.koryakin.dacha2.services.VkService;

@Slf4j
@Service
public class VkServiceImpl implements VkService {

    private final RestTemplate restTemplate;

    @Value("${vkapi.url}")
    String vkApiUrl;

    @Autowired
    public VkServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public String getWallPlainJSON() {
        String wallJSON = restTemplate.getForObject(vkApiUrl, String.class);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(wallJSON);
            JsonNode first = rootNode.path("response").path("items").get(0);
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(first);
        } catch (JsonProcessingException exception) {
            log.error("Error while parsing JSON from api.vk.com");
        }
        return "{}";
    }

}
