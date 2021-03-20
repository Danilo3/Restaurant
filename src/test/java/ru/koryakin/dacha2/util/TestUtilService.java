package ru.koryakin.dacha2.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.platform.commons.util.ReflectionUtils;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.util.Pair;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;
import ru.koryakin.dacha2.dto.BlogPostDto;

import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Supplier;

@Service
public class TestUtilService {

    /**
        @return pair of json node of object and id of created object
     **/
    public <T> Pair<ObjectNode, Integer> makeCreateOperation(TestRestTemplate restTemplate, String url, Supplier<T> supplier, Class<T> clazz, BiPredicate<T, T> predicate) throws JsonProcessingException {
        Integer id = 0;
        ObjectMapper om = new ObjectMapper();
        setupObjectMapper(om);
        var requestEntity = makeRequestEntity(supplier, om);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, requestEntity, String.class);
        TypeFactory typeFactory = om.getTypeFactory();
        List<T> objects = om.readValue(responseEntity.getBody(), typeFactory.constructCollectionType(List.class, clazz));
        for (T dto: objects) {
            if (predicate.test(dto, supplier.get())) {
                id = ReflectionTestUtils.invokeMethod(dto, "getId");
                break;
            }
        }
        return Pair.of(requestEntity.getBody(), id);
    }

    public <T> ResponseEntity<String> makePatchRequest(TestRestTemplate restTemplate, String url, Supplier<T> patchSupplier) {
       ObjectMapper objectMapper = new ObjectMapper();
       setupObjectMapper(objectMapper);
       var request = makeRequestEntity(patchSupplier, objectMapper);
       return restTemplate.exchange(url, HttpMethod.PATCH, request, String.class);
    }

    public void printJSON(Object object, ObjectMapper om) {
        String result;
        try {
            result = om.writerWithDefaultPrettyPrinter().writeValueAsString(object);
            System.out.println(result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private  <T> HttpEntity<ObjectNode> makeRequestEntity(Supplier<T> supplier, ObjectMapper om) {
        T obj = supplier.get();
        ObjectNode node = om.valueToTree(obj);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(node, headers);
    }

    private void setupObjectMapper(ObjectMapper objectMapper) {
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }


}
