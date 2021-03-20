package ru.koryakin.dacha2.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.koryakin.dacha2.domain.UserEmail;

import java.io.IOException;
import java.util.List;


@ActiveProfiles(profiles = "api-tests")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
public class UserEmailRestTestWithRealRepository {

    private static final ObjectMapper om = new ObjectMapper();

    private static Integer id = 0;

    @Autowired
    TestRestTemplate restTemplate;

    @BeforeEach
    public void init() {
        restTemplate.getRestTemplate().setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        restTemplate = restTemplate.withBasicAuth("user", "user");
    }

    @Test
    @Order(1)
    public void testCreateAndUpdate() throws JSONException, IOException {
        String patchInJSON = "{\"status\":\"unsubscribed\"}";
        UserEmail userEmail = new UserEmail();
        userEmail.setEmail("danilo@email.com");
        userEmail.setStatus("undefined");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        final HttpEntity<String> requestEntity = new HttpEntity<>(patchInJSON, headers);
        ResponseEntity<String> response = restTemplate.postForEntity("/api/email/", userEmail, String.class);
        TypeFactory typeFactory = om.getTypeFactory();
        List<UserEmail> userEmails = om.readValue(response.getBody(), typeFactory.constructCollectionType(List.class, UserEmail.class));
        for (UserEmail email: userEmails) {
            if (email.getEmail().equals(userEmail.getEmail()) && email.getStatus().equals("undefined")) {
                id = email.getId();
                break;
            }
        }
        response = restTemplate.exchange("/api/email/" + id, HttpMethod.PATCH, requestEntity, String.class);
        response = restTemplate.getForEntity("/api/email/" + id, String.class);
        String expected = "{\"id\":" + id + ",\"email\":\"danilo@email.com\",\"status\":\"unsubscribed\"}";
        JSONAssert.assertEquals(expected, response.getBody(), false);
    }


    @Test
    @Order(2)
    public void testDelete() throws JSONException {
        restTemplate.delete("/api/email/" + id);
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("/api/email/" + id, String.class);
        String expected = "{\"id\":null,\"email\":null,\"status\":null}";
        JSONAssert.assertEquals(expected, responseEntity.getBody(), false);
    }

}
