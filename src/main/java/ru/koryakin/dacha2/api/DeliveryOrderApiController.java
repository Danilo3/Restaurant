package ru.koryakin.dacha2.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.koryakin.dacha2.domain.DeliveryOrder;
import ru.koryakin.dacha2.dto.DeliveryOrderDto;
import ru.koryakin.dacha2.dto.DeliveryOrderItemDto;
import ru.koryakin.dacha2.services.OrderService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/order")
public class DeliveryOrderApiController {

    private final OrderService orderService;

    @Autowired
    public DeliveryOrderApiController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(value = "/all/")
    public List<DeliveryOrderDto> getAllOrders() {
        return orderService.findAll();
    }

    @GetMapping(value = "/{id}")
    public DeliveryOrderDto getOneById(@PathVariable("id") Integer id) {
        return orderService.findById(id);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteOrderById(@PathVariable(name = "id") Integer id) {
        orderService.deleteById(id);
        log.info("Order was deleted");
        return "{\"HttpStatus\": \"ok\"}";
    }

    @PatchMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String updateOrder(@PathVariable(name = "id") Integer id, @RequestBody DeliveryOrderDto deliveryOrderDto) {
        orderService.update(id, deliveryOrderDto);
        log.info("order was updated");
        return "{\"HttpStatus\": \"ok\"}";
    }

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DeliveryOrderDto> createOrder(@RequestBody DeliveryOrderDto deliveryOrderDto) {
        orderService.save(deliveryOrderDto);
        log.info("new order was created");
        return orderService.findAll();
    }

    @GetMapping(value = "/{id}/items/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DeliveryOrderItemDto> getItemsById(@PathVariable("id") Integer id) {
        return orderService.getDeliveryOrderItemsById(id);
    }

    @PostMapping(value = "/{id}/items/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DeliveryOrderItemDto> createItemsByOrderId(@PathVariable("id") Integer id, @RequestBody() List<DeliveryOrderItemDto> orderItems) {
        return orderService.saveOrderItems(id, orderItems);
    }

    @PatchMapping(value = "/{id}/items/", produces = MediaType.APPLICATION_JSON_VALUE)
    public String updateOrderItems(@PathVariable("id") Integer id, @RequestBody List<DeliveryOrderItemDto> orderItems) {
        orderService.updateOrderItems(id, orderItems);
        return "{\"HttpStatus\": \"ok\"}";
    }

    @GetMapping(value = "{order_id}/items/{item_id}")
    public DeliveryOrderItemDto getItemByOrderIdAndItemId(@PathVariable("order_id") Integer order_id, @PathVariable("item_id") Integer item_id) {
        return orderService.getItemByOrderIdAndItemId(order_id, item_id);
    }


}
