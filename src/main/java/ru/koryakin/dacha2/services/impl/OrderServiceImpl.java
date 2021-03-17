package ru.koryakin.dacha2.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.koryakin.dacha2.annotations.EmailSend;
import ru.koryakin.dacha2.domain.DeliveryOrder;
import ru.koryakin.dacha2.domain.DeliveryOrderItem;
import ru.koryakin.dacha2.dto.DeliveryOrderDto;
import ru.koryakin.dacha2.mappers.DeliveryOrderMapper;
import ru.koryakin.dacha2.mappers.OrderItemMapper;
import ru.koryakin.dacha2.repositories.DeliveryOrderRepository;
import ru.koryakin.dacha2.services.OrderItemService;
import ru.koryakin.dacha2.services.OrderService;
import ru.koryakin.dacha2.services.UtilService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    private final DeliveryOrderRepository orderRepository;

    private final DeliveryOrderMapper deliveryOrderMapper;

    private final UtilService utilService;

    private final OrderItemService orderItemService;

    @Autowired
    public OrderServiceImpl(DeliveryOrderRepository orderRepository, DeliveryOrderMapper deliveryOrderMapper,
                            UtilService utilService, OrderItemService orderItemService) {
        this.orderRepository = orderRepository;
        this.deliveryOrderMapper = deliveryOrderMapper;
        this.utilService = utilService;
        this.orderItemService = orderItemService;
    }

    @Override
    public DeliveryOrderDto getDeliveryOrderByUsername(String username) {
        return deliveryOrderMapper.toDeliveryOrderDto(orderRepository.getDeliveryOrderByUsername(username));
    }

    @Override
    public void save(DeliveryOrder deliveryOrder) {
        orderRepository.save(deliveryOrder);
    }

    @Override
    @EmailSend(subject = "New order")
    public void save(DeliveryOrderDto deliveryOrderDto) {
        save(deliveryOrderMapper.toDeliveryOrder(deliveryOrderDto));
    }

    @Override
    public List<DeliveryOrderDto> findAll() {
        return deliveryOrderMapper.toDeliveryOrderDtos(orderRepository.findAll());
    }

    @Override
    public void deleteById(Integer id) {
        orderRepository.deleteById(id);
    }

    @Override
    public boolean makeOrder(String cart, HttpServletResponse response, AtomicInteger userId) {
        if (cart != null) {
            String username = "order_for_user[" + userId.incrementAndGet() + "]";
            String token = utilService.generateToken(username);
            Cookie cookie = new Cookie("abracadabra", token);
            cookie.setMaxAge(600);
            response.addCookie(cookie);
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode rootNode = objectMapper.readTree(cart);
                ArrayList<DeliveryOrderItem> items = new ArrayList<>();
                double total = 0.0;
                for (JsonNode node : rootNode) {
                    double price = node.path("product_price").asDouble();
                    items.add(new DeliveryOrderItem(null, node.path("product_name").textValue(),
                            price,
                            node.path("product_quantity").asInt(),
                            node.path("product_id").asInt()));
                    total += price;
                }
                DeliveryOrder order = new DeliveryOrder();
                order.setUsername(username);
                order.setOrderItems(items);
                order.setPrice(total);
                orderItemService.saveAll(items);
                orderItemService.flush();
                save(order);
            } catch (JsonProcessingException e) {
                log.warn("Something wrong with cart_list:" + cart);
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    @Override
    public void finishOrder(DeliveryOrder deliveryOrder, HttpServletResponse response) {
        deleteCookie(response);
        deliveryOrder.setOrderItems(orderRepository.getDeliveryOrderByUsername(deliveryOrder.getUsername()).getOrderItems());
    }

    @Override
    public DeliveryOrderDto getDeliveryOrder(HttpServletRequest request) {
        String token = null;
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("abracadabra")) {
                token = cookie.getValue();
                break;
            }
        }
        if (token != null && !token.isEmpty()) {
            String username = utilService.decodeToken(token);
            return getDeliveryOrderByUsername(username);
        } else {
            return null;
        }
    }

    private void deleteCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie("abracadabra", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}
