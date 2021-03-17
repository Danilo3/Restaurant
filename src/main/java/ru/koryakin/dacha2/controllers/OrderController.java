package ru.koryakin.dacha2.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.koryakin.dacha2.domain.*;
import ru.koryakin.dacha2.dto.DeliveryOrderDto;
import ru.koryakin.dacha2.dto.MenuItemDto;
import ru.koryakin.dacha2.mappers.DeliveryOrderMapper;
import ru.koryakin.dacha2.services.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Controller
public class OrderController {

    private final AtomicInteger userId;

    private final MenuService menuService;

    private final OrderService orderService;

    private final DeliveryOrderMapper mapper;

    public OrderController(MenuService menuService, OrderService orderService, DeliveryOrderMapper mapper) {
        this.menuService = menuService;
        this.orderService = orderService;
        this.userId = new AtomicInteger(552);
        this.mapper = mapper;
    }

    @GetMapping(value = {"/order", "/order.html"})
    private String order(@RequestParam Optional<String> category, Model model) {
        if (category.isPresent()){
            try {
                MenuCategory menuCategory = MenuCategory.valueOf(category.get().toUpperCase());
                model.addAttribute("productList", menuService.findAllByCategoryAndIsAvailableForOrder(menuCategory.toString(), true));
            } catch (IllegalArgumentException e) {
                return "404";
            }
        } else {
            model.addAttribute("productList", menuService.findAllByCategory(MenuCategory.DESSERTS.toString()));
        }
        return "order";
    }

    @GetMapping(value = "/item/")
    private String itemDetail(@RequestParam("id") Optional<Integer> id, Model model) {
        if (id.isPresent()) {
            Optional<MenuItemDto> menuItem = menuService.findById(id.get());
            if (menuItem.isPresent())
                model.addAttribute("item", menuItem.get());
            else
                return "404";
            return "item-detail";
        } else {
            return "404";
        }
    }

    @PostMapping(value = "/order")
    private String makingOrder(@RequestParam("cart_list") String cart_list, HttpServletResponse response){
        if (orderService.makeOrder(cart_list, response, userId))
            return "redirect:" + "/order/detail/";
        else
            return "400";
    }

    @GetMapping(value = "/order/detail/")
    private String orderDetail(Model model, HttpServletRequest request) {
        DeliveryOrderDto order = orderService.getDeliveryOrder(request);
        if (order == null) {
            return "404";
        } else {
            model.addAttribute("delivery_order", order);
            return "order-detail";
        }
    }

    @PostMapping(value = "/order/detail")
    private String finishOrder(@Valid DeliveryOrder deliveryOrder, BindingResult bindingResult, HttpServletResponse response, Model model) {
        if(!validate(deliveryOrder, bindingResult, model)) {
            return "order-detail";
        }
        else {
            orderService.finishOrder(deliveryOrder, response);
            orderService.save(mapper.toDeliveryOrderDto(deliveryOrder));
            return "redirect:" + "/order/success";

        }
    }

    @GetMapping(value = "/order/success")
    private String successOrder() {
        return "success";
    }

    private boolean validate(DeliveryOrder deliveryOrder, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors() || isEmptyFields(deliveryOrder)) {
            ArrayList<String> errors = new ArrayList<>();
            bindingResult.getAllErrors().forEach((error) -> {
                String fieldName = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                errors.add(fieldName+ ": " + errorMessage);
            });
            if ((deliveryOrder.getEmail().isEmpty() && deliveryOrder.getPhone().isEmpty())) {
                errors.add("Для связи должен быть телефон или имя");
            }
            if (deliveryOrder.getAddress().isEmpty()) {
                errors.add("Адрес не должен быть пустым");
            }
            DeliveryOrderDto dto = mapper.toDeliveryOrderDto(deliveryOrder);
            dto.setOrderItems(orderService.getDeliveryOrderByUsername(deliveryOrder.getUsername()).getOrderItems());
            model.addAttribute("delivery_order", dto);
            model.addAttribute("errors", errors);
            model.addAttribute("is_errors", true);
            return false;
        }
        return true;
    }

    private boolean isEmptyFields(DeliveryOrder deliveryOrder) {
        return (deliveryOrder.getEmail().isEmpty() && deliveryOrder.getPhone().isEmpty()) || (deliveryOrder.getAddress().isEmpty());
    }
}
