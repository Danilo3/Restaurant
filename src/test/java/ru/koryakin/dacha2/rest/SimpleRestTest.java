package ru.koryakin.dacha2.rest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
public class SimpleRestTest {

    @Autowired
    private TestRestTemplate template;

    @Test
    public void givenAuthRequestOnPrivateApiShouldSucceedWith200() throws Exception {
        ResponseEntity<String> result = template.withBasicAuth("user", "user")
                .getForEntity("/api/messages/all/", String.class);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }
}