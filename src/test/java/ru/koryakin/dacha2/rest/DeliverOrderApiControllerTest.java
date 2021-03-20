package ru.koryakin.dacha2.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.util.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.koryakin.dacha2.dto.DeliveryOrderDto;
import ru.koryakin.dacha2.dto.DeliveryOrderItemDto;
import ru.koryakin.dacha2.util.TestUtilService;


import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@ActiveProfiles(profiles = "api-tests")
public class DeliverOrderApiControllerTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    TestUtilService testUtilService;

    @BeforeEach
    public void setup() {
        restTemplate = restTemplate.withBasicAuth("user", "user");
    }

    @Test
    public void testCreateAndUpdate() throws JsonProcessingException, JSONException {
        Pair<ObjectNode, Integer> result = testUtilService.makeCreateOperation(restTemplate, "/api/order/", this::initOrder, DeliveryOrderDto.class,
                (o1, o2) -> o1.getAddress().equals(o2.getAddress()) && o1.getName().equals(o2.getName()));
        Integer id = result.getSecond();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("/api/order/" + id, String.class);

        JSONAssert.assertEquals(result.getFirst().toString(), responseEntity.getBody(), false);

        testUtilService.makePatchRequest(restTemplate, "/api/order/"  + id, this::initPatch);
        ResponseEntity<String> response = restTemplate.getForEntity("/api/order/" + id, String.class);

        testUtilService.printJSON(response, new ObjectMapper());
        JSONAssert.assertEquals("{\"address\": \"new Address\"}", response.getBody(), false);
    }

    private DeliveryOrderDto initOrder() {
        List<DeliveryOrderItemDto> orderItems = List.of(
                new DeliveryOrderItemDto(null, "name", 34.56, 50, null),
                new DeliveryOrderItemDto(null, "name2", 574.32, 20, null));
        return DeliveryOrderDto.builder()
                .address("address")
                .comment("comment")
                .email("danilo@email.com")
                .name("Name")
                .price(350.35)
                .timeToDeliver("tomorrow")
                .phone("899944411111")
                .username("order_for_user[777]")
                .orderItems(orderItems)
                .build();
    }

    private DeliveryOrderDto initPatch() {
        List<DeliveryOrderItemDto> orderItems = List.of(
                new DeliveryOrderItemDto(null, "new item", 340.56, 560, null),
                new DeliveryOrderItemDto(null, "new item 2", 574.32, 20, null));
        return DeliveryOrderDto.builder()
                .address("new Address")
                .orderItems(orderItems)
                .build();
    }
}
