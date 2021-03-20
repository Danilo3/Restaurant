package ru.koryakin.dacha2.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.koryakin.dacha2.domain.UserEmail;
import ru.koryakin.dacha2.repositories.UserEmailRepository;
import ru.koryakin.dacha2.util.TestUtilService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@ActiveProfiles(profiles = "api-tests")
public class UserEmailApiControllerTestWithMockRepository {

    private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private TestRestTemplate template;

    @Autowired
    TestUtilService testUtilService;

    @MockBean
    private UserEmailRepository mockRepository;

    @BeforeEach
    public void init() {
        template.getRestTemplate().setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        template = template.withBasicAuth("user", "user");

    }

    @Test
    public void findUserEmailById() throws Exception {
        UserEmail userEmail = new UserEmail();
        userEmail.setStatus("subscribed");
        userEmail.setEmail("danilo@email.com");
        userEmail.setId(1);
        when(mockRepository.findById(1)).thenReturn(Optional.of(userEmail));
        String expected = "{\"id\":1,\"email\":\"danilo@email.com\",\"status\":\"subscribed\"}";
        ResponseEntity<String> response = template.getForEntity("/api/email/1", String.class);

        testUtilService.printJSON(response, om);

        assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
        assertEquals(HttpStatus.OK, response.getStatusCode());

        JSONAssert.assertEquals(expected, response.getBody(), false);

    }

    @Test
    public void updateUserEmailById() throws Exception {
        String expected = "{\"id\":1,\"email\":\"danilo@email.com\",\"status\":\"unsubscribed\"}";
        UserEmail userEmail = new UserEmail();
        userEmail.setId(1);
        userEmail.setEmail("danilo@email.com");
        userEmail.setStatus("unsubscribed");
        when(mockRepository.findById(1)).thenReturn(Optional.of(userEmail));
        ObjectNode node = om.valueToTree(userEmail);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        final HttpEntity<ObjectNode> requestEntity = new HttpEntity<>(node, headers);
        template.exchange("/api/email/1", HttpMethod.PATCH, requestEntity, String.class);
        ResponseEntity<String> response = template.getForEntity("/api/email/1", String.class);

        JSONAssert.assertEquals(expected, response.getBody(), false);
    }


    @Test
    public void updateUserEmailWithOnlyStatus() throws Exception {
        String expected = "{\"id\":1,\"email\":\"danilo@email.com\",\"status\":\"unsubscribed\"}";
        UserEmail userEmail = new UserEmail();
        userEmail.setId(1);
        userEmail.setEmail("danilo@email.com");
        userEmail.setStatus("unsubscribed");
        String patchInJSON = "{\"status\":\"unsubscribed\"}";
        when(mockRepository.findById(1)).thenReturn(Optional.of(userEmail));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        final HttpEntity<String> requestEntity = new HttpEntity<>(patchInJSON, headers);
        template.exchange("/api/email/1", HttpMethod.PATCH, requestEntity, String.class);
        ResponseEntity<String> response = template.getForEntity("/api/email/1", String.class);

        JSONAssert.assertEquals(expected, response.getBody(), false);
    }




}
